package com.cahek.ws;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Get commission percent from DB
 */
public class GetCommissionPercent {

    /**
     * Get credit card brand and currency and return commission percent from DB
     * @param cardBrand credit card brand
     * @param cardCurrency credit card currency
     * @return commission percent from DB
     */
    public BigDecimal ComissionGetFromDB(CardBrand cardBrand, Currency cardCurrency) {
        String QUERYSELECT = "SELECT value FROM commission WHERE brand=? AND currency=?";
        BigDecimal commission = null;

        try (Connection con = (Connection) ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERYSELECT);) {
            ps.setString(1, cardBrand.toString());
            ps.setString(2, cardCurrency.toString());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                commission = rs.getBigDecimal("value");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commission;
    }

}
