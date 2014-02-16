package com.shuyun.osgi.dev2.customer.dataobject;

import java.util.Date;

/**
 * User: weilin.li
 * Date: 14-2-13
 * Time: 下午6:32
 */
public class AddressDO {
    private String uName;
    private String address;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
