package DataBase;


import java.sql.*;

public class connect {

    public Connection c;
    public Statement s;
    public connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","datnq123");
            s = (Statement)c.createStatement();		
            // int result = s.executeUpdate("CREATE DATABASE databasename");
        }catch(ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}
