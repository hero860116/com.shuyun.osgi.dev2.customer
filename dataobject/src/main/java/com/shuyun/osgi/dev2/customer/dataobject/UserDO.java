package com.shuyun.osgi.dev2.customer.dataobject;

import java.util.Date;

/**
 * User: weilin.li
 * Date: 14-2-13
 * Time: 下午6:32
 */
public class UserDO {
    private String name;
    private Integer age;
    private String email;
    private Date insertTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
