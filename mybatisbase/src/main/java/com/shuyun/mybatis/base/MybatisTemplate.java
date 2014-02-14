package com.shuyun.mybatis.base;

import org.apache.ibatis.session.SqlSession;

import java.util.List;


public interface MybatisTemplate {


	//获取单条记录
	public <T> T selectOne(String statement, T paramObject);

	//获取列表数据
	public <T> List<T> selectList(String statement, T paramObject) ;

	//插入
	public <T> int insert(String statement, T paramObject) ;

	//批量插入
	public <T> int batchInsert(String statement, List<T> paramObjects);

	//删除
	public <T> int delete(String statement, T paramObject);

	//批量删除
	public <T> int delete(String statement, List<T> paramObjects);

	//更新
	public <T> int update(String statement, T paramObject) ;

    //批量更新
	public <T> int batchUpdate(String statement, List<T> paramObject) ;

	//获取session
	public SqlSession getSqlSession();


	//开始事务
	public void begin();

	//提交事务
	public void commit();
}
