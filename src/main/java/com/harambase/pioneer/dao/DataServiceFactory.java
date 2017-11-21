package com.harambase.pioneer.dao;

import java.sql.Connection;
import java.sql.DriverManager;

class DataServiceFactory {
    
    static Connection openDBConnection() {
        try {
            // Load driver and link to driver manager
            Class.forName("com.mysql.jdbc.Driver");
            // Create a connection to the specified database
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/pioneer_2.0","root", "123456");
        } catch (Exception E) {
            E.printStackTrace();
        }
        return null;
    }
}
