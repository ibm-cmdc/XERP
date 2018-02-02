package com.ibm.ams.entity.system;

import com.ibm.ams.entity.Page;

/**
 * 角色主表
 */
public class Role {
	private String ROLE_ID; // 角色ID
	private String ROLE_NAME; // 角色名称
	private String PARENT_ID; // 父角色ID
	private String RIGHTS; // 菜单序列
	private String ADD_QX; // 创建功能
	private String DEL_QX; // 删除功能
	private String EDIT_QX; // 编辑功能
	private String QUR_QX; // 查询功能
	private String RPLATFORM; // 系统平台
	private String ROLE_TYPE; // 角色类型
	private String LAST_UPD_USR;//最后修改时间
	private String LAST_UPD_DT;//最后修改人
	private String VER;//版本号
	private boolean flag;//标示
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private Page page; // 分页对象

	public Page getPage() {
		if (page == null)
			page = new Page();
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getROLE_ID() {
		return ROLE_ID;
	}

	public void setROLE_ID(String rOLE_ID) {
		this.ROLE_ID = rOLE_ID;
	}

	public String getROLE_NAME() {
		return ROLE_NAME;
	}

	public void setROLE_NAME(String rOLE_NAME) {
		this.ROLE_NAME = rOLE_NAME;
	}

	public String getRIGHTS() {
		return RIGHTS;
	}

	public void setRIGHTS(String rIGHTS) {
		this.RIGHTS = rIGHTS;
	}

	public String getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(String pARENT_ID) {
		this.PARENT_ID = pARENT_ID;
	}

	public String getADD_QX() {
		return ADD_QX;
	}

	public void setADD_QX(String aDD_QX) {
		this.ADD_QX = aDD_QX;
	}

	public String getDEL_QX() {
		return DEL_QX;
	}

	public void setDEL_QX(String dEL_QX) {
		this.DEL_QX = dEL_QX;
	}

	public String getEDIT_QX() {
		return EDIT_QX;
	}

	public void setEDIT_QX(String eDIT_QX) {
		this.EDIT_QX = eDIT_QX;
	}

	public String getQUR_QX() {
		return QUR_QX;
	}

	public void setQUR_QX(String qUR_QX) {
		this.QUR_QX = qUR_QX;
	}

	public String getRPLATFORM() {
		return RPLATFORM;
	}

	public void setRPLATFORM(String rPLATFORM) {
		this.RPLATFORM = rPLATFORM;
	}


	public String getROLE_TYPE() {
		return ROLE_TYPE;
	}

	public void setROLE_TYPE(String rOLE_TYPE) {
		this.ROLE_TYPE = rOLE_TYPE;
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

}
