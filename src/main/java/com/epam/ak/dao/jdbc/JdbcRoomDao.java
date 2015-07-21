package com.epam.ak.dao.jdbc;

import com.epam.ak.dao.RoomDao;
import com.epam.ak.db.ConnectionPoll;
import com.epam.ak.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JdbcRoomDao implements RoomDao {
    ConnectionPoll connectionPoll = ConnectionPoll.getInstance();

    @Override
    public Room findById(long id) {
        Connection connection = connectionPoll.takeConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT id FROM ROOM WHERE id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            boolean found = rs.next();
            if (!found) {
                return null;
            }
            Room room = new Room();
            room.setBedsCount(rs.getInt("Beds_Count"));
            room.setId(rs.getLong("Id"));
            return room;
        } catch (SQLException e) {
            throw new JdbcRoomDaoException("SQLException", e);
        } finally {
            connectionPoll.releaseConnection(connection);
        }
    }

    @Override
    public Room insert(Room room) {
        Connection connection = connectionPoll.takeConnection();
        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO ROOM (ID, BEDS_COUNT, APARTMENT_TYPE, DAILY_COST) VALUES (DEFAULT, ?, ?, ? )")) {
            ps.setInt(1, room.getBedsCount());
            ps.setObject(2, room.getApartmentType());
            ps.setInt(3, room.getDailyCost());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            room.setId(rs.getLong(1));
            return room;

        } catch (SQLException e) {
            throw new JdbcRoomDaoException("SQLException", e);
        } finally {
            connectionPoll.releaseConnection(connection);
        }
    }

    @Override
    public Room findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public void set(Room room) {

    }

    @Override
    public void update(Room room) {

    }

    @Override
    public void merge(Room room) {

    }

    @Override
    public boolean removeById(long id) {
        return false;
    }

    @Override
    public boolean removeByUuid(UUID uuid) {
        return false;
    }
}
