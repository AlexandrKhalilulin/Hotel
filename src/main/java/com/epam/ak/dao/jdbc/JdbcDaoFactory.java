package com.epam.ak.dao.jdbc;

import com.epam.ak.dao.DaoFactory;
import com.epam.ak.dao.RoomDao;

public class JdbcDaoFactory extends DaoFactory {
    @Override
    public RoomDao newRoomDao() {
        return new JdbcRoomDao();
    }
}
