package com.epam.ak.dao;

import com.epam.ak.model.Room;

import java.util.UUID;

public interface RoomDao {
    Room findById(long id);

    Room findByUuid(UUID uuid);

    void set(Room room);

    void update(Room room);

    void merge(Room room);

    Room insert(Room room);

    boolean removeById(long id);

    boolean removeByUuid(UUID uuid);
}
