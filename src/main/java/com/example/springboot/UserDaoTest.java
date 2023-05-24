package com.example.springboot;

import com.example.springboot.dao.UserDao;
import com.example.springboot.dao.DaoFactory;
import com.example.springboot.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserDao userDao = new DaoFactory().userDao();

        userDao.add(new User("14", "Hyuk", "qwer"));
        User user = userDao.get("14");
        System.out.println(user.getId() +" " + user.getName() + " " + user.getPassword());
    }
}
