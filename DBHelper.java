/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 12345
 */
public class DBHelper {

    public static Connection makeConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
}
