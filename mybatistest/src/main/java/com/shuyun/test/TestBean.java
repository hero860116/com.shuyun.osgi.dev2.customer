package com.shuyun.test;

import com.shuyun.mybatis.base.MybatisTemplate;
import com.shuyun.mybatis1.ServiceAO;
import com.shuyun.mybatis1.UserAO;
import com.shuyun.osgi.dev2.customer.dao.UserDAO;
import com.shuyun.osgi.dev2.customer.dataobject.UserDO;
import com.shuyun.osgi.dev2.customer.usermanager.UserManagerAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: weilin.li
 * Date: 14-1-24
 * Time: 下午4:52
 */
public class TestBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserDAO userDAO;

    private UserManagerAO userManagerAO;


    public void init() {
        logger.info("TestBean: init start2 .................");

        UserDO userDO = new UserDO();
        userDO.setName("lihaihong");
        userDO.setAge(30);

        userDAO.saveUser(userDO);

        userDO.setAge(31);
        userManagerAO.saveUser(userDO);

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserManagerAO getUserManagerAO() {
        return userManagerAO;
    }

    public void setUserManagerAO(UserManagerAO userManagerAO) {
        this.userManagerAO = userManagerAO;
    }
    /*

    private UserAO userAO;

    private ServiceAO serviceAO;

    private MybatisTemplate mybatisTemplate;

    public void init() {
        logger.info("TestBean: init start2 .................");

        logger.info("testUserAO:{}", userAO.getUserName(1l));
        logger.info("testServiceAO:{}", serviceAO.getName());
        logger.info("mybatisTemplate:{}", mybatisTemplate);
    }

    public String test() {
        return  userAO.getUserName(1l);
    }

    public void setUserAO(UserAO userAO) {
        logger.info("TestBean: setUserAO start");
        this.userAO = userAO;
    }

    public void setServiceAO(ServiceAO serviceAO) {
        this.serviceAO = serviceAO;
    }

    public void setMybatisTemplate(MybatisTemplate mybatisTemplate) {
        this.mybatisTemplate = mybatisTemplate;
    }*/
}
