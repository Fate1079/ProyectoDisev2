/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ANDRES FELIPE
 */
public class DataBaseConfing {

  

 private static final String URL = "jdbc:mysql://localhost:3307/proyectoFinal";
    private static final String USER = "root";
    private static final String PASSWORD = "Kurumi107911";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
   public static boolean register(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            String hashedPassword = hashPassword(password);

            ps.setString(1, username);
            ps.setString(2, hashedPassword);

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean login(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPasswordHash = rs.getString("password");
                
                return checkPassword(password, storedPasswordHash);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

      private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
    
 
}
