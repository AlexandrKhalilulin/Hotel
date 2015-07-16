package com.epam.ak.db;

import com.epam.ak.util.PropertyManager;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connpoll {
    Logger log = org.slf4j.LoggerFactory.getLogger(Connpoll.class);
    private String driver;
    private String connect;
    private String connectLogin;
    private String connectPassword;
    Connection connection;

    public Connpoll() {
        PropertyManager pm = new PropertyManager("db/db.properties");
        configure(pm);
        try {
            Class.forName(driver);
            log.info(String.valueOf(Class.forName(driver)));
        } catch (ClassNotFoundException e) {
            throw new ConnPollException("ClassNotFoundException", e);
        }
        try {
            connection = DriverManager.getConnection(connect, connectLogin, connectPassword);
        } catch (SQLException e) {
            throw new ConnPollException("SQLException", e);
        }
    }

    private void configure(PropertyManager pm) {
        driver = pm.getProperty("driver");
        connect = pm.getProperty("connect");
        connectLogin = pm.getProperty("connectLogin");
        connectPassword = pm.getProperty("connectPassword");
    }

    public static Connpoll getInstance() {
        return ConnpollHolder.instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private static class ConnpollHolder {
        private final static Connpoll instance = new Connpoll();
    }
}
