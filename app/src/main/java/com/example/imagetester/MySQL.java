package com.example.imagetester;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {
    public Connection getMySQLConnection(){
        Connection ret = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String connectionURL = "jdbc:mysql://";

            String userName = "";

            String password = "";

            ret = DriverManager.getConnection(connectionURL, userName, password);


        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            return ret;
        }
    }
}
