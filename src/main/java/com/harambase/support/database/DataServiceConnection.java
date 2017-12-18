package com.harambase.support.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataServiceConnection {
    
    public static Connection openDBConnection() {
        try {
            // Load driver and link to driver manager
            Class.forName(DataServiceProperty.DRIVER);
            // Create a connection to the specified database
            return DriverManager.getConnection(DataServiceProperty.URL, DataServiceProperty.USERNAME, DataServiceProperty.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
