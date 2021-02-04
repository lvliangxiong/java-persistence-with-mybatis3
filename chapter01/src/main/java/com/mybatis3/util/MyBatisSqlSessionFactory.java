package com.mybatis3.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Siva
 */
public class MyBatisSqlSessionFactory {
    private static volatile SqlSessionFactory sqlSessionFactory;

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            synchronized (MyBatisSqlSessionFactory.class) {
                if (sqlSessionFactory == null) {
                    // 实例化
                    try (InputStream in = Resources.getResourceAsStream("mybatis-config.xml");) {
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
                    } catch (IOException e) {
                        throw new RuntimeException(e.getCause());
                    }
                }
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        return getSqlSessionFactory().openSession();
    }
}
