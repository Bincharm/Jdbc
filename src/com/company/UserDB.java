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
                    "WHERE username = ? AND coalesce(is_blocked, false) = false";
                    //"WHERE username = ? AND (is_blocked is null or is_blocked = false )";
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

    @SuppressWarnings("Duplicates")
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

    public static int getUserLogsCountByUserIdAndLoginResult(User user, String loginResult){
        String sql = "SELECT COUNT(user_log_id) FROM user_logs " +
                    "WHERE user_id = ? AND login_result = COALESCE(?, login_result)";
        int result = 0;

        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, user.getId());
            stmt.setString(2, loginResult);

            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                result = rs.getInt(1);
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public static boolean setUserIsBlockedByUserIdOrUsername(User user, boolean isBlocked){
        String sql = "UPDATE users \n" +
                "SET is_blocked = ?\n" +
                "WHERE user_id = ? OR username = ?";
        int result = 0;

        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setBoolean(1, isBlocked);
            stmt.setInt(2, user.getId());
            stmt.setString(3, user.getUsername());

            result = stmt.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result > 0;
    }

    @SuppressWarnings("Duplicates")
    public static boolean deleteUserLogsByUserIdAndLoginResult(User user, String loginResult){
        String sql = "DELETE FROM user_logs \n" +
                    "WHERE user_id = ? AND login_result = COALESCE(?, login_result)";
        int result = 0;

        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, user.getId());
            stmt.setString(2, loginResult);

            result = stmt.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result > 0;
    }


}
