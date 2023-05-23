package com.example.springboot.dao;

import com.example.springboot.domain.User;

import java.sql.*;

public abstract class UserDao {
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

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException ;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new NUserDao();
        userDao.add(new User("14", "Hyuk", "qwer"));
        User user = userDao.get("13");
        System.out.println(user.getId() +" " + user.getName() + " " + user.getPassword());
    }
}
