package com.shuyun.osgi.dev2.customer.transaction;

import com.shuyun.osgi.dev2.customer.base.MybatisTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: weilin.li
 * Date: 14-2-14
 * Time: 下午4:23
 */
public class BaseTransaction {
    Logger logger = LoggerFactory.getLogger(getClass());

     public void exeTran(TransactionExec exec) {

         try {
             MybatisTemplate.begin();
             exec.exec();
             MybatisTemplate.commit();
         } catch (Exception e) {
             MybatisTemplate.rollback();
             logger.warn("BaseTransaction exec error, {}", e);
         }
     }
}
