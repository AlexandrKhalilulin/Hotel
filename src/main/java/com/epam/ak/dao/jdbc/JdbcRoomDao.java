package com.epam.ak.dao.jdbc;

import com.epam.ak.dao.RoomDao;
import com.epam.ak.model.Room;

import java.util.UUID;

public class JdbcRoomDao implements RoomDao {

    public Room findById(long id) {
        return null;
    }

    public Room findByUuid(UUID uuid) {
        return null;
    }

    public void set(Room room) {

    }

    public void update(Room room) {

    }

    public void merge(Room room) {

    }

    public Room insert(Room room) {
        return null;
    }

    public boolean removeById(long id) {
        return false;
    }

    public boolean removeByUuid(UUID uuid) {
        return false;
    }
}
