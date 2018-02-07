package com.ibm.ams.controller.system;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.ams.controller.base.BaseController;
import com.ibm.ams.entity.system.Bo;
import com.ibm.ams.entity.system.Menu;
import com.ibm.ams.entity.system.Role;
import com.ibm.ams.entity.system.User;
import com.ibm.ams.service.bo.BoManager;
import com.ibm.ams.service.menu.MenuManager;
import com.ibm.ams.service.role.RoleManager;
import com.ibm.ams.service.user.UserManager;
import com.ibm.ams.util.CacheUtil;
import com.ibm.ams.util.Const;
import com.ibm.ams.util.DateUtil;
import com.ibm.ams.util.MapUtil;
import com.ibm.ams.util.PageData;
import com.ibm.ams.util.RightsHelper;
import com.ibm.ams.util.Tools;

import net.sf.json.JSONArray;

@Controller
public class UmeController extends BaseController {
	@Resource
	private UserManager userService;
	@Resource
	private RoleManager roleService;
	@Resource
	private MenuManager menuService;
	@Resource
	private BoManager boService;

	/**
	 * 查询用户信息
	 * 
	 * @param limit
	 *            当前页
	 * @param offset
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUser", method = RequestMethod.GET)
	@ResponseBody
	public String queryUser() throws Exception {
		PageData pd = this.getPageData();
		int limit = Integer.valueOf((String) pd.get("limit"));
		int offset = Integer.valueOf((String) pd.get("offset"));
		JSONObject rspJson = new JSONObject();
		int rowno = (limit - 1) * offset + 1;
		int rownum = limit * offset;
		// 1 2 3
		// 起始条(页码-1)*每页长度+1 1 4 5 8 9 12
		// 结束条limit*offset
		pd.put("rowno", rowno);
		pd.put("rownum", rownum);
		List<User> listUser = userService.queryUserInfo(pd);
		PageData userCount = userService.getUserCount("");
		BigDecimal totalResult = (BigDecimal) userCount.get("userCount");
		if (null == listUser || "".equals(listUser) || null == userCount || "".equals(userCount)) {
			JSONArray arr = JSONArray.fromObject(listUser);
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "查询用户信息失败!");
			rspJson.put("ALLUSER", arr);
			rspJson.put("TOTALRESULT", totalResult);
			return rspJson.toString();
		}

		JSONArray arr = JSONArray.fromObject(listUser);
		rspJson.put(Const.RESULT_CODE, "S");
		rspJson.put(Const.RESULT_MSG, "查询用户信息成功!");
		rspJson.put("ALLUSER", arr);
		rspJson.put("TOTALRESULT", totalResult);
		return rspJson.toString();
	}

	@RequestMapping(value = { "/queryBo" }, method =RequestMethod.GET)
	@ResponseBody
	public String queryBo() throws Exception {
		PageData pd = getPageData();
		JSONObject rspJson = new JSONObject();
		List<Bo> queryBo = boService.queryBo(pd);
		JSONArray arr = JSONArray.fromObject(queryBo);
		if (queryBo != null) {
			rspJson.put("LISTBO", arr);
			rspJson.put("TYPE", "S");
			rspJson.put("MESSAGE", "查询权限信息成功!");
		} else {
			rspJson.put("LISTBO", arr);
			rspJson.put("TYPE", "E");
			rspJson.put("MESSAGE", "查询权限信息失败!");
		}
		return rspJson.toString();
	}

	@RequestMapping(value = "/queryFYBoInfo", method = RequestMethod.GET)
	@ResponseBody
	public String queryFYBoInfo() throws Exception {
		PageData pd = this.getPageData();
		int limit = Integer.valueOf((String) pd.get("limit"));
		int offset = Integer.valueOf((String) pd.get("offset"));
		JSONObject rspJson = new JSONObject();
		int rowno = (limit - 1) * offset + 1;
		int rownum = limit * offset;
		// 1 2 3
		// 起始条(页码-1)*每页长度+1 1 4 5 8 9 12
		// 结束条limit*offset
		pd.put("rowno", rowno);
		pd.put("rownum", rownum);
		List<Bo> queryFYBoInfo = boService.queryFYBoInfo(pd);
		PageData queryBoCount = boService.queryBoCount("");
		BigDecimal totalResult = (BigDecimal) queryBoCount.get("boCount");
		if (null == queryFYBoInfo || "".equals(queryFYBoInfo) || null == queryBoCount || "".equals(queryBoCount)) {
			JSONArray arr = JSONArray.fromObject(queryFYBoInfo);
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "查询权限信息失败!");
			rspJson.put("ALLBO", arr);
			rspJson.put("TOTALRESULT", totalResult);
			return rspJson.toString();
		}

		JSONArray arr = JSONArray.fromObject(queryFYBoInfo);
		rspJson.put(Const.RESULT_CODE, "S");
		rspJson.put(Const.RESULT_MSG, "查询权限信息成功!");
		rspJson.put("ALLBO", arr);
		rspJson.put("TOTALRESULT", totalResult);
		return rspJson.toString();
	}

	/**
	 * 查询bokey信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryBokey", method = RequestMethod.GET)
	@ResponseBody
	public String queryBokey() throws Exception {
		JSONObject rspJson = new JSONObject();
		List<PageData> queryBokey = boService.queryBokey();
		JSONArray arr = JSONArray.fromObject(queryBokey);
		if (null != queryBokey) {
			rspJson.put("LISTBOKEY", arr);
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "查询权限key对象信息成功!");
		} else {
			rspJson.put("LISTBOKEY", arr);
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "查询权限key对象信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 获取类型
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryBoType", method = RequestMethod.GET)
	@ResponseBody
	public String queryBoType() throws Exception {
		JSONObject rspJson = new JSONObject();
		List<PageData> queryBoType = boService.queryBoType();
		JSONArray arr = JSONArray.fromObject(queryBoType);
		if (null != queryBoType) {
			rspJson.put("LISTBOTYPE", arr);
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "查询权限Type对象信息成功!");
		} else {
			rspJson.put("LISTBOTYPE", arr);
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "查询权限Type对象信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 根据用户查询Bo信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryBoByUser", method = RequestMethod.GET)
	@ResponseBody
	public String queryBoByUser() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		List<Bo> queryBoByUidUpa = userService.queryBoByUidUpa(pd);
		JSONArray arr = JSONArray.fromObject(queryBoByUidUpa);
		if (null != queryBoByUidUpa) {
			rspJson.put("LISTBO", arr);
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "查询权限信息成功!");
		} else {
			rspJson.put("LISTBO", arr);
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "查询权限信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 根据Bo信息查询User信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserByBoidAndBP", method = RequestMethod.GET)
	@ResponseBody
	public String queryUserByBoidAndBP() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		List<User> queryUserByBoidAndBP = boService.queryUserByBoidAndBP(pd);
		JSONArray arr = JSONArray.fromObject(queryUserByBoidAndBP);
		if (null != queryUserByBoidAndBP) {
			rspJson.put("LISTUSER", arr);
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "查询用户信息成功!");
		} else {
			rspJson.put("LISTUSER", arr);
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "查询用户信息成功!");
		}
		return rspJson.toString();
	}

	/**
	 * 查询角色信息
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryRole", method = RequestMethod.GET)
	@ResponseBody
	public String queryRole() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		List<Role> listAllRolesByRIdPa = roleService.queryRoleInfo(pd);
		JSONArray arr = JSONArray.fromObject(listAllRolesByRIdPa);
		if (null != listAllRolesByRIdPa && !"".equals(listAllRolesByRIdPa)) {
			int size = listAllRolesByRIdPa.size();
			if (0 < size) {
				rspJson.put(Const.RESULT_CODE, "S");
				rspJson.put("LISTROLEINFO", arr);
				rspJson.put(Const.RESULT_MSG, "查询角色信息成功,共" + size + "!");
			} else {
				rspJson.put(Const.RESULT_CODE, "W");
				rspJson.put("LISTROLEINFO", arr);
				rspJson.put(Const.RESULT_MSG, "查询角色信息成功,共" + size + "!");
			}
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put("LISTROLEINFO", arr);
			rspJson.put(Const.RESULT_MSG, "查询角色信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 根据角色ID(RoleID)查询，菜单缓存选中项
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryRoMeCaByRoId", method = RequestMethod.GET)
	@ResponseBody
	public String queryRoMeCaByRoId() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		PageData roleBean = roleService.findObjectById(pd);
		if (null != roleBean && !"".equals(roleBean)) {
			String rights = (String) roleBean.get("RIGHTS");
			String role_Name = (String) roleBean.get("ROLE_NAME");
			String menu = "";
			if (null != rights) {
				menu = CacheUtil.getMenuListID(rights, "AMSMenuList");
			}
			
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put("ROLE_NAME", role_Name);
			rspJson.put("LISTMENUINFO", menu);
			rspJson.put(Const.RESULT_MSG, "根据角色序号查询菜单信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put("ROLE_NAME", "");
			rspJson.put("LISTMENUINFO", "");
			rspJson.put(Const.RESULT_MSG, "根据角色序号未查询到角色的信息!");
		}
		return rspJson.toString();
	}

	@RequestMapping(value = "/queryMenu", method = RequestMethod.GET)
	@ResponseBody
	public String queryMenu() throws Exception {
		PageData pd = this.getPageData();
		
		JSONObject rspJson = new JSONObject();
		List<Menu> queryMenu = menuService.queryMenu(pd);
		JSONArray arr = JSONArray.fromObject(queryMenu);
		if (null != queryMenu && !"".equals(queryMenu)) {
			int size = queryMenu.size();
			if (0 < size) {
				rspJson.put(Const.RESULT_CODE, "S");
				rspJson.put("LISTMENUINFO", arr);
				rspJson.put(Const.RESULT_MSG, "查询菜单信息成功,共" + size + "!");
			} else {
				rspJson.put(Const.RESULT_CODE, "W");
				rspJson.put("LISTMENUINFO", arr);
				rspJson.put(Const.RESULT_MSG, "查询菜单信息成功,共" + size + "!");
			}
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put("LISTMENUINFO", arr);
			rspJson.put(Const.RESULT_MSG, "查询菜单信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 创建菜单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveMenu", method = RequestMethod.GET)
	@ResponseBody
	public String saveMenu() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		int saveMenu = menuService.saveMenu(pd);
		String   menu_name=(String)pd.get("MENU_NAME");
		String   menu_url=(String)pd.get("MENU_URL");
		String   parent_id=(String)pd.get("PARENT_ID");
		String   mplatform=(String)pd.get("MPLATFORM");
		String   menu_order=(String)pd.get("MENU_ORDER");
		String   menu_icon=(String)pd.get("MENU_ICON");
		String   menu_type=(String)pd.get("MENU_TYPE");
		String   menu_state=(String)pd.get("MENU_STATE");
		String   last_upd_usr=(String)pd.get("LAST_UPD_USR");
		String   last_upd_dt=(String)pd.get("LAST_UPD_DT");
		String   ver=(String)pd.get("VER");
		if (0 < saveMenu) {
			Menu menu = new Menu();
			menu.setMENU_NAME(menu_name);
			menu.setMENU_URL(menu_url);
			menu.setPARENT_ID(parent_id);
			menu.setMPLATFORM(mplatform);
			menu.setMENU_ORDER(menu_order);
			menu.setMENU_ICON(menu_icon);
			menu.setMENU_TYPE(menu_type);
			menu.setMENU_STATE(menu_state);
			menu.setLAST_UPD_USR(last_upd_usr);
			menu.setLAST_UPD_DT(last_upd_dt);
			menu.setVER(ver);
			if (null!=menu) {
				List<Menu> allmenuList = CacheUtil.getCahe("AMSMenuList");
				List<Menu> addListMenu = CacheUtil.getAddListMenu(allmenuList, parent_id, menu);
				if (null != addListMenu) {
					CacheUtil.addCahe(addListMenu, "AMSMenuList");
				}
			}
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "添加菜单名称【" + menu_name + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "添加菜单名称【" + menu_name + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 保存角色信息
	 * 
	 * @param pd
	 * @param rights
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveRole", method = RequestMethod.GET)
	@ResponseBody
	public String saveRole() throws Exception {
		PageData pd = this.getPageData();
		String rights = (String) pd.get("RIGHTS");
		JSONObject rspJson = new JSONObject();
		BigInteger rights_JM = RightsHelper.sumRights(Tools.str2StrArray(rights));
		pd.put("RIGHTS", rights_JM);
		int add = roleService.add(pd);
		String role_name = (String) pd.get("ROLE_NAME");
		if (0 < add) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "添加角色【" + role_name + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "添加角色【" + role_name + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 保存角色信息
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveUser", method = RequestMethod.GET)
	@ResponseBody
	public String saveUser() throws Exception {
		PageData pd = this.getPageData();
		pd.put("UPASSWORD", "abcd1234");// 初始化默认密码
		pd.put("CREATETIME", DateUtil.getStringCurrentDate("yyyy/MM/dd"));// 创建时间
		pd.put("LAST_IP", "");// 登录IP
		pd.put("SECPOLICY", "");// 密码策略
		JSONObject rspJson = new JSONObject();
		String username = (String) pd.get("USERNAME");
		int saveU = userService.saveU(pd);
		if (0 < saveU) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "添加用户【" + username + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "添加用户【" + username + "】信息失败!");
		}
		return rspJson.toString();
	}

	@RequestMapping(value = "/saveUserObject", method = RequestMethod.GET)
	@ResponseBody
	public String saveUserObject(String[] DB_ID) throws Exception {
		PageData pd01 = this.getPageData();
		String USER_ID = (String) pd01.get("USER_ID");
		String BO_TYPE = (String) pd01.get("BO_TYPE");
		PageData pd_dete = new PageData();
//		pd_dete.put("BO_DB_ID", DB_ID);
		pd_dete.put("USER_ID", USER_ID);
		pd_dete.put("BO_TYPE", BO_TYPE);
		userService.deleteAllUB(pd_dete);
		
		List<PageData> list = new ArrayList<PageData>();
		 for (String dbid : DB_ID) {
			 PageData pd02 = new PageData();
			 pd02.put("USER_ID", USER_ID);
			 pd02.put("BO_ID", "");
			 pd02.put("BO_KEY", "");
			 pd02.put("BO_DB_ID", dbid);
			 pd02.put("VER", "");
			 pd02.put("BO_TYPE", BO_TYPE);
			 list.add(pd02);
        }
		JSONObject rspJson = new JSONObject();
		int insertUB = userService.insertBacthUB(list);
		if (0 < insertUB) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "添加用户权限关系信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "添加用户权限关系信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 添加Bo信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveBo", method = RequestMethod.GET)
	@ResponseBody
	public String saveBo() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		int saveU = boService.CreateBo(pd);
		String bo_name = (String) pd.get("BO_NAME");
		if (0 < saveU) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "添加权限【" + bo_name + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "添加权限【" + bo_name + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 更新菜单
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMenu", method = RequestMethod.GET)
	@ResponseBody
	public String updateMenu() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		String menu_id = (String) pd.get("MENU_ID");
		int updateMenu = menuService.updateMenu(pd);
		if (0 < updateMenu) {
			@SuppressWarnings("unchecked")
			Menu map2Bean = MapUtil.map2Bean(pd, Menu.class);
			List<Menu> allmenuList = CacheUtil.getCahe("AMSMenuList");
			List<Menu> upListMenu = CacheUtil.getUpListMenu(allmenuList, menu_id, map2Bean);
			if (null != upListMenu) {
				CacheUtil.addCahe(upListMenu, "AMSMenuList");
			}
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "更新菜单ID【" + menu_id + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "更新菜单ID【" + menu_id + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 更新角色
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateRole", method = RequestMethod.GET)
	@ResponseBody
	public String updateRole() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		String role_id = (String) pd.get("ROLE_ID");
		String rights = (String) pd.get("RIGHTS");
		BigInteger rights_JM = RightsHelper.sumRights(Tools.str2StrArray(rights));
		pd.put("RIGHTS", rights_JM);
		int updateRole = roleService.updateRole(pd);
		if (0 < updateRole) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "更新角色ID【" + role_id + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "更新角色ID【" + role_id + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 更新用户
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	@ResponseBody
	public String updateUser() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		String user_id = (String) pd.get("USER_ID");
		int updateUser = userService.updateUser(pd);
		if (0 < updateUser) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "更新用户ID【" + user_id + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "更新用户ID【" + user_id + "】信息失败!");
		}
		return rspJson.toString();
	}

	@RequestMapping(value = "/updateUAndB", method = RequestMethod.GET)
	@ResponseBody
	public String updateUAndB() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		String user_id = (String) pd.get("USER_ID");
		int updateUserAndBoRelationship = userService.updateUserAndBoRelationship(pd);
		if (0 < updateUserAndBoRelationship) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "更新用户ID【" + user_id + "】权限信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "更新用户ID【" + user_id + "】权限信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 更新权限信息根据db_id 必输项： DB_ID BO_NAME
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateBo", method = RequestMethod.GET)
	@ResponseBody
	public String updateBo() throws Exception {
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
		int updateUser = boService.UpdateBo(pd);
		String db_id = (String) pd.get("DB_ID");
		if (0 < updateUser) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "更新权限ID【" + db_id + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "更新权限ID【" + db_id + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 删除菜单
	 * 
	 * @param menu_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMenuByID", method = RequestMethod.GET)
	@ResponseBody
	public String deleteMenuByID(String menu_id) throws Exception {
		JSONObject rspJson = new JSONObject();
		int deleteMenuById = menuService.deleteMenuById(menu_id);
		if (0 < deleteMenuById) {
			@SuppressWarnings("unchecked")
			List<Menu> allmenuList = CacheUtil.getCahe("AMSMenuList");
			// Menu menu = new Menu();
			// menu.setMENU_ID(menu_id);
			List<Menu> menuBean = CacheUtil.getDeleMenuByListMenu(allmenuList, menu_id);
			if (null != menuBean) {
				CacheUtil.addCahe(allmenuList, "AMSMenuList");
			}
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除菜单ID【" + menu_id + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除菜单ID【" + menu_id + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 删除菜单
	 * 
	 * @param menu_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePLMenuById", method = RequestMethod.GET)
	@ResponseBody
	public String deletePLMenuById(String[] MENU_ID) throws Exception {
		JSONObject rspJson = new JSONObject();
		int deleteMenuById = menuService.deletePLMenuById(MENU_ID);
		if (0 < deleteMenuById) {
			List<Menu> allmenuList = CacheUtil.getCahe("AMSMenuList");
			List<Menu> menuBean = CacheUtil.getDelePlMenuByListMenu(allmenuList, MENU_ID);
			if (null != menuBean) {
				CacheUtil.addCahe(allmenuList, "AMSMenuList");
			}
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除菜单ID【" + Tools.toString(MENU_ID) + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除菜单ID【" + Tools.toString(MENU_ID) + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 根据角色ID删除角色信息
	 * 
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteRoleByID", method = RequestMethod.GET)
	@ResponseBody
	public String deleteRoleByID() throws Exception {
		JSONObject rspJson = new JSONObject();
		PageData pd = this.getPageData();
		String role_id = (String) pd.get("ROLE_ID");
		int deleteRoleById = roleService.deleteRoleById(role_id);
		if (0 < deleteRoleById) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除角色ID【" + role_id + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除角色ID【" + role_id + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 根据用户id删除用户
	 * 
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteUserByID", method = RequestMethod.GET)
	@ResponseBody
	public String deleteUserByID() throws Exception {
		JSONObject rspJson = new JSONObject();
		PageData pd = this.getPageData();
		String user_id = (String) pd.get("USER_ID");
		PageData pd_dete = new PageData();
		pd_dete.put("USER_ID", user_id);
		userService.deleteAllUB(pd_dete);
		int deleteU = userService.deleteU(pd);
		if (0 < deleteU) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除用户ID【" + user_id + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除用户ID【" + user_id + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 删除权限Bo根据DB_id
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteBoByDBID", method = RequestMethod.GET)
	@ResponseBody
	public String deleteBoByDBID() throws Exception {
		JSONObject rspJson = new JSONObject();
		PageData pd = this.getPageData();
		String db_id = (String) pd.get("DB_ID");
		int deleteBo = boService.DeleteBo(db_id);
		if (0 < deleteBo) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除权限ID【" + db_id + "】信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除权限ID【" + db_id + "】信息失败!");
		}
		return rspJson.toString();
	}

	/**
	 * 批量删除用户，根据用户ID
	 * 
	 * @param user_ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePLUserByID", method = RequestMethod.GET)
	@ResponseBody
	public String deletePLUserByID(String[] USER_ID) throws Exception {
		JSONObject rspJson = new JSONObject();
		PageData pd_dete = new PageData();
		pd_dete.put("USER_IDS", USER_ID);
		userService.deleteAllUB(pd_dete);
		int deleteAllU = userService.deleteAllU(USER_ID);
		if (0 < deleteAllU) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除用户" + Tools.toString(USER_ID) + "【" + deleteAllU + "】条信息成功!");
		} else {
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除用户" + Tools.toString(USER_ID) + "【" + deleteAllU + "】条信息失败!");
		}
		return rspJson.toString();
	}
}
