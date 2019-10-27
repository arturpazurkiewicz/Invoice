package org.pazurkiewicz;

import java.sql.*;

abstract class Database {
    static Connection connection;
    static Statement statement;
    static PreparedStatement preparedStatement;
Database(){
    makeJDBCConnection();
}
    private static void makeJDBCConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/faktura", "faktura_user","user");
            statement = connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
