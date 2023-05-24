package com.example.springboot.dao;

public class MessageDao {
    ConnectionMaker connectionMaker;

    public MessageDao() {
    }

    public MessageDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
