package com.learnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryManagementSystem {

    private static final String url = "jdbc:mysql://localhost:3306/Library";
    private static final String username = "root";
    private static final String password = "!sw@thi!2404";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(url, username, password)) {

            Book book = new Book(connection, scanner);
            Reader reader = new Reader(connection, scanner);

            while (true) {
                System.out.println("\n===== Library Management System =====");
                System.out.println("1. Add Books");
                System.out.println("2. Register Members");
                System.out.println("3. Issue Books");
                System.out.println("4. Exit");
                System.out.print("Please Enter Your Choice: ");
                
                while (!scanner.hasNextInt()) { // handle invalid inputs
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine();
                }
                int choice = scanner.nextInt();
                scanner.nextLine();

                //int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        book.addBooks();
                        System.out.println();
                        break;
                    case 2:
                        reader.RegisterMembers();
                        System.out.println();
                        break;
                    case 3:
                        issueBook(book, reader, connection, scanner);
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("THANK YOU FOR USING LIBRARY MANAGEMENT SYSTEM!");
                        return;
                    default:
                        System.out.println("Please enter a valid input.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void issueBook(Book book, Reader reader, Connection connection, Scanner scanner) {
        System.out.print("Please Enter Book ID: ");
        int bookID = scanner.nextInt();
        System.out.print("Please Enter Reader ID: ");
        int readerID = scanner.nextInt();
        System.out.print("Please Enter Issue Date (YYYY-MM-DD): ");
        String issueDate = scanner.next();

        if (book.getBookByID(bookID) && reader.getReaderByID(readerID)) {
            if (checkBookAvailability(bookID, issueDate, connection)) {
                String issueQuery = "INSERT INTO Issue_Books (reader_id, book_id, issue_date) VALUES (?, ?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(issueQuery)) {
                    ps.setInt(1, readerID);
                    ps.setInt(2, bookID);
                    ps.setString(3, issueDate);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Book issued successfully.");
                    } else {
                        System.out.println("Book could not be issued.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Book is NOT available!");
            }
        } else {
            System.out.println("Either the book or the reader does not exist!");
        }
    }

    public static boolean checkBookAvailability(int bookID, String issueDate, Connection connection) {
        String query = "SELECT COUNT(*) FROM Issue_Books WHERE book_id = ? AND issue_date = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, bookID);
            ps.setString(2, issueDate);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
