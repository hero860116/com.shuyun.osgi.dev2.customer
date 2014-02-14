package com.shuyun.osgi.dev2.customer.dao.impl;

import com.shuyun.osgi.dev2.customer.base.MybatisTemplateDev;
import com.shuyun.osgi.dev2.customer.dao.UserDAO;
import com.shuyun.osgi.dev2.customer.dataobject.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: weilin.li
 * Date: 14-2-13
 * Time: 下午6:41
 */
public class UserDAOImpl  extends MybatisTemplateDev implements UserDAO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void saveUser(UserDO userDO) {

        long time = -System.currentTimeMillis();
        logger.info("##################  saveUser start");

           insert("user.saveUser", userDO);

         time += System.currentTimeMillis();

        logger.info("******************* saveuser end, ... cost time:{}", time);
    }
}
