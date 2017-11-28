package com.lisj.mail;

import com.github.pagehelper.PageHelper;
import com.lisj.mail.entity.MstDict;
import com.lisj.mail.mapper.MstDictMapper;

import com.lisj.mail.service.MstDictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

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


	/**
	 *
	 */
	@Autowired
	private MstDictMapper mstDictMapper;
	@Test
	public void test() throws Exception{
		PageHelper.startPage(1,2);
	List<MstDict> list= mstDictMapper.selectAll();
		for (MstDict mstDict :list){
		System.out.println(mstDict.getName());
	}
}
	@Autowired
	private MstDictService mstDictService;

	@Test
	public void test2() throws Exception{

		List<MstDict> list= mstDictMapper.findByStatus("1");
		for (MstDict mstDict :list){
			System.out.println(mstDict.getName());
		}
	}

}
