package com.shuyun.test;

import com.shuyun.osgi.dev2.customer.dao.TestDAO;
import com.shuyun.osgi.dev2.customer.dao.UserDAO;
import com.shuyun.osgi.dev2.customer.dao.impl.UserDAOImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;


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


        logger.info("bundle mybatistest start");


         /* 读写绝对路径文件测试 */
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader("c:\\a.txt"));
        try {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append("insert into ECif(a, b, c) values(");
                sb.append(s);
                sb.append(");");
                sb.append("\n");
            }
        } finally {
            in.close();
        }

        PrintWriter out = new PrintWriter(new File("c:\\b.txt"));
        out.print(sb);
        out.close();


        ServiceReference serviceRef = bundleContext.getServiceReference(UserDAO.class);

        if (serviceRef != null) {
            logger.info("UserDAO:{}", serviceRef);

            UserDAO userDAO = (UserDAO)bundleContext.getService(serviceRef);


            logger.info("serviceReference---getName:{}", userDAO);
        }

        serviceRef = bundleContext.getServiceReference(TestDAO.class);

        if (serviceRef != null) {
            logger.info("UserDAO:{}", serviceRef);

            TestDAO userDAO = (TestDAO)bundleContext.getService(serviceRef);


            logger.info("serviceReference---getName:{}", userDAO);
        }
/*
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

        serviceRef = bundleContext.getServiceReference(MybatisTemplate.class);

        if (serviceRef != null) {
            logger.info("serviceReference:{}", serviceRef);

            MybatisTemplate userAO = (MybatisTemplate)bundleContext.getService(serviceRef);


            logger.info("MybatisTemplate---getName:{}", userAO);
        }*/



    }

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
