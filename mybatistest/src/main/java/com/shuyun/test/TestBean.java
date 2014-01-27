package com.shuyun.test;

import com.shuyun.mybatis1.ServiceAO;
import com.shuyun.mybatis1.UserAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: weilin.li
 * Date: 14-1-24
 * Time: 下午4:52
 */
public class TestBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserAO userAO;

    private ServiceAO serviceAO;

    public void init() {
        logger.info("TestBean: init start2 .................");

        logger.info("testUserAO:{}", userAO.getUserName(1l));
        logger.info("testServiceAO:{}", serviceAO.getName());
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
}
