package com.ibm.ams.util;

public class Const {
	
	public static final String PAGE	= "10";				//分页条数
	public static final long TOKEN_EXPIRES_HOUR = 2;    //token超时时间
	
	//接口header
	public static final String AUTHORIZATION = "token";   //header权限标识
	public static final String CURRENT_USER_ID = "userid";  //request当前用户 / header用户标识
	public static final String PLATFORM = "PLATFORM";  //header系统平台标识
	
	
	//接口返回
	public static final String RESULT_CODE = "TYPE";   // 接口返回标识 S,E
	public static final String RESULT_MSG = "MESSAGE"; //接口返回消息
}
