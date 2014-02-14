package com.shuyun.osgi.dev2.customer.transaction;

import com.shuyun.osgi.dev2.customer.base.MybatisTemplateDev;
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
         MybatisTemplateDev mybatisTemplateDev = new MybatisTemplateDev();

         try {
             mybatisTemplateDev.begin();
             exec.exec();
             mybatisTemplateDev.commit();
         } catch (Exception e) {
             mybatisTemplateDev.rollback();
             logger.warn("BaseTransaction exec error, {}", e);
         }
     }
}
