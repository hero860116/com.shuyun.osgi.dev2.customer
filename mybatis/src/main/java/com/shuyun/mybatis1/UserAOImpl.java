package com.shuyun.mybatis1;

public class UserAOImpl implements UserAO{
    private UserDO userDO;

	public String getUserName(Long id) {
        if (userDO != null) {
            return userDO.getEmail();
        }

		if (id == 1) {
			return "lwl";
		} else {
			return "lhh";
		}
	}

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

}
