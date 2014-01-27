package com.shuyun.test;

import com.shuyun.mybatis1.ServiceAO;
import com.shuyun.mybatis1.UserAO;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
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
		Activator.context = bundleContext;


        logger.info("########mybatistest************");

        ServiceReference serviceRef = bundleContext.getServiceReference(UserAO.class);

        if (serviceRef != null) {
            logger.info("serviceReference:{}", serviceRef);

            UserAO userAO = (UserAO)bundleContext.getService(serviceRef);


            logger.info("serviceReference---getName:{}", userAO.getUserName(1l));
        }

        serviceRef = bundleContext.getServiceReference(ServiceAO.class);

        if (serviceRef != null) {
            logger.info("serviceReference:{}", serviceRef);

            ServiceAO userAO = (ServiceAO)bundleContext.getService(serviceRef);


            logger.info("serviceReference---getName:{}", userAO.getName());
        }


	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
