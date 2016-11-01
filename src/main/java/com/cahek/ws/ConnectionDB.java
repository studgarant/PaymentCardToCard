package com.cahek.ws;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection to DB
 */
public class ConnectionDB {

    final static Logger logger = Logger.getLogger(SaveTransaction.class);

     /**
     * Create and return connection to DB
     * @return connection to DB
     */
    public static Connection getConnection() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("db.properties");

        Connection con = null;
        try {
            Properties props = new Properties();
            props.load(input);
            // load the Driver Class
            Class.forName(props.getProperty("DB_DRIVER_CLASS"));

            // create the connection now
            con = DriverManager.getConnection(props.getProperty("DB_URL"),
                    props.getProperty("DB_USERNAME"),
                    props.getProperty("DB_PASSWORD"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            logger.error("Connect to DB: " + e.toString());
        }
        return con;
    }
}
