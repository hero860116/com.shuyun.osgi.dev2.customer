package com.shuyun.osgi.dev2.customer.dao.impl;

import com.shuyun.osgi.dev2.customer.dao.TestDAO;

/**
 * User: weilin.li
 * Date: 14-2-14
 * Time: 上午10:50
 */
public class TestDAOImpl implements TestDAO {
    @Override
    public String test() {
        return "test";
    }
}
