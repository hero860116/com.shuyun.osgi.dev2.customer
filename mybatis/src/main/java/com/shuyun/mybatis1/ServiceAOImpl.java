package com.shuyun.mybatis1;

/**
 * User: weilin.li
 * Date: 14-1-26
 * Time: 下午2:24
 */
public class ServiceAOImpl implements ServiceAO{

    private String name;

    @Override
    public String getName() {
        if (name != null) {
            return name;
        } else {
            return "liweilin";
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
