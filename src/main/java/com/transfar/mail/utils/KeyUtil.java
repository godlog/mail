package com.transfar.mail.utils;


import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;


/**
 * @description:
 *  <B>系统名称：</B>通用平台<BR>
 * <B>模块名称：</B>通用平台-公共服务<BR>
 * <B>中文类名：</B>KeyUtils<BR>
 * <B>概要说明：</B>主键生成策略-工具类<BR>
 * @author 16519
 * @date 2017/11/18 17:42
 * @version 1.0
 */

public class KeyUtil {


	/**
	 * @description:
	 * <B>方法名称：</B>generatorUUID<BR>
	 * <B>概要说明：</B>主键生成策略<BR>
	 * @author 16519
	 * @date 2017/11/18 17:43
	 * @version 1.0
	 */
	public static String generatorUUID(){
		TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
		return timeBasedGenerator.generate().toString();
	}
	
	
	
}
