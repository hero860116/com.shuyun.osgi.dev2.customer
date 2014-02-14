package com.shuyun.osgi.dev2.customer.base;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;



public class MybatisTemplateDev implements MybatisInterface{

	//�洢�߳�SqlSession
	private static ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<SqlSession>();

	//��ȡ������¼
	public <T> T selectOne(String statement, T paramObject) {
		 //�ӵ�ǰ�߳��л�ȡ�������
		SqlSession session = sqlSessionThreadLocal.get();


		if (session == null) {

			try {
				session = MybatisInit.sessionFactoryDev.openSession(true);
				return session.selectOne(statement, paramObject);

			} finally {
				session.close();

			}
		} else {
			return session.selectOne(statement, paramObject);
		}
	}

	//��ȡ�б����
	public <T> List<T> selectList(String statement, T paramObject) {
		 //�ӵ�ǰ�߳��л�ȡ�������
		SqlSession session = sqlSessionThreadLocal.get();


		if (session == null) {

			try {
				session = MybatisInit.sessionFactoryDev.openSession(true);
				return session.selectList(statement, paramObject);

			} finally {
				session.close();
			}
		} else {
			return session.selectList(statement, paramObject);
		}
	}

	//����
	public <T> int insert(String statement, T paramObject) {
       return update(statement, paramObject);
	}

	//��������
	public <T> int batchInsert(String statement, List<T> paramObjects) {
       return batchUpdate(statement, paramObjects);
	}

	//ɾ��
	public <T> int delete(String statement, T paramObject) {
       return update(statement, paramObject);
	}

	//����ɾ��
	public <T> int delete(String statement, List<T> paramObjects) {
       return batchUpdate(statement, paramObjects);
	}

	//����
	public <T> int update(String statement, T paramObject) {
        //�ӵ�ǰ�߳��л�ȡ�������
		SqlSession session = sqlSessionThreadLocal.get();


		if (session == null) {

			try {
				session = MybatisInit.sessionFactoryDev.openSession(true);
				return session.update(statement, paramObject);

			} finally {
				session.close();

			}
		} else {
			return session.update(statement, paramObject);
		}
	}

    //��������
	public <T> int batchUpdate(String statement, List<T> paramObject) {
		SqlSession session = sqlSessionThreadLocal.get();

		int result = 0;

		if (session == null) {

			try {
				session = MybatisInit.sessionFactoryDev.openSession(ExecutorType.BATCH, false);

				for (T paramMap : paramObject) {
					result += session.update(statement, paramMap);
				}

				session.commit();

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

	//��ȡsession
	public SqlSession getSqlSession() {
		SqlSession session = sqlSessionThreadLocal.get();

		if (session == null) {
			session = MybatisInit.sessionFactoryDev.openSession(ExecutorType.BATCH, false);
		}

		return session;
	}


	//��ʼ����
	public void begin() {
		SqlSession session = MybatisInit.sessionFactoryDev.openSession(ExecutorType.BATCH, false);
		sqlSessionThreadLocal.set(session);
	}

	//�ύ����
	public void commit() {

        SqlSession session = null;
        try {
            session = sqlSessionThreadLocal.get();

            if (session != null) {
                session.commit();
            }
        } catch (Exception e) {

        } finally {
            if (session != null) {
                session.close();
            }
        }


	}

    @Override
    public void rollback() {
        SqlSession session = null;
        try {
            session = sqlSessionThreadLocal.get();

            if (session != null) {
                session.rollback();
            }
        } catch (Exception e) {

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
