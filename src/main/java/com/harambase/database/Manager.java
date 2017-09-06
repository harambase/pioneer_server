package com.harambase.database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.sql.*;
import java.util.ResourceBundle;

public class Manager {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
    private final static String DRIVER   = resourceBundle.getString("jdbc.driver");
    private final static String URL      = resourceBundle.getString("jdbc.url");
    private final static String USERNAME = resourceBundle.getString("jdbc.username");
    private final static String PASSWORD = resourceBundle.getString("jdbc.password");

    public static void init(){

    }

    public static void reset() {
        try {
            Class.forName(DRIVER).newInstance();
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setErrorLogWriter(null);
            runner.setLogWriter(null);
            runner.runScript(Resources.getResourceAsReader("/pioneer.sql"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void restore(){

    }

    public static void backup(){

    }

    public static void main(String[] args){
        reset();
    }
}
