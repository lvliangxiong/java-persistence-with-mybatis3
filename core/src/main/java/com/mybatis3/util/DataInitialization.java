package com.mybatis3.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.sql.Connection;

import static com.mybatis3.util.JdbcUtil.closeAll;
import static com.mybatis3.util.JdbcUtil.getDatabaseConnection;

public class DataInitialization {
    private static Logger logger = LoggerFactory.getLogger(DataInitialization.class);

    public static void initDatabase() {
        Connection connection = null;
        Reader reader = null;
        try {
            connection = getDatabaseConnection();
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            reader = Resources.getResourceAsReader("sql/data.sql");
            scriptRunner.runScript(reader);
            logger.info("data.sql executed successfully");
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(connection, reader);
        }

    }
}
