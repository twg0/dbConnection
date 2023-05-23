package com.example.springboot.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectionChecker {

    private static Connection connection;
    private static Statement stmt;

    private ConnectionChecker(){}

    public static Connection getConn() throws SQLException, ClassNotFoundException {
        if(connection == null) {
            Map<String, String> env = getenv();
            String dbHost = env.get("DB_HOST");
            String dbUser = env.get("DB_USER");
            String dbPassword = env.get("DB_PASSWORD");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
            stmt = conn.createStatement();
        }
        return connection;
    }

    public static void select() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM `spring-db`.`user`");
        while(rs.next()) {
            String line = rs.getString(1);
            String line2 = rs.getString(2);
            String line3 = rs.getString(3);
            System.out.println(line + " " + line2 + " " + line3);
        }
    }

    public static void createUserTable() throws SQLException {
        stmt.execute("CREATE TABLE `spring-db`.`user` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NOT NULL," +
                "  `password` VARCHAR(45) NOT NULL," +
                "  PRIMARY KEY (`id`));");
        System.out.println("create UserTable");
    }

    public static void createUser(String userName, String phone) throws SQLException {
        stmt.execute("Insert Into `spring-db`.`user`(name,phone) VALUES('" + userName + "','" + phone + "')");
        System.out.println("create user");
    }

    public static void updateUserName(int id, String name) throws SQLException {
        stmt.execute("UPDATE `spring-db`.`user` SET name = '" + name + "' WHERE id = '" + id + "';");
    }

    public static void dropUserTable() throws SQLException {
        stmt.execute("DROP TABLE `spring-db`.`user`");
    }
    public static void deleteAllUser() throws SQLException {
        stmt.execute("DELETE FROM `spring-db`.`user`");
    }

    public static ResultSet getUsers() throws SQLException {
        return stmt.executeQuery("SELECT name FROM `spring-db`.`user`");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//
//        ConnectionChecker.deleteAllUser();
        ConnectionChecker.getConn();
        ConnectionChecker.dropUserTable();
        ConnectionChecker.createUserTable();
//
//        ConnectionChecker.createUser("kyeongrok","1577");
//        ConnectionChecker.createUser("Ronaldo", "1544");
//        ConnectionChecker.createUser("Messi","1566");
//        ConnectionChecker.createUser("Dukbae","1588");
//        ConnectionChecker.updateUserName(2,"newUserName");
//
//        ConnectionChecker.select();
    }
}
