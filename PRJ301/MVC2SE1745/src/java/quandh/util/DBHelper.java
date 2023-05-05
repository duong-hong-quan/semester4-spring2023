/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quandh.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1745;instanceName=LAPTOP_HONGQUAN";
        Connection connection = DriverManager.getConnection(url, "sa", "123");
        return connection;

    }
   
//    public static Connection makeConnection() throws ClassNotFoundException, SQLException, NamingException {
//        //1.Find current Context
//          Context context = new InitialContext();
//        //2. Find Tomcat's Context
//        Context tomcatContext = (Context) context.lookup("java:comp/env");
//        javax.sql.DataSource ds = (javax.sql.DataSource)tomcatContext.lookup("HongQuanDS");
//        Connection con = ds.getConnection();
//        return con;
//    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException {
        System.out.println(DBHelper.makeConnection());
    }
   
    
}
