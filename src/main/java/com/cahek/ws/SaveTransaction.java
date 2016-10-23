package com.cahek.ws;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveTransaction {

    public SaveTransaction(Transaction transaction) {

        //CREATE TABLE transaction (id int PRIMARY KEY NOT NULL AUTO_INCREMENT, date TIMESTAMP SET DEFAULT CURRENT_TIMESTAMP,
        //cardfrom VARCHAR(100), cardto VARCHAR(100), currency VARCHAR(3),  amount DECIMAL(12,2), commission DECIMAL(10,2));
        String QUERYINSERT = "INSERT INTO transaction (CARDFROM, CARDTO, CURRENCY, AMOUNT, COMMISSION) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = (Connection) ConnectionDB.getConnection();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(QUERYINSERT);) {

            Gson gson = new Gson();

            preparedStatement.setString(1, gson.toJson(transaction.getCardFrom()));
            preparedStatement.setString(2, gson.toJson(transaction.getCardTo()));
            preparedStatement.setString(3, transaction.getCurrency().toString());
            preparedStatement.setFloat(4, transaction.getAmount());
            preparedStatement.setFloat(5, transaction.getCommission());
            preparedStatement.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
