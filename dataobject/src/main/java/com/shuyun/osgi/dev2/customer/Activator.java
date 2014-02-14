package com.shuyun.osgi.dev2.customer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        logger.info("bundle dataobject start");

		Activator.context = bundleContext;

        //logger.info("**************** minutes : {}", DateUtils.truncate(new Date(), Calendar.MINUTE));
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
