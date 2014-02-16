package com.shuyun.osgi.dev2.customer.dao;

import com.shuyun.osgi.dev2.customer.dataobject.AddressDO;
import com.shuyun.osgi.dev2.customer.dataobject.UserDO;

/**
 * User: weilin.li
 * Date: 14-2-13
 * Time: 下午6:28
 */
public interface AddressDAO {
      void saveAddress(AddressDO addressDO);
}
