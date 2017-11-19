package com.transfar.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTests {


	@Resource(name = "masterDataSource")
	private DataSource masterDataSource;

	@Resource(name = "slaveDataSource")
	private DataSource slaveDataSource;
	@Test
	public void contextLoads() throws  Exception {
		Connection c1= masterDataSource.getConnection("root","root");
		System.err.println(c1.getMetaData().getURL());
		Connection c2= slaveDataSource.getConnection("root","root");
		System.err.println(c2.getMetaData().getURL());

	}

}
