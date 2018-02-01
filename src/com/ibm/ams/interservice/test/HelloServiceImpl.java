package com.ibm.ams.interservice.test;

public class HelloServiceImpl implements HelloService{

	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		//http://localhost:8080/CMDCAMS/services/test/hello1?wsdl
		return "您好"+name;
	}

}
