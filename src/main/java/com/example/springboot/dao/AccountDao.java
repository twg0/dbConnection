package com.example.springboot.dao;

public class AccountDao {
    ConnectionMaker connectionMaker;

    public AccountDao() {
    }

    public AccountDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
