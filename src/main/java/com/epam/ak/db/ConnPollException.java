package com.epam.ak.db;

public class ConnPollException extends RuntimeException {
    public ConnPollException() {
    }

    public ConnPollException(String message) {
        super(message);
    }

    public ConnPollException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnPollException(Throwable cause) {
        super(cause);
    }
}
