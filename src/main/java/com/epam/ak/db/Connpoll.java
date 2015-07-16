package com.epam.ak.db;

import com.epam.ak.util.PropertyManager;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

public class Connpoll implements DataSource {
    private int poolSize;
    Logger log = org.slf4j.LoggerFactory.getLogger(Connpoll.class);
    private String driver;
    private String connect;
    private String connectLogin;
    private String connectPassword;
    private int poolSize = 10;
    private BlockingDeque<Connection> connectionsQueue;

    public Connpoll() {
        PropertyManager pm = new PropertyManager("db/db.properties");
        configure(pm);

        connectionsQueue = new ArrayBlockingQueue<Connection>(poolSize);
        try {
            Class.forName(driver);
            log.info(String.valueOf(Class.forName(driver)));
        } catch (ClassNotFoundException e) {
            throw new ConnPollException("ClassNotFoundException, driver not found", e);
        }

    }

    private void configure(PropertyManager pm) {
        driver = pm.getProperty("driver");
        connect = pm.getProperty("connect");
        connectLogin = pm.getProperty("connectLogin");
        connectPassword = pm.getProperty("connectPassword");
        poolSize = Integer.parseInt(pm.getProperty("poolSize"));
    }

    private void configure(PropertyManager pm) {
        driver = pm.getProperty("driver");
        connect = pm.getProperty("connect");
        connectLogin = pm.getProperty("connectLogin");
        connectPassword = pm.getProperty("connectPassword");
        poolSize = Integer.parseInt(pm.getProperty("poolSize"));
    }    public static Connpoll getInstance() {
        return ConnpollHolder.instance;
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionsQueue.take();
        } catch (InterruptedException e) {
            throw new ConnPollException("InterruptedException, no free connections", e);
        }
        return connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    private static class ConnpollHolder {
        private final static Connpoll instance = new Connpoll();
    }

}
