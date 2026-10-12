package com.learnJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Book {
	private Connection connection;
	private Scanner scanner;
	
	public Book(Connection connection,Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
	
	public void addBooks() {
		System.out.println("Enter the Name of the Book: ");
		String book_name = scanner.nextLine();
		
		System.out.println("Enter the Name of The Author: ");
		String author = scanner.nextLine();
		
		System.out.println("Enter the Genre of the Book: ");
		String genre = scanner.nextLine();
		
		try {
			String Query= "INSERT INTO Books(book_name,author,genre) values (?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(Query);
			preparedStatement.setString(1,book_name);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, genre);
			
			int affectRows = preparedStatement.executeUpdate();
			
			if (affectRows>0) {
				System.out.println("Book Added Successfully!!");
			} else {
				System.out.println("Failed to Add Books!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getBookByID(int id)
	{
		String query="select * from Books where id = ? ";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			/*ResultSet resultset = preparedStatement.executeQuery();
			
			if (resultset.next()) {
				return true;
			}
			else
			{
				return false;
			}*/
			try (ResultSet resultset = preparedStatement.executeQuery()) {
	            return resultset.next();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
		/*}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}*/
	
	
	

}
