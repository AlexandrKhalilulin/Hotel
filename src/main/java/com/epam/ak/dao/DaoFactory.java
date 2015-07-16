package com.epam.ak.dao;

import com.epam.ak.dao.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {
    public static DaoFactory getInstance() {
        return new JdbcDaoFactory();
    }

    public abstract RoomDao newRoomDao();
}
