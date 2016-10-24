package com.cahek.ws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SaveCommissionList {

    public SaveCommissionList(List<Commission> commissionList) {

        //CREATE TABLE Commission (id int PRIMARY KEY,  brand VARCHAR(20),  currency VARCHAR(3),  value DECIMAL(5,2);
        String QUERYTRUNCATE = "TRUNCATE TABLE commission";
        String QUERYINSERT = "INSERT INTO commission (ID, BRAND, CURRENCY, VALUE) VALUES (?, ?, ?, ?)";

        try (Connection con = (Connection) ConnectionDB.getConnection();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(QUERYINSERT);) {

            statement.executeUpdate(QUERYTRUNCATE);

            for (Commission c : commissionList) {

                preparedStatement.setInt(1, c.getId());
                preparedStatement.setString(2, c.getBrand().toString());
                preparedStatement.setString(3, c.getCurrency().toString());
                preparedStatement.setFloat(4, c.getValue());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
