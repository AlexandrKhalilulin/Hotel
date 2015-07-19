package com.epam.ak.dao;

import com.epam.ak.dao.DaoFactory;
import com.epam.ak.dao.RoomDao;
import com.epam.ak.db.ConnectionPoll;
import com.epam.ak.model.Room;

public class DaoRunner {
    public static void main(String[] args) {

        DaoFactory daoFactory = DaoFactory.getInstance();
        RoomDao roomDao = daoFactory.newRoomDao();
        Room room = roomDao.findById(2L);
        roomDao.set(room);
        roomDao.update(room);
        roomDao.merge(room);
        roomDao.insert(room);
        roomDao.removeById(2L);
        ConnectionPoll connectionPoll = ConnectionPoll.getInstance();

    }
}