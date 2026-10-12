# Library Management System

## 📌 Project Overview

The Library Management System is a Java-based console application developed to manage basic library operations. It uses JDBC to connect Java with a MySQL database and stores information about books, readers, and issued books.

## ✨ Features

- **Add Books:** Add book details such as book name, author, and genre.
- **Register Readers:** Register library readers with their details.
- **Issue Books:** Issue books to registered readers.
- **Book Validation:** Check whether a book and reader exist before issuing a book.
- **Issue Tracking:** Store book issue records and issue dates in MySQL.
- **Database Connectivity:** Perform database operations using Java, JDBC, and SQL.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Database:** MySQL
- **Database Connectivity:** JDBC
- **IDE:** Eclipse IDE

## 📂 Project Structure

```text
LibraryManagementSystem/
├── src/
│   └── com/
│       └── learnJDBC/
│           ├── LibraryManagementSystem.java
│           ├── Book.java
│           └── Reader.java
├── README.md
└── .gitignore
```

*Note: Update the class names and folder structure if your project contains additional classes.*

## ⚙️ Prerequisites

Make sure you have installed:

- Java Development Kit (JDK)
- MySQL Server
- Eclipse IDE or another Java IDE
- MySQL Connector/J

## 🗄️ Database Setup

1. Start your MySQL server.
2. Create a database named `Library`.
3. Create the required tables: `Books`, `Readers`, and `Issue_Books`.
4. Configure the database connection details in your local Java configuration.

The application expects the following tables:

- **Books:** Stores book IDs, names, authors, and genres.
- **Readers:** Stores reader IDs, names, phone numbers, and genders.
- **Issue_Books:** Stores issue records, including reader ID, book ID, and issue date.

Ensure the column names and foreign keys match the SQL queries in your Java code.

## 🚀 How to Run

1. Clone or download this repository.
2. Open Eclipse.
3. Import the project as an existing Java project.
4. Ensure MySQL is running and the database and tables are created.
5. Add the MySQL Connector/J JAR to the project's Java Build Path.
6. Configure your local database username and password.
7. Run `LibraryManagementSystem.java` as a Java Application.
8. Follow the console menu to add books, register readers, or issue books.

## 🔌 Database Connection

The application connects to MySQL using JDBC.

Example JDBC URL:

```java
jdbc:mysql://localhost:3306/Library
```

Set your own database username and password locally. **Never upload passwords or other sensitive credentials to GitHub.**

## 🎓 Learning Outcomes

- Practiced Java programming and object-oriented programming concepts.
- Learned to connect Java applications to MySQL using JDBC.
- Gained experience writing SQL queries and using prepared statements.
- Practiced validating records before performing database operations.
- Built a menu-driven console application with database integration.

## 👩‍💻 Author

**Swathi Jonnavithula**

Computer Science and Engineering (Data Science) Student

## 📄 License

This project is intended for educational and learning purposes.
