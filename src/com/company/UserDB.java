package com.company;

import com.company.DataBase;
import com.company.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB {

    public static boolean addUser(User user){
        String sql = "INSERT INTO users(username, email, password, date_of_registration) " +
                    "VALUES(?, ?, ?, now())";
        int result = 0;

        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            result = stmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result > 0;
    }

    public static User getUserByUsername(String username){
        String sql = "SELECT * FROM users " +
                    "WHERE username = ?";
        User user = null;

        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, username);

            try(ResultSet rs = stmt.executeQuery()){

                if (rs.next()){
                    user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(username);
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setDateOfRegistration(rs.getDate("date_of_registration"));
                }
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public static boolean addUserLog(User user, String loginResult){
        String sql = "INSERT INTO user_logs(user_id, login_time, login_result) " +
                    "VALUES(?, now(), ?)";
        int result = 0;

        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, user.getId());
            stmt.setString(2, loginResult);

            result = stmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result > 0;
    }
}
