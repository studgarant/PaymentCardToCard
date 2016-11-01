package com.cahek.ws;

import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.*;

public class ConnectionDBTest {

    @Test
    public void connect() throws Exception {
        Connection connection = null;

        connection = (Connection) ConnectionDB.getConnection();

        assertNotNull(connection);
        assertTrue(connection.isValid(10));
        connection.close();
    }
}