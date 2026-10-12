package com.learnJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;

public class Reader {
	private Connection connection;
	private Scanner scanner;
	public Reader(Connection connection,Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
	
	public void RegisterMembers() {
		System.out.println("Enter the Name of the Reader: ");
		String reader_name = scanner.next();
		
		System.out.println("Enter the phone Number: ");
		String phone_no = scanner.next();
		
		System.out.println("Enter the gender of the Reader: ");
		String gender = scanner.next();
		
		try {
			String Query = "Insert into Readers(reader_name,phone_no,gender) values (?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(Query);
			preparedStatement.setString(1,reader_name);
			preparedStatement.setString(2, phone_no);
			preparedStatement.setString(3, gender);
			
			int affectedRows = preparedStatement.executeUpdate();
			
			if(affectedRows>0) {
				System.out.println("Members registered Successfully");
			}else {
				System.out.println("Failed to Register Members!");
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getReaderByID(int id)
	{
		String query ="select * from Readers where reader_id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			
			if(resultset.next()) {
				return true;
			}
			else
			{
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

}
