package com.example.springboot;

import com.example.springboot.db.ConnectionChecker;

import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker.getConn();
        ConnectionChecker.dropUserTable();
    }
}
