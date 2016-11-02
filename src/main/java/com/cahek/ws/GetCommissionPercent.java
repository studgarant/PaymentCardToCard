package com.cahek.ws;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Get commission percent from DB
 */
public class GetCommissionPercent {

    final static Logger logger = Logger.getLogger(SaveTransaction.class);

    /**
     * Get credit card brand and currency and return commission percent from DB
     *
     * @param cardBrand    credit card brand
     * @param cardCurrency credit card currency
     * @return commission percent from DB
     */
    public BigDecimal ComissionGetFromDB(CardBrand cardBrand, Currency cardCurrency) {
        String QUERYSELECT = "SELECT value FROM COMMISSION WHERE brand=? AND currency=?";
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
            logger.error("Get commission percent from DB: " + e.toString());
        }

        return commission;
    }

}
