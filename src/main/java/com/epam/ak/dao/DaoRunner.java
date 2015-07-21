package com.epam.ak.dao;

import com.epam.ak.db.ConnectionPoll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoRunner {
    static Logger log = LoggerFactory.getLogger(DaoRunner.class);

    public static void main(String[] args) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        RoomDao roomDao = daoFactory.newRoomDao();

        ConnectionPoll connectionPoll = ConnectionPoll.getInstance();
    }
}
