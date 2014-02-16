package com.shuyun.osgi.dev2.customer.usermanager.impl;

import com.shuyun.osgi.dev2.customer.dao.AddressDAO;
import com.shuyun.osgi.dev2.customer.dao.UserDAO;
import com.shuyun.osgi.dev2.customer.dataobject.AddressDO;
import com.shuyun.osgi.dev2.customer.dataobject.UserDO;
import com.shuyun.osgi.dev2.customer.transaction.BaseTransaction;
import com.shuyun.osgi.dev2.customer.transaction.TransactionExec;
import com.shuyun.osgi.dev2.customer.usermanager.UserManagerAO;

/**
 * User: weilin.li
 * Date: 14-2-14
 * Time: 下午4:12
 */
public class UserManagerAddressAOImpl extends BaseTransaction implements UserManagerAO {

    private UserDAO userDAO;

    private AddressDAO addressDAO;

    @Override
    public void saveUser(final UserDO userDO) {

        exeTran(new TransactionExec() {
            @Override
            public void exec() throws Exception {
                userDO.setName("taobao_777_1111_" + userDO.getName());
                userDAO.saveUser(userDO);

/*                   if (true) {
                       throw new RuntimeException("test");
                   }*/

                AddressDO addressDO = new AddressDO();
                addressDO.setuName(userDO.getName());
                addressDO.setAddress("test address");

                addressDAO.saveAddress(addressDO);
            }
        });

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }
}
