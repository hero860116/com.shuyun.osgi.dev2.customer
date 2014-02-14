package com.shuyun.osgi.dev2.customer;

import com.shuyun.osgi.dev2.customer.base.MybatisInit;
import com.shuyun.osgi.dev2.customer.base.MybatisTemplateDev;
import com.shuyun.osgi.dev2.customer.dao.impl.UserDAOImpl;
import com.shuyun.osgi.dev2.customer.dataobject.UserDO;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Activator implements BundleActivator {

	private static BundleContext context;

    private Logger logger = LoggerFactory.getLogger(getClass());

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
        logger.info("bundle dao start");

		Activator.context = bundleContext;

        //logger.info("**************** minutes : {}", DateUtils.truncate(new Date(), Calendar.MINUTE));

	new MybatisInit().init();
      /*
        String savaSql = "UPDATE `tb_shop_config` SET `shop_id`='6655', `shop_name`='芝曼专业护发', `catch_index`='10000', `status`='4' WHERE (`shop_id`='6655')";

        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap = new HashMap<String, Object>();
        paraMap.put("execSql", savaSql);

        new MybatisTemplateDev().insert("exec", paraMap);

        UserDO userDO = new UserDO();
        userDO.setAge(27);
        userDO.setName("liweilin");

        new UserDAOImpl().insert("user.saveUser", userDO);*/
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
