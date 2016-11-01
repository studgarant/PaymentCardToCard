package com.cahek.ws;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Save transaction to DB
 */
public class SaveTransaction {

    final static Logger logger = Logger.getLogger(SaveTransaction.class);

    /**
     * Transaction information save to DB
     * @param transaction Transaction information
     */
    public SaveTransaction(Transaction transaction) {

        String QUERYCREAT = "CREATE TABLE  IF NOT EXISTS transaction (id int PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "date TIMESTAMP SET DEFAULT CURRENT_TIMESTAMP, cardfrom VARCHAR(100), cardto VARCHAR(100), " +
                "currency VARCHAR(3),  amount DECIMAL(12,2), commission DECIMAL(10,2))";

        String QUERYINSERT = "INSERT INTO transaction (CARDFROM, CARDTO, CURRENCY, AMOUNT, COMMISSION) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = (Connection) ConnectionDB.getConnection();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(QUERYINSERT);) {

            statement.executeUpdate(QUERYCREAT);

            Gson gson = new Gson();

            preparedStatement.setString(1, gson.toJson(transaction.getCardFrom()));
            preparedStatement.setString(2, gson.toJson(transaction.getCardTo()));
            preparedStatement.setString(3, transaction.getCurrency().toString());
            preparedStatement.setBigDecimal(4, transaction.getAmount());
            preparedStatement.setBigDecimal(5, transaction.getCommission());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Save transaction information: " + e.toString());
        }
    }
}
