package com.cahek.ws;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Check and create table in DB
 */
public class CheckTable {

    final static Logger logger = Logger.getLogger(SaveTransaction.class);

    String QUERYCREATECOMMISSION = "CREATE TABLE IF NOT EXISTS COMMISSION (id int PRIMARY KEY,  brand VARCHAR(20),  " +
            "currency VARCHAR(3),  value DECIMAL(5,2));";

    String QUERYCREATETRANSACTION = "CREATE TABLE IF NOT EXISTS TRANSACTION (id int PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, cardfrom VARCHAR(100), cardto VARCHAR(100), " +
            "currency VARCHAR(3),  amount DECIMAL(12,2), commission DECIMAL(10,2));";

    /**
     * Create tables if not exists
     */
    public void CheckTable() {

        try (Connection con = (Connection) ConnectionDB.getConnection();
             Statement statement = con.createStatement()) {

            statement.executeUpdate(QUERYCREATECOMMISSION);
            statement.executeUpdate(QUERYCREATETRANSACTION);

        } catch (SQLException e) {
            logger.error("Check tables in DB: " + e.toString());
        }

    }
}
