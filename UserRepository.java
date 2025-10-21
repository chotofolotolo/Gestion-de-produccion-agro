package com.myproyect.miproyect;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserRepository implements RepositoryInterface {
    private Connection conn;
    private PreparedStatement pstmt;
    private ArrayList<User> users;

    public UserRepository() {

        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/api_prueba_1",
                    "folotolo",
                    "1234");
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
        }

        this.users = new ArrayList<>();
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email_user = ?";
        User user = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getString("name_user"), "email_user", "password_user");
            }

            return user;

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }

    }

    public boolean thisEmailExist(String email) {
        String query = "SELECT email_user FROM users WHERE email_user = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.getString("email_user") != null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        User user = null;
        try {
            pstmt = conn.prepareStatement(query);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getString("name_user"), "email_user", "password_user");
                this.users.add(user);
                user = null;
            }

            return this.users;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        String query = "INSERT INTO public.users(name_user,email_user,password_user) VALUES (?,?,?)";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return user;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            return null;
        }

    }

    @Override
    public User deleteUser(User user) {
        String query = "DELETE name_user,email_user,password_user FROM users WHERE email_user = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getEmail());

            if (pstmt.executeUpdate() > 0) {
                return user;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public User updateUser(User user) {
        String query = "UPDATE users SET name_user = ?,email_user = ?,password_user = ? WHERE email_user = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());

            if (pstmt.executeUpdate() > 0) {
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

}
