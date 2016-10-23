package com.cahek.ws;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetCommissionPercent {

    public float ComissionGetFromDB(CardBrand cardBrand, Currency cardCurrency) {
        String QUERYSELECT = "SELECT value FROM commission WHERE brand='" + cardBrand.toString() + "' AND currency='"
                + cardCurrency.toString() + "'";
        float commission = 0;

        try (Connection con = (Connection) ConnectionDB.getConnection();
                Statement statement = con.createStatement();) {
            ResultSet rs = statement.executeQuery(QUERYSELECT);

            while (rs.next()) {
                commission = rs.getFloat("value");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return commission;
        }
    }

}
