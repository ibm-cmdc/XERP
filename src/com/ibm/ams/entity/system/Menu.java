package com.ibm.ams.entity.system;

import java.util.List;

/**
 * 菜单主表
 */
public class Menu {

	private String MENU_ID; // 菜单ID
	private String MENU_NAME; // 菜单名称
	private String MENU_URL; // 菜单URL
	private String PARENT_ID; // 父类菜单
	private String MENU_ORDER; // 菜单顺序
	private String MENU_ICON; // 菜单ICON图标
	private String MENU_TYPE; // 菜单类型
	private String MENU_STATE; // 菜单状态
	private String MPLATFORM; // 系统平台
	private String LAST_UPD_USR;//最后修改时间
	private String LAST_UPD_DT;//最后修改人
	private String VER;//版本号
	private String target;
	
	private Menu parentMenu;
	private List<Menu> subMenu;
	private boolean hasMenu = false;


	public String getMENU_ID() {
		return MENU_ID;
	}

	public void setMENU_ID(String mENU_ID) {
		this.MENU_ID = mENU_ID;
	}

	public String getMENU_NAME() {
		return MENU_NAME;
	}

	public void setMENU_NAME(String mENU_NAME) {
		this.MENU_NAME = mENU_NAME;
	}

	public String getMENU_URL() {
		return MENU_URL;
	}

	public void setMENU_URL(String mENU_URL) {
		this.MENU_URL = mENU_URL;
	}

	public String getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(String pARENT_ID) {
		this.PARENT_ID = pARENT_ID;
	}

	public String getMENU_ORDER() {
		return MENU_ORDER;
	}

	public void setMENU_ORDER(String mENU_ORDER) {
		this.MENU_ORDER = mENU_ORDER;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}

	public boolean isHasMenu() {
		return hasMenu;
	}

	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMENU_ICON() {
		return MENU_ICON;
	}

	public void setMENU_ICON(String mENU_ICON) {
		this.MENU_ICON = mENU_ICON;
	}

	public String getMENU_TYPE() {
		return MENU_TYPE;
	}

	public void setMENU_TYPE(String mENU_TYPE) {
		this.MENU_TYPE = mENU_TYPE;
	}

	public String getMENU_STATE() {
		return MENU_STATE;
	}

	public void setMENU_STATE(String mENU_STATE) {
		this.MENU_STATE = mENU_STATE;
	}

	public String getMPLATFORM() {
		return MPLATFORM;
	}

	public void setMPLATFORM(String mPLATFORM) {
		this.MPLATFORM = mPLATFORM;
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
