package com.example.springboot.dao;

import com.example.springboot.db.ConnectionChecker;
import com.example.springboot.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement("Insert Into `spring-db`.`user`(id, name, password) VALUES(?,?,?)");

        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM USER WHERE id = ?");
        pstmt.setString(1,id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"),rs.getString("name"),rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();
        return user;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbHost, dbUser, dbPassword);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.add(new User("13", "Hyuk", "qwer"));
        User user = userDao.get("13");
        System.out.println(user.getId() +" " + user.getName() + " " + user.getPassword());
    }
}
