package com.shuyun.osgi.dev2.customer;

import com.shuyun.osgi.dev2.customer.dataobject.UserDO;
import com.shuyun.osgi.dev2.customer.usermanager.impl.UserManagerAOImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        logger.info("bundle usermanager start");

        UserDO userDO = new UserDO();
        userDO.setAge(26);
        userDO.setName("liufei");
        new UserManagerAOImpl().saveUser(userDO);

		Activator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
