package com.example.myhealthapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class Database {
    Connection connection;

    public Connection getDbConnection(){
        String connectionstring="jdbc:mysql://server134.hosting.reg.ru:3306/u2431395_healthapp_test";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        }catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        try{
            connection=DriverManager.getConnection(connectionstring, "u2431395_doctor", "doctor123132!");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return connection;
    }
    public boolean login(String username, String password) {
        if (connection == null) {
            Log.e("Database", "Подключение к базе данных не установлено");
            return false;
        }

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean register(String username, String email, String password) {
        if (connection == null) {
            Log.e("Database", "Подключение к базе данных не установлено");
            return false;
        }
        else
        {
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            try{
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
            catch (SQLException e)
            {
                return false;
            }
        }
    }
}
