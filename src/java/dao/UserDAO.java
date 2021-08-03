package dao;

import entity.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends dbcontext.DBContext {

    //Get random salt
    private byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }

    //Encrypt password with Java SHA Hashing by password and salt
    private String encryptPassword(String password, byte[] salt) {
        String encryptedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes 
            byte[] bytes = md.digest(password.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            encryptedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    public java.sql.Date getCurrentDate() {
        Date utilDate = new Date();
        return new java.sql.Date(utilDate.getTime());
    }

    /* Check username and password */
    public User login(String username, String password) {
        try {
            String sql = "SELECT u.*, r.name as rname FROM [user] u  join [role] r on r.id = u.roleID WHERE [username] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String encryptedPassword = rs.getString("password");
                String salt = rs.getString("salt");
                String pass = encryptPassword(password, Base64.getDecoder().decode(salt));

                if (pass.equals(encryptedPassword)) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));

                    user.setRoleId(rs.getInt("roleID"));

                    user.setCreatedDate(rs.getDate("createdDate"));
                    user.setEmail(rs.getString("email"));
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /* Check is email is already existed in the system */
    public boolean checkUserExisted(String username) {
        String sql = "SELECT * FROM [user] WHERE username = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    /* Check is username is already existed in the system */
    public boolean checkEmailExisted(String email) {
        String sql = "SELECT * FROM [user] WHERE email = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    /* Register new account */
    public void register(User user) {
        String sql = "INSERT INTO [User] ([username], [password], [roleID], [email], [createdDate], [salt])\n"
                + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUsername());

            byte[] salt = null;
            try {
                salt = getSalt();
            } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            String encryptedPassword = encryptPassword(user.getPassword(), salt);

            stm.setString(2, encryptedPassword);
            stm.setInt(3, user.getRoleId());
            stm.setString(4, user.getEmail());
            stm.setDate(5, getCurrentDate());
            stm.setString(6, Base64.getEncoder().encodeToString(salt));
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
