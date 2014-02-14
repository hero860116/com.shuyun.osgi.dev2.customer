package com.shuyun.mybatis.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MybatisTemplateImpl implements MybatisTemplate{

    private Logger logger = LoggerFactory.getLogger(getClass());

	//存储线程SqlSession
	private static ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<SqlSession>();

    private MySessionFactoryBean mySessionFactoryBean;

	//获取单条记录
	public <T> T selectOne(String statement, T paramObject) {
		 //从当前线程中获取事务对象
		SqlSession session = sqlSessionThreadLocal.get();


		if (session == null) {

			try {
                try {
                    session = mySessionFactoryBean.getSqlSessionFactory().openSession(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return session.selectOne(statement, paramObject);

			} finally {
				session.close();

			}
		} else {
			return session.selectOne(statement, paramObject);
		}
	}

	//获取列表数据
	public <T> List<T> selectList(String statement, T paramObject) {
		 //从当前线程中获取事务对象
		SqlSession session = sqlSessionThreadLocal.get();


		if (session == null) {

			try {
				session = mySessionFactoryBean.getSqlSessionFactory().openSession(true);
				return session.selectList(statement, paramObject);

			} catch (Exception e) {
                e.printStackTrace();
            } finally {
				session.close();
			}
		} else {
			return session.selectList(statement, paramObject);
		}

        return new ArrayList<T>();
	}

	//插入
	public <T> int insert(String statement, T paramObject) {
       return update(statement, paramObject);
	}

	//批量插入
	public <T> int batchInsert(String statement, List<T> paramObjects) {
       return batchUpdate(statement, paramObjects);
	}

	//删除
	public <T> int delete(String statement, T paramObject) {
       return update(statement, paramObject);
	}

	//批量删除
	public <T> int delete(String statement, List<T> paramObjects) {
       return batchUpdate(statement, paramObjects);
	}

	//更新
	public <T> int update(String statement, T paramObject) {
        //从当前线程中获取事务对象
		SqlSession session = sqlSessionThreadLocal.get();


		if (session == null) {

			try {
				session = mySessionFactoryBean.getSqlSessionFactory().openSession(true);
				return session.update(statement, paramObject);

			} catch (Exception e) {
                e.printStackTrace();
            } finally {
				session.close();

			}
		} else {
			return session.update(statement, paramObject);
		}

        return -1;
	}

    //批量更新
	public <T> int batchUpdate(String statement, List<T> paramObject) {
		SqlSession session = sqlSessionThreadLocal.get();

		int result = 0;

		if (session == null) {

			try {
				session = mySessionFactoryBean.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

				for (T paramMap : paramObject) {
					result += session.update(statement, paramMap);
				}

				session.commit();

			} catch (Exception e) {
                e.printStackTrace();
            } finally {
				session.close();

			}
		} else {
			for (T paramMap : paramObject) {
				result += session.update(statement, paramMap);
			}
		}

		return result;
	}

	//获取session
	public SqlSession getSqlSession() {
		SqlSession session = sqlSessionThreadLocal.get();

		if (session == null) {
            try {
                session = mySessionFactoryBean.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

		return session;
	}


	//开始事务
	public void begin() {
        SqlSession session = null;
        try {
            session = mySessionFactoryBean.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSessionThreadLocal.set(session);
	}

	//提交事务
	public void commit() {

		SqlSession session = sqlSessionThreadLocal.get();

		if (session != null) {
			session.commit();
		}

	}

    public MySessionFactoryBean getMySessionFactoryBean() {
        return mySessionFactoryBean;
    }

    public void setMySessionFactoryBean(MySessionFactoryBean mySessionFactoryBean) {
        logger.info("setMySessionFactoryBean:{}",mySessionFactoryBean);
        this.mySessionFactoryBean = mySessionFactoryBean;
    }
}
