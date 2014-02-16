package com.shuyun.osgi.dev2.customer.usermanager;

import com.shuyun.osgi.dev2.customer.dataobject.UserDO;

/**
 * User: weilin.li
 * Date: 14-2-14
 * Time: 下午3:57
 */
public interface UserManagerAO {
    void saveUser(UserDO userDO);
}
