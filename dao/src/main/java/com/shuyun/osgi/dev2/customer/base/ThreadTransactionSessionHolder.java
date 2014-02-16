package com.shuyun.osgi.dev2.customer.base;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * User: weilin.li
 * Date: 14-2-16
 * Time: 下午4:46
 */
public class ThreadTransactionSessionHolder {
    private boolean requireTransaction;

    Map<String, SqlSession> nameSqlSessionMap = new HashMap<String, SqlSession>();

    public boolean isRequireTransaction() {
        return requireTransaction;
    }

    public void setRequireTransaction(boolean requireTransaction) {
        this.requireTransaction = requireTransaction;
    }

    public Map<String, SqlSession> getNameSqlSessionMap() {
        return nameSqlSessionMap;
    }

    public void setNameSqlSessionMap(Map<String, SqlSession> nameSqlSessionMap) {
        this.nameSqlSessionMap = nameSqlSessionMap;
    }
}
