package com.mybatis3.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.sql.Connection;

import static com.mybatis3.util.JdbcUtil.closeAll;
import static com.mybatis3.util.JdbcUtil.getDatabaseConnection;

public class DataPopulator {
    private static Logger logger = LoggerFactory.getLogger(DataPopulator.class);

    public static void initDatabase() {
        Connection connection = null;
        Reader reader = null;
        try {
            connection = getDatabaseConnection();
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            reader = Resources.getResourceAsReader("sql/drop_tables.sql");
            scriptRunner.runScript(reader);
            logger.info("drop_tables.sql executed successfully");
            reader = Resources.getResourceAsReader("sql/create_tables.sql");
            scriptRunner.runScript(reader);
            logger.info("create_tables.sql executed successfully");
            reader = Resources.getResourceAsReader("sql/sample_data.sql");
            scriptRunner.runScript(reader);
            logger.info("sample_data.sql executed successfully");
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(connection, reader);
        }

    }
}
