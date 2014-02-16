package com.shuyun.osgi.dev2.customer.base;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class MybatisTemplate {

    private static Logger logger = LoggerFactory.getLogger(MybatisTemplate.class);

    private SessionFactoryBean sessionFactoryBean;

    private static ThreadLocal<ThreadTransactionSessionHolder> sqlSessionThreadLocal = new ThreadLocal<ThreadTransactionSessionHolder>();

    public <T> T selectOne(String statement, T paramObject) {

        SqlSession session = null;
        try {
            session = sessionFactoryBean.getSqlSessionFactory().openSession(true);
            return session.selectOne(statement, paramObject);

        } finally {
            session.close();

        }
    }

    public <T> List<T> selectList(String statement, T paramObject) {

        SqlSession session = null;
        try {
            session = sessionFactoryBean.getSqlSessionFactory().openSession(true);
            return session.selectList(statement, paramObject);

        } finally {
            session.close();
        }
    }

    public <T> int insert(String statement, T paramObject) {
        return update(statement, paramObject);
    }

    public <T> int batchInsert(String statement, List<T> paramObjects) {
        return batchUpdate(statement, paramObjects);
    }

    public <T> int delete(String statement, T paramObject) {
        return update(statement, paramObject);
    }

    public <T> int delete(String statement, List<T> paramObjects) {
        return batchUpdate(statement, paramObjects);
    }

    public <T> int update(String statement, T paramObject) {
        ThreadTransactionSessionHolder threadTransactionSessionHolder = sqlSessionThreadLocal.get();


        SqlSession session = null;
        if (threadTransactionSessionHolder == null || !threadTransactionSessionHolder.isRequireTransaction()) {

            try {
                session = sessionFactoryBean.getSqlSessionFactory().openSession(true);
                return session.update(statement, paramObject);

            } finally {
                session.close();
            }
        } else {
            session = threadTransactionSessionHolder.getNameSqlSessionMap().get(sessionFactoryBean.getDataSource().toString());

            if (session == null) {
                session = sessionFactoryBean.getSqlSessionFactory().openSession(true);
                threadTransactionSessionHolder.getNameSqlSessionMap().put(sessionFactoryBean.getDataSource().toString(), session);
            }

            return session.update(statement, paramObject);
        }
    }

    public <T> int batchUpdate(String statement, List<T> paramObject) {
        ThreadTransactionSessionHolder threadTransactionSessionHolder = sqlSessionThreadLocal.get();

        SqlSession session = null;

        int result = 0;

        if (threadTransactionSessionHolder == null || !threadTransactionSessionHolder.isRequireTransaction()) {

            try {
                session = sessionFactoryBean.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

                for (T paramMap : paramObject) {
                    result += session.update(statement, paramMap);
                }

                session.commit();

            } finally {
                session.close();

            }
        } else {
            session = threadTransactionSessionHolder.getNameSqlSessionMap().get(sessionFactoryBean.getDataSource().toString());

            if (session == null) {
                session = sessionFactoryBean.getSqlSessionFactory().openSession(true);
                threadTransactionSessionHolder.getNameSqlSessionMap().put(sessionFactoryBean.getDataSource().toString(), session);
            }

            for (T paramMap : paramObject) {
                result += session.update(statement, paramMap);
            }
        }

        return result;
    }

    public SqlSession getSqlSession() {

        ThreadTransactionSessionHolder threadTransactionSessionHolder = sqlSessionThreadLocal.get();

        SqlSession session = null;
        if (!threadTransactionSessionHolder.isRequireTransaction()) {
            session = sessionFactoryBean.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        } else {
            session = threadTransactionSessionHolder.getNameSqlSessionMap().get(sessionFactoryBean.getDataSource().toString());
            if (session == null) {
                session = sessionFactoryBean.getSqlSessionFactory().openSession(true);
                threadTransactionSessionHolder.getNameSqlSessionMap().put(sessionFactoryBean.getDataSource().toString(), session);
            }
        }

        return session;
    }


    public static void begin() {

        //
        ThreadTransactionSessionHolder threadTransactionSessionHolder = new ThreadTransactionSessionHolder();
        threadTransactionSessionHolder.setRequireTransaction(true);

        sqlSessionThreadLocal.set(threadTransactionSessionHolder);
    }

    public static void commit() {

        ThreadTransactionSessionHolder threadTransactionSessionHolder = sqlSessionThreadLocal.get();

        Map<String, SqlSession> nameSqlSessionMap = threadTransactionSessionHolder.getNameSqlSessionMap();

        try {
            for (Map.Entry<String, SqlSession> stringSqlSessionEntry : nameSqlSessionMap.entrySet()) {

                SqlSession session = null;
                try {
                    session = stringSqlSessionEntry.getValue();

                    if (session != null) {
                        session.commit();
                    }
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("commit error, {}", e);
        } finally {
            sqlSessionThreadLocal.remove();
        }


    }

    public static void rollback() {

        ThreadTransactionSessionHolder threadTransactionSessionHolder = sqlSessionThreadLocal.get();

        Map<String, SqlSession> nameSqlSessionMap = threadTransactionSessionHolder.getNameSqlSessionMap();

        try {


            for (Map.Entry<String, SqlSession> stringSqlSessionEntry : nameSqlSessionMap.entrySet()) {
                SqlSession session = null;
                try {
                    session = stringSqlSessionEntry.getValue();

                    if (session != null) {
                        session.rollback();
                    }
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
            }

        } catch (Exception e) {
            logger.warn("rollbake error, {}", e);
        } finally {
            sqlSessionThreadLocal.remove();
        }
    }

    public SessionFactoryBean getSessionFactoryBean() {
        return sessionFactoryBean;
    }

    public void setSessionFactoryBean(SessionFactoryBean sessionFactoryBean) {
        this.sessionFactoryBean = sessionFactoryBean;
    }
}
