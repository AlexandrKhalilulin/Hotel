package com.epam.ak.db;

import com.epam.ak.util.PropertyManager;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPoll implements DataSource {
    Logger log = org.slf4j.LoggerFactory.getLogger(ConnectionPoll.class);
    private String driver;
    private String connect;
    private String connectLogin;
    private String connectPassword;
    private int poolSize;
    private String FileProperties = "db/database.properties";
    private BlockingQueue<Connection> connectionsQueue;

    private ConnectionPoll() {
        PropertyManager pm = new PropertyManager(FileProperties);
        driver = pm.getProperty("driver");
        connect = pm.getProperty("connect");
        connectLogin = pm.getProperty("connectLogin");
        connectPassword = pm.getProperty("connectPassword");
        poolSize = Integer.parseInt(pm.getProperty("poolSize"));
        connectionsQueue = new ArrayBlockingQueue<Connection>(poolSize);
        try {
            Class.forName(driver);
            log.info("Driver is - {}", Class.forName(driver));
        } catch (ClassNotFoundException e) {
            throw new ConnectionPollException("ClassNotFoundException, driver not found", e);
        }
        for (int i = 0; i < poolSize; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(connect, connectLogin, connectPassword);
            } catch (SQLException e) {
                throw new ConnectionPollException("SQLException, cannot create connection", e);
            }
            connectionsQueue.offer(connection);
        }
        log.info("Created {} connections", connectionsQueue.size());

    }

    public static ConnectionPoll getInstance() {
        return ConnectionPollHolder.instance;
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionsQueue.take();
        } catch (InterruptedException e) {
            throw new ConnectionPollException("InterruptedException, no free connections", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                connectionsQueue.offer(connection);
                log.info("Conection added to poll");
            }

        } catch (SQLException e) {
            throw new ConnectionPollException("SQLException, Connection not added to pool", e);
        }
    }

    public void close() {
        if (ConnectionPollHolder.instance != null) {
            ConnectionPollHolder.instance.clearConnectionQueue();
            log.info("Poll connection is empty");
            //question about
            //ConnpollHolder.instance = null;
            //log.info("ConnectionPoll succesfully close");
        }
    }

    private void clearConnectionQueue() {
        Connection connection;
        while ((connection = connectionsQueue.poll()) != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionPollException("SQLException", e);
            }
        }
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

    @Override
    public String toString() {
        return "ConnectionPoll{" +
                "driver='" + driver + '\'' +
                ", connect='" + connect + '\'' +
                ", connectLogin='" + connectLogin + '\'' +
                ", connectPassword='" + connectPassword + '\'' +
                ", poolSize=" + poolSize +
                ", FileProperties='" + FileProperties + '\'' +
                ", connectionsQueue=" + connectionsQueue +
                '}';
    }

    private static class ConnectionPollHolder {
        private final static ConnectionPoll instance = new ConnectionPoll();
    }

}
