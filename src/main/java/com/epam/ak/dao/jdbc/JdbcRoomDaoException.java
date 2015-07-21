package com.epam.ak.dao.jdbc;

public class JdbcRoomDaoException extends RuntimeException {
    public JdbcRoomDaoException(Throwable cause) {
        super(cause);
    }

    public JdbcRoomDaoException() {
    }

    public JdbcRoomDaoException(String message) {
        super(message);
    }

    public JdbcRoomDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
