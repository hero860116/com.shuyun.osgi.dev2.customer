package com.shuyun.osgi.dev2.customer.dao.impl;

import com.shuyun.osgi.dev2.customer.base.MybatisTemplate;
import com.shuyun.osgi.dev2.customer.dao.AddressDAO;
import com.shuyun.osgi.dev2.customer.dataobject.AddressDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: weilin.li
 * Date: 14-2-13
 * Time: 下午6:41
 */
public class AddressDAOImpl implements AddressDAO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MybatisTemplate mybatisTemplate;

    @Override
    public void saveAddress(AddressDO addressDO) {

        long time = -System.currentTimeMillis();
        logger.info("##################  saveAddress start");


        mybatisTemplate.insert("address.saveAddress", addressDO);

         time += System.currentTimeMillis();

        logger.info("******************* saveAddress end, ... cost time:{}", time);
    }

    public void setMybatisTemplate(MybatisTemplate mybatisTemplate) {
        this.mybatisTemplate = mybatisTemplate;
    }
}
