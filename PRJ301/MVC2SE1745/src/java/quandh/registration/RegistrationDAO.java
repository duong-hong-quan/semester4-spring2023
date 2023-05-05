/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quandh.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import quandh.util.DBHelper;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException {
    public RegistrationDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException {
        RegistrationDTO result = null;
        //Connect DB
        // Write SQL
        //Create Statement
        //Excute Statement
        //Process result

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = " SELECT username,  [lastname], [isAdmin] "
                        + "FROM Registration "
                        + "WHERE username = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, lastname, role);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastName(String searchValue) throws ClassNotFoundException, SQLException, NamingException {
        //Connect DB
        // Write SQL
        //Create Statement
        //Excute Statement
        //Process result

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = " SELECT username, password,lastname,isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();

                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, lastname, role);
                    if (this.accountList == null) {
                        accountList = new ArrayList<>();
                    }
                    this.accountList.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public ArrayList<RegistrationDTO> getAll() throws SQLException, NamingException {
        ArrayList<RegistrationDTO> accountlist = new ArrayList<>();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM Registration";
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, isAdmin);
                    accountlist.add(dto);
                }
            }
        } catch (ClassNotFoundException ex) {
            // Handle ClassNotFoundException
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return accountlist;
    }

    public boolean deleteUser(String username) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = " delete from Registration"
                        + " where username= ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateUser(String username, String password, boolean role) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Registration]"
                        + " SET  [password] = ?, "
                        + " [isAdmin] = ?"
                        + " WHERE username= ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NamingException {
        RegistrationDAO dao = new RegistrationDAO();
       RegistrationDTO result = dao.checkLogin("admin", "123");
        System.out.println(result);
    }
}
