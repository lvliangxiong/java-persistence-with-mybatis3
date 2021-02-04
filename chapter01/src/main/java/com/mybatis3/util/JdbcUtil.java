package com.mybatis3.util;

import com.mybatis3.services.JdbcStudentService;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is = JdbcStudentService.class.getResourceAsStream("/application.properties")) {
            PROPERTIES.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDatabaseConnection() throws SQLException {
        String driver = PROPERTIES.getProperty("jdbc.driverClassName");
        String url = PROPERTIES.getProperty("jdbc.url");
        String username = PROPERTIES.getProperty("jdbc.username");
        String password = PROPERTIES.getProperty("jdbc.password");
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public static void closeAll(AutoCloseable... acs) {
        for (AutoCloseable ac : acs) {
            if (ac != null) {
                try {
                    ac.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
