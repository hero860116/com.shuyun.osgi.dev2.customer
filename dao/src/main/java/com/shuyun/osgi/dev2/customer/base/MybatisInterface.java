package com.shuyun.osgi.dev2.customer.base;;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public interface MybatisInterface {
	// ��ȡ������¼
	public <T> T selectOne(String statement, T paramObject);

	// ��ȡ�б����
	public <T> List<T> selectList(String statement, T paramObject);

	// ����
	public <T> int insert(String statement, T paramObject);

	// ��������
	public <T> int batchInsert(String statement, List<T> paramObjects);

	// ɾ��
	public <T> int delete(String statement, T paramObject);

	// ����ɾ��
	public <T> int delete(String statement, List<T> paramObjects);

	// ����
	public <T> int update(String statement, T paramObject);

	// ��������
	public <T> int batchUpdate(String statement, List<T> paramObject);

	// ��ȡsession
	public SqlSession getSqlSession();

	// ��ʼ����
	public void begin();

	// �ύ����
	public void commit();

    public void rollback();

}
