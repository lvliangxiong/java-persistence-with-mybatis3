package com.mybatis3.util;

import com.mybatis3.domain.Student;
import com.mybatis3.mappers.StudentMapper;
import com.mybatis3.typehandlers.PhoneTypeHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author Siva
 */
public class MyBatisUtil {
    private static volatile SqlSessionFactory xmlSqlSessionFactory;
    private static volatile SqlSessionFactory javaSqlSessionFactory;

    /**
     * 使用 XML 配置文件构建 SqlSessionFactory 对象
     *
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactoryUsingXML() {
        if (xmlSqlSessionFactory == null) {
            synchronized (MyBatisUtil.class) {
                if (xmlSqlSessionFactory == null) {
                    try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml");) {
                        xmlSqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return xmlSqlSessionFactory;
    }

    /**
     * 使用 Java API 构建 SqlSessionFactory 对象
     *
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactoryUsingJavaAPI() {
        if (javaSqlSessionFactory == null) {
            synchronized (MyBatisUtil.class) {
                if (javaSqlSessionFactory == null) {
                    try {
                        // 等效于 XML 配置文件中的 <dataSource> 标签
                        DataSource dataSource = DataSourceFactory.getPooledDataSource();
                        // 等效于 XML 配置文件中的 <transactionManager> 标签
                        TransactionFactory transactionFactory = new JdbcTransactionFactory();
                        // 等效于 XML 配置文件中的 <environment> 标签
                        Environment environment = new Environment("development", transactionFactory, dataSource);
                        // 等效于 XML 配置文件中的 <configuration> 标签
                        Configuration configuration = new Configuration(environment);
                        // 设置别名，等效于 <typeAliases> 标签
                        configuration.getTypeAliasRegistry().registerAlias("student", Student.class);
                        // 设置类型转化器，等效于 <typeHandlers> 标签
                        configuration.getTypeHandlerRegistry().register(PhoneTypeHandler.class);
                        // 设置 Mapper XML 文件或者 Mapper 接口，等效于 <mappers> 标签
                        configuration.addMapper(StudentMapper.class);
                        // 类似于 XML 配置文件的方式，创建 SqlSessionFactory 对象
                        javaSqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return javaSqlSessionFactory;
    }

}
