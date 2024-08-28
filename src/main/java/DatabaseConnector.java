import java.sql.*;

public class DatabaseConnector {
    // Method to establish a connection to the SQLite database
    public Connection connect() {
        Connection connection = null;
        try {
            // Corrected SQLite connection string
            String url = "jdbc:sqlite:C:/Users/admin/sqlite/Bookstore.db"; // Replace with the correct path
            connection = DriverManager.getConnection(url);
            System.out.println("Successfully connected to the database!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to execute a SELECT query and print all records from the books table
    public void selectAllBooks() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = this.connect();
            if (connection == null) {
                System.out.println("Failed to make a connection to the database.");
                return;
            }

            String sql = "SELECT * FROM books";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            // Print results
            while (rs.next()) {
                System.out.println(rs.getString("title") + "\t" +
                        rs.getString("author") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement.");
            e.printStackTrace();
        } finally {
            // Properly close the resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to test the application
    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.selectAllBooks();
    }
}