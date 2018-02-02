package com.ibm.ams.entity.system;

import com.ibm.ams.entity.Page;

/**
 * 系统接口表
 */
public class Intf {
	private String INTF_ID;//接口ID
	private String INTF_NAME;//接口名称
	private String INTF_URL;//接口地址
	private String IPLATFORM;//系统平台
	private String INTF_DESC;//接口描述
	private String LAST_UPD_USR;//最后修改时间
	private String LAST_UPD_DT;//最后修改人
	private String VER;//版本号
	private Page page;			//分页对象
	
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getINTF_ID() {
		return INTF_ID;
	}
	public void setINTF_ID(String iNTF_ID) {
		this.INTF_ID = iNTF_ID;
	}
	public String getINTF_NAME() {
		return INTF_NAME;
	}
	public void setINTF_NAME(String iNTF_NAME) {
		this.INTF_NAME = iNTF_NAME;
	}
	public String getINTF_URL() {
		return INTF_URL;
	}
	public void setINTF_URL(String iNTF_URL) {
		this.INTF_URL = iNTF_URL;
	}
	public String getIPLATFORM() {
		return IPLATFORM;
	}
	public void setIPLATFORM(String iPLATFORM) {
		this.IPLATFORM = iPLATFORM;
	}
	public String getLAST_UPD_USR() {
		return LAST_UPD_USR;
	}
	public void setLAST_UPD_USR(String lAST_UPD_USR) {
		this.LAST_UPD_USR = lAST_UPD_USR;
	}
	public String getLAST_UPD_DT() {
		return LAST_UPD_DT;
	}
	public void setLAST_UPD_DT(String lAST_UPD_DT) {
		this.LAST_UPD_DT = lAST_UPD_DT;
	}
	public String getVER() {
		return VER;
	}
	public void setVER(String vER) {
		this.VER = vER;
	}
	public String getINTF_DESC() {
		return INTF_DESC;
	}
	public void setINTF_DESC(String iNTF_DESC) {
		this.INTF_DESC = iNTF_DESC;
	}
}
