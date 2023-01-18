/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBHelper;

/**
 *
 * @author 12345
 */
public class UserDAO implements Serializable {

    private static Connection con = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    private static int effectedRow = 0;
    private static boolean result = false;

    private void refresh() {
        con = null;
        stm = null;
        rs = null;
        effectedRow = 0;
        result = false;
    }

    public boolean addNewUser(String username, String password, String fullname, boolean isAdmin) throws SQLException {
        result = false;
        refresh();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into User "
                        + "values( "
                        + "?, ?, ?, ? "
                        + ")";
                stm = con.prepareStatement(sql);
                if (stm != null) {
                    stm.setString(1, username);
                    stm.setString(2, password);
                    stm.setString(3, fullname);
                    stm.setBoolean(4, isAdmin);

                    effectedRow = stm.executeUpdate();
                    if (effectedRow > 0) {
                        result = true;
                    }
                }
            }
        } catch (Exception ex) {
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

    public List<User> searchByFullname(String fullname){
        List<User> list = new ArrayList<>();
        refresh();
        try {
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "select username, password, fullname, isAdmin "
                        + "from Users "
                        + "where fullname like ? ";
                stm = con.prepareStatement(sql);
                if(stm != null){
                    stm.setString(1, "%" + fullname + "%");
                    rs = stm.executeQuery();
                    while(rs.next()){
                        User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getBoolean("isAdmin"));
                        list.add(user);
                    }
                }
            }
        } catch (Exception e) {
        }
        return list;
    }
}
