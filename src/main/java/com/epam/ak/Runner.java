package com.epam.ak;

import com.epam.ak.db.ConnectionPoll;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Runner {
    static org.slf4j.Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        ConnectionPoll connectionPoll = ConnectionPoll.getInstance();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    Connection c = connectionPoll.takeConnection();
                    log.info(String.valueOf(c));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        PreparedStatement ps = c.prepareStatement("INSERT INTO ROOM SET BEDS_COUNT = 2, APARTMENT_TYPE = 12");
                        ps.execute();
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    connectionPoll.releaseConnection(c);

                }
            }
        };
        Thread[] threads = new Thread[9];
        for (int i = 0; i < 9; i++) { threads[i] = new Thread(runnable);
            threads[i].start();
        }
        



    }
}
