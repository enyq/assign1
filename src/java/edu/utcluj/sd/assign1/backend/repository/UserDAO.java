/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utcluj.sd.assign1.backend.repository;

import edu.utcluj.sd.assign1.backend.model.User;
import edu.utcluj.sd.assign1.backend.util.DateConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Eniko
 */
@Stateless
public class UserDAO {
    private final ConnectionManager cm;
    public UserDAO() {
		this.cm = ConnectionManager.getInstance();
	}
    public List<User> getAllUsers() {
		PreparedStatement selectUsers = null;
		Connection con = null;
		ArrayList<User> result = new ArrayList<>();
		try {
			con = cm.getConnection();
			selectUsers = con
					.prepareStatement("SELECT * FROM users");
			ResultSet rs = selectUsers.executeQuery();
			while (rs.next()) {
				result.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getFloat(7), rs.getDate(8), rs.getString(9)));
            
			}
		} catch (SQLException e) {
			System.out.println("Connection failed at select all");
		} finally {
			cm.returnConnection(con);
		}
		return result;

	}
    
    public User getUserByUsername(String username) {
        PreparedStatement user = null;
		Connection con = null;
		User result = null;
		try {
			con = cm.getConnection();
			user = con
					.prepareStatement("Select * from users where username like ('" + username +"')");
			ResultSet rs = user.executeQuery();
			if (rs.next()) {
				result = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getFloat(7), rs.getDate(8), rs.getString(9));
            
			}
                        else return new User();
		} catch (SQLException e) {
			System.out.println("Connection failed at select one");
		} finally {
			cm.returnConnection(con);
		}
		return result;
    }
   public boolean insertUser(User user) {
        PreparedStatement insertUser = null;
		Connection con = null;
		
		try {
			con = cm.getConnection();
			insertUser = con
					.prepareStatement("insert into users values (?,?,?,?,?,?,?,?,?)");
			 insertUser.setInt(1, user.getId());
            insertUser.setString(2, user.getName());
            insertUser.setString(3, user.getUsername());
            insertUser.setString(4, user.getPassword());
            insertUser.setString(5,  user.getAddress());
            insertUser.setFloat(6,  user.getLatitude());
            insertUser.setFloat(7,  user.getLongitude());
            insertUser.setString(8, DateConverter.convertToString(user.getBirthDate()));
            insertUser.setString(9,  user.getRole());
            return insertUser.executeUpdate()!= 0;
            
		} catch (SQLException e) {
			System.out.println("Connection failed at insert");
		} finally {
			cm.returnConnection(con);
		}
		return false;
    } 
    public boolean updateUser(User user) {
        PreparedStatement updateUser = null;
		Connection con = null;
		
		try {
			con = cm.getConnection();
			updateUser = con
					.prepareStatement("update users set name=?,username=?,password=?,address=?,latitude=?,longitude=?,birth_date=?,role=? where id=?");
			 updateUser.setInt(9, user.getId());
            updateUser.setString(1, user.getName());
            updateUser.setString(2, user.getUsername());
            updateUser.setString(3, user.getPassword());
            updateUser.setString(4,  user.getAddress());
            updateUser.setFloat(5,  user.getLatitude());
            updateUser.setFloat(6,  user.getLongitude());
            updateUser.setString(7, DateConverter.convertToString(user.getBirthDate()));
            updateUser.setString(8,  user.getRole());
            return updateUser.executeUpdate()!= 0;
            
		} catch (SQLException e) {
                    e.printStackTrace();
			System.out.println("Connection failed at update ");
		} finally {
			cm.returnConnection(con);
		}
		return false;
    } 
    public boolean deleteUser(User user) { 
         PreparedStatement deleteUser = null;
		Connection con = null;
		
		try {
			con = cm.getConnection();
			deleteUser = con
					.prepareStatement("delete from users where id=?");
                        deleteUser.setInt(1, user.getId());
                      
             return deleteUser.executeUpdate() != 0;
                }catch (SQLException e) {
			System.out.println("Connection failed at delete");
		} finally {
			cm.returnConnection(con);
		}
         return false;       
    } 
}
