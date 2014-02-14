package com.shuyun.osgi.dev2.customer.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisInit {
	public static SqlSessionFactory sessionFactoryDev;

	public void init() throws IOException {



		Enumeration<URL> url2 = Thread.currentThread().getContextClassLoader().getResources("mybatis/mappers/");

		//URL urll = Thread.currentThread().getContextClassLoader().getResource("mybatis/mappers/");
		//System.out.println(FileLocator.toFileURL(urll));

		String resource = "mybatis/mybatis-config.xml";

        InputStream reader = getClass().getClassLoader().getResourceAsStream(resource);
		sessionFactoryDev = new SqlSessionFactoryBuilder().build(reader, "development");

        URL url = Thread.currentThread().getContextClassLoader().getResource("mybatis/mappers/user.xml");
        Configuration configuration = sessionFactoryDev.getConfiguration();
        new XMLMapperBuilder(url.openStream(), configuration, url.toString(), configuration.getSqlFragments()).parse();
/*
		while (url2.hasMoreElements()) {
			URL url = url2.nextElement();

			File file = new File(url.getFile());

			File flie = Resources.getResourceAsFile(Thread.currentThread().getContextClassLoader(), "/mybatis/mappers/");

			File[] files = flie.listFiles();



			Configuration configuration = sessionFactoryDev.getConfiguration();
			new XMLMapperBuilder(url.openStream(), configuration,
					url.toString(),
				      configuration.getSqlFragments()).parse();

			reader = Resources.getResourceAsReader(resource);
		}



		reader = Resources.getResourceAsReader(resource);
		sessionFactoryTest = new SqlSessionFactoryBuilder().build(reader, "test");
		Configuration configuration = sessionFactoryTest.getConfiguration();
		while (url2.hasMoreElements()) {
			URL url = url2.nextElement();

			new XMLMapperBuilder(url.openStream(), configuration,
					url.toString(),
				      configuration.getSqlFragments()).parse();

			reader = Resources.getResourceAsReader(resource);
		}*/

		//URL url1 = Thread.currentThread().getContextClassLoader().getResource("mybatis/mappers/synchronToInfoBright.xml");

		/*new XMLMapperBuilder(url1.openStream(), configuration,
				url1.toString(),
			      configuration.getSqlFragments()).parse();*/

/*		reader = Resources.getResourceAsReader(resource);
		sessionFactoryTest = new SqlSessionFactoryBuilder().build(reader, "test");*/

		/*configuration = sessionFactoryTest.getConfiguration();
		new XMLMapperBuilder(url.openStream(), configuration, url.toString(), configuration.getSqlFragments()).parse();

		new XMLMapperBuilder(url1.openStream(), configuration,
				url1.toString(),
			      configuration.getSqlFragments()).parse();*/
	}
}
