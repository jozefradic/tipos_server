package Tipos_server.db;

import Tipos_server.Registration;
import Tipos_server.User;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQL {
    private Connection conn;
    private String driver = "com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/tipos";
    private String username="root";
    private String password="";

    public User getUser(String username, String password){
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);

            String query = "SELECT * from users where login like ? and password like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                User user=new User(rs.getString("firstname"),rs.getString("lastname"),rs.getString("login"),rs.getString("email"));
                query = "UPDATE tokens SET token=? WHERE idu=?";
                ps = conn.prepareStatement(query);
                ps.setString(1, user.getToken());
                ps.setInt(2,rs.getInt("id"));

                ps.executeUpdate();
                System.out.println(ps);
                return user;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void logout( String token) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);

            String query = "UPDATE tokens SET token=\"\" where token like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,token);
            System.out.println(ps);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkIfEmailOrLoginExist(String login, String email) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);

            String query = "SELECT count(*) as num FROM users WHERE login like ? OR email like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getInt("num") == 0)
                return false;
            else
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

        public void insertNewUserIntoDb(Registration registration){
            try{
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url, this.username, this.password);
                String query = "INSERT INTO users(firstname, lastname, email, login, password) "+
                        "VALUES (?,?,?,?,?";
                PreparedStatement ps= conn.prepareStatement(query);
                ps.setString(1,registration.firstname);
                ps.setString(2,registration.lastname);
                ps.setString(3,registration.email);
                ps.setString(4,registration.login);
                ps.setString(5,registration.password);

            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public boolean checkLogin(String login) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);

            String query = "SELECT * FROM users Where login like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,login);
            ResultSet rs=ps.executeQuery();

            if(rs.next())
                return true;
            else
                return false;

        }catch(Exception e){
            e.printStackTrace();
        }
            return false;
        }

    public boolean checkToken(String token) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);

            String query = "SELECT * FROM tokens Where token like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,token);
            ResultSet rs=ps.executeQuery();

            if(rs.next())
                return true;
            else
                return false;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;

        }
        }


