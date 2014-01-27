package com.shuyun.mybatis1;

public class UserAOImpl1 implements UserAO{
    private UserDO userDO;

	public String getUserName(Long id) {
        if (userDO != null) {
            return userDO.getEmail();
        }

		return "lihaihong";
	}

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }
}
