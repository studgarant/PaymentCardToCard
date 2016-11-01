package com.cahek.ws;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Save commission to DB
 */
public class SaveCommissionList {

    final static Logger logger = Logger.getLogger(SaveTransaction.class);

    /**
     * Save commission to DB
     * @param commissionList {@link List} {@link Commission}
     */
    public SaveCommissionList(List<Commission> commissionList) {

        String QUERYCREAT = "CREATE TABLE IF NOT EXISTS Commission (id int PRIMARY KEY,  brand VARCHAR(20),  " +
                "currency VARCHAR(3),  value DECIMAL(5,2))";
        String QUERYSELECT = "SELECT ID FROM commission WHERE BRAND=? AND CURRENCY=?";
        String QUERYINSERT = "INSERT INTO commission (ID, BRAND, CURRENCY, VALUE) VALUES (?, ?, ?, ?)";
        String QUERYUPDATE = "UPDATE commission SET VALUE=? WHERE BRAND=? AND CURRENCY=?";

        try (Connection con = (Connection) ConnectionDB.getConnection();
                Statement statement = con.createStatement();
                PreparedStatement psInsert = con.prepareStatement(QUERYINSERT);
                PreparedStatement psSelect = con.prepareStatement(QUERYSELECT);
                PreparedStatement psUpdate = con.prepareStatement(QUERYUPDATE)) {

            statement.executeUpdate(QUERYCREAT);

            for (Commission c : commissionList) {

                psSelect.setString(1, c.getBrand().toString());
                psSelect.setString(2, c.getCurrency().toString());
                ResultSet rs = psSelect.executeQuery();

                if (rs.next()) {
                    psUpdate.setBigDecimal(1, c.getValue());
                    psUpdate.setString(2, c.getBrand().toString());
                    psUpdate.setString(3, c.getCurrency().toString());
                    psUpdate.executeUpdate();
                }else {
                    psInsert.setInt(1, c.getId());
                    psInsert.setString(2, c.getBrand().toString());
                    psInsert.setString(3, c.getCurrency().toString());
                    psInsert.setBigDecimal(4, c.getValue());
                    psInsert.executeUpdate();
                }
            }

        } catch (SQLException e) {
            logger.error("Save commission's list to DB: " + e.toString());
        }
    }

}
