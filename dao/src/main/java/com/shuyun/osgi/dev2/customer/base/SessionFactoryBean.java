package com.shuyun.osgi.dev2.customer.base;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * User: weilin.li
 * Date: 14-2-16
 * Time: 下午4:05
 */
public class SessionFactoryBean{
    private DataSource dataSource;

    private SqlSessionFactory sqlSessionFactory;

    private List<String> mappers;

    public void afterPropertiesSet() throws IOException {
        String resource = "mybatis/mybatis-config.xml";

        InputStream reader = getClass().getClassLoader().getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        Configuration configuration = sqlSessionFactory.getConfiguration();

        for (String mapper : mappers) {
            URL url = getClass().getClassLoader().getResource(mapper);
            new XMLMapperBuilder(url.openStream(), configuration, url.toString(), configuration.getSqlFragments()).parse();

        }
      /*  URL url = Thread.currentThread().getContextClassLoader().getResource("mybatis/mappers/user.xml");
        new XMLMapperBuilder(url.openStream(), configuration, url.toString(), configuration.getSqlFragments()).parse();

        URL url1 = Thread.currentThread().getContextClassLoader().getResource("mybatis/mappers/address.xml");
        new XMLMapperBuilder(url1.openStream(), configuration, url1.toString(), configuration.getSqlFragments()).parse();
*/
        TransactionFactory transactionFactory = new
                JdbcTransactionFactory();
        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        sqlSessionFactory.getConfiguration().setEnvironment(environment);
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getMappers() {
        return mappers;
    }

    public void setMappers(List<String> mappers) {
        this.mappers = mappers;
    }
}
