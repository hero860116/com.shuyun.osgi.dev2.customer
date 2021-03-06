package com.shuyun.osgi.dev2.customer.dao.impl;

import com.shuyun.osgi.dev2.customer.base.MybatisTemplate;
import com.shuyun.osgi.dev2.customer.dao.UserDAO;
import com.shuyun.osgi.dev2.customer.dataobject.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * User: weilin.li
 * Date: 14-2-13
 * Time: 下午6:41
 */
public class UserDAOImpl implements UserDAO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MybatisTemplate mybatisTemplate;

    @Override
    public void saveUser(UserDO userDO) {

        long time = -System.currentTimeMillis();
        logger.info("##################  saveUser start");

        if (userDO.getInsertTime() == null) {
            userDO.setInsertTime(new Date());
        }

        mybatisTemplate.insert("user.saveUser", userDO);

         time += System.currentTimeMillis();

        logger.info("******************* saveuser end, ... cost time:{}", time);
    }

    public void setMybatisTemplate(MybatisTemplate mybatisTemplate) {
        this.mybatisTemplate = mybatisTemplate;
    }
}
