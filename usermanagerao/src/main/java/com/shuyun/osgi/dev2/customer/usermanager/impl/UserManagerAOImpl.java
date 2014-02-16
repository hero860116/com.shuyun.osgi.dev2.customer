package com.shuyun.osgi.dev2.customer.usermanager.impl;

import com.shuyun.osgi.dev2.customer.dao.UserDAO;
import com.shuyun.osgi.dev2.customer.dataobject.UserDO;
import com.shuyun.osgi.dev2.customer.transaction.BaseTransaction;
import com.shuyun.osgi.dev2.customer.transaction.TransactionExec;
import com.shuyun.osgi.dev2.customer.usermanager.UserManagerAO;

/**
 * User: weilin.li
 * Date: 14-2-14
 * Time: 下午4:12
 */
public class UserManagerAOImpl extends BaseTransaction implements UserManagerAO {

    private UserDAO userDAO;

    @Override
    public void saveUser(final UserDO userDO) {

        exeTran(new TransactionExec() {
            @Override
            public void exec() throws Exception {
                userDO.setName("taobao_777_1111_" + userDO.getName());
                userDAO.saveUser(userDO);
            }
        });

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
