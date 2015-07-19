package com.epam.ak.db;

public class ConnectionPollException extends RuntimeException {
    public ConnectionPollException() {
    }

    public ConnectionPollException(String message) {
        super(message);
    }

    public ConnectionPollException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPollException(Throwable cause) {
        super(cause);
    }
}
