package com.ibm.ams.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ibm.ams.entity.system.Menu;
import com.ibm.ams.init.AmsCache;

import net.sf.json.JSONArray;

public class CacheUtil {
    
	/**
	 * 根据缓存名称查询缓存对象
	 * @param value
	 * @return
	 */
	public static List<Menu> getCahe(String value){
		//缓存获取资产系统菜单 
		List<Menu> allmenuList= null;
		try {
			AmsCache amsCache = AmsCache.getInstance();
			Object map = amsCache.getMap(value);
			
			//获取有权限的菜单
			List<Menu> readMenu = new ArrayList<Menu>();
			if(map!=null && map instanceof List){
				allmenuList = (List<Menu>) map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return allmenuList;
	}
	/**
	 * 删除缓存中的数据
	 * @param allmenuList
	 * @param menu_id
	 * @return
	 */
	public static List<Menu> getDeleMenuByListMenu(List<Menu> allmenuList,String menu_id){
//		Menu menuBean=null;
		Iterator<Menu> iter = allmenuList.iterator();
		int menuSize = allmenuList.size();
		while (iter.hasNext()) {
            Menu menu = iter.next();
            String menu_ID2 = menu.getMENU_ID();
            if (null==menu_ID2||"".equals(menu_ID2)) {
            	continue;
			}
            if (menu_ID2.equals(menu_id)) {
            	if (1<menuSize) {
				}else{
					menu.setSubMenu(null);
				}
                iter.remove();
            }else{
					deleSubMenu( menu, menu_id);
            }
        }
		return allmenuList;
	}
	/**
	 * 增加Menu
	 * @param allmenuList 缓存listMenu
	 * @param parent_id   
	 * @param enu 新增的
	 * @return
	 */
	public static List<Menu> getAddListMenu(List<Menu> allmenuList,String parent_id,Menu enu){
		for (Menu menu : allmenuList) {
			String menu_ID2 = menu.getMENU_ID();
			boolean hasMenu = menu.isHasMenu();
			if (menu_ID2.equals(parent_id)) {
				List<Menu> subMenu=new ArrayList<>(); 
				subMenu.add(enu);
				menu.setSubMenu(subMenu);
			}else{
				if (hasMenu) {
					addSubMenu( menu, parent_id, enu);
				}
			}
		}
		return allmenuList;
	}
	/**
	 * 修改菜单
	 * @param allmenuList
	 * @param menu_id
	 * @param enu
	 * @return
	 */
	public static List<Menu> getUpListMenu(List<Menu> allmenuList,String menu_id,Menu enu){
		for (Menu menu : allmenuList) {
			String menu_ID2 = menu.getMENU_ID();
			boolean hasMenu = menu.isHasMenu();
			if (menu_ID2.equals(menu_id)) {
				menu.setMENU_NAME(enu.getMENU_NAME());
				menu.setMENU_URL(enu.getMENU_URL());
				menu.setPARENT_ID(enu.getPARENT_ID());
				menu.setMENU_STATE(enu.getMENU_STATE());
			}else{
				if (hasMenu) {
					upSubMenu( menu, menu_id, enu);
				}
			}
		}
		return allmenuList;
	}
	public static void upSubMenu(Menu menuSu,String parent_id,Menu enu){
		List<Menu> subMenu = menuSu.getSubMenu();
		for(Menu menu : subMenu){
			String menu_ID2 = menu.getMENU_ID();
			boolean hasMenu = menu.isHasMenu();
			if (menu_ID2.equals(parent_id)) {
				menu.setMENU_NAME(enu.getMENU_NAME());
				menu.setMENU_URL(enu.getMENU_URL());
				menu.setPARENT_ID(enu.getPARENT_ID());
				menu.setMENU_STATE(enu.getMENU_STATE());
			}else{
				if (hasMenu) {
					upSubMenu( menu, parent_id,enu);
				}
			}
				
		}
	}
	public static void addSubMenu(Menu menuSu,String parent_id,Menu enu){
		List<Menu> subMenu = menuSu.getSubMenu();
		for(Menu menu : subMenu){
			String menu_ID2 = menu.getMENU_ID();
			boolean hasMenu = menu.isHasMenu();
			if (menu_ID2.equals(parent_id)) {
				List<Menu> subMenus = menu.getSubMenu();
				subMenus.add(enu);
				menu.setSubMenu(subMenus);
			}else{
				if (hasMenu) {
					addSubMenu( menu, parent_id,enu);
				}
			}
				
		}
	}
    /**
     * 根据多个menuID获取多个MENU
     * @param allmenuList
     * @param arr
     * @return
     */
	public static List<Menu> getDelePlMenuByListMenu(List<Menu> allmenuList, String[] arr) {
//		List<Menu> menuBean = new ArrayList<Menu>();

		for (int a = 0; a < arr.length; a++) {
			String menu_id = arr[a];
			Iterator<Menu> iter = allmenuList.iterator();
			int menuSize = allmenuList.size();
			while (iter.hasNext()) {
	            Menu menu = iter.next();
	            String menu_ID2 = menu.getMENU_ID();
	            if (null==menu_ID2||"".equals(menu_ID2)) {
	            	continue;
				}
	            if (menu_ID2.equals(menu_id)) {
	            	if (1<menuSize) {
					}else{
						menu.setSubMenu(null);
					}
	                iter.remove();
	            }else{
	            	delePLSubMenu( menu, menu_id);
	            }
	        }
			
		}

		return allmenuList;
	}
	
	public static List<Menu> subMenu(List<Menu> menuBean,Menu menuSu,String menu_id){
		List<Menu> subMenu = menuSu.getSubMenu();
		int menuSize = subMenu.size();
		for(Menu menu : subMenu){
			String menu_ID2 = menu.getMENU_ID();
			boolean hasMenu = menu.isHasMenu();
			if (menu_ID2.equals(menu_id)) {
				menuBean.add(menu);
				if (1<menuSize) {
				}else{
					menuSu.setSubMenu(null);
				}
			}else{
				if (hasMenu) {
				menuBean = subMenu(menuBean, menu, menu_id);
				}
			}
				
		}
		return menuBean;
	}
	public static void delePLSubMenu(Menu menuSu,String menu_id){
		List<Menu> subMenu = menuSu.getSubMenu();
		if (null!=subMenu) {
			int menuSize = subMenu.size();
			Iterator<Menu> iter = subMenu.iterator();
			 while (iter.hasNext()) {
		            Menu menu = iter.next();
		            String menu_ID2 = menu.getMENU_ID();
		            if (null==menu_ID2||"".equals(menu_ID2)) {
		            	continue;
					}
						if (menu_ID2.equals(menu_id)) {
			            	if (1<menuSize) {
							}else{
								menuSu.setSubMenu(null);
							}
			                iter.remove();
			            }else{
			            		delePLSubMenu( menu, menu_id);
			            }
		            
		        }
		}
		
	}
	public static void deleSubMenu(Menu menuSu,String menu_id){
		List<Menu> subMenu = menuSu.getSubMenu();
		int menuSize = subMenu.size();
		Iterator<Menu> iter = subMenu.iterator();
		 while (iter.hasNext()) {
	            Menu menu = iter.next();
	            String menu_ID2 = menu.getMENU_ID();
				boolean hasMenu = menu.isHasMenu();
	            if (menu_ID2.equals(menu_id)) {
	            	if (1<menuSize) {
					}else{
						menuSu.setSubMenu(null);
					}
	                iter.remove();
	            }else{
	            	if (hasMenu) {
						deleSubMenu( menu, menu_id);
					}
	            }
	        }
	}
	
	/**
	 * 保存缓存
	 * @param menu
	 * @param value
	 */
	public static void addCahe(List<Menu> allmenuList,String value){
		try {
			//缓存获取资产系统菜单 
			AmsCache amsCache = AmsCache.getInstance();
			if (null!=allmenuList) {
				amsCache.putMap(value, allmenuList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取Menu,根据RIGHTS
	 * @param RIGHTS
	 * @param value
	 * @return
	 * @throws Exception
	 */
   public static String getMenu(String rights,String value)throws Exception{
		
		//缓存获取资产系统菜单 
		AmsCache amsCache = AmsCache.getInstance();
		Object map = amsCache.getMap(value);
		
		//获取有权限的菜单
		List<Menu> readMenu = new ArrayList<Menu>();
		if(map!=null && map instanceof List){
			@SuppressWarnings("unchecked")
			List<Menu> allmenuList = (List<Menu>) map;
			readMenu = readMenu(allmenuList,rights);
		}
		//输出菜单为json格式
		JSONArray arr = JSONArray.fromObject(readMenu);
		String json = arr.toString();
		String replace = json.replace("[]", "null");
		return replace;
	} 
   public static String getMenuListID(String rights,String value)throws Exception{
		
		//缓存获取资产系统菜单 
		AmsCache amsCache = AmsCache.getInstance();
		Object map = amsCache.getMap(value);
		
		//获取有权限的菜单
		List<Integer> readMenuGetMenuID = new ArrayList<Integer>();
		if(map!=null && map instanceof List){
			@SuppressWarnings("unchecked")
			List<Menu> allmenuList = (List<Menu>) map;
			List<Integer> listMenuID = new ArrayList<Integer>();
			 readMenuGetMenuID = readMenuGetMenuID(allmenuList,rights, listMenuID);
		}
		//输出菜单为json格式
		JSONArray arr = JSONArray.fromObject(readMenuGetMenuID);
		String json = arr.toString();
		String replace = json.replace("[]", "null");
		return replace;
	} 
   /**根据角色权限获取本权限的菜单列表(递归处理)
	 * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 */
	public static List<Menu> readMenu(List<Menu> menuList,String roleRights){
		if (null!=menuList) {
			for(int i=0;i<menuList.size();i++){
				//set是否对该菜单有权限
				menuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, menuList.get(i).getMENU_ID()));
				if(menuList.get(i).isHasMenu()){		//判断是否有此菜单权限
					readMenu(menuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
				}
			}
		}
		return menuList;
	}
	  /**根据角色权限获取本权限的菜单列表(递归处理)
		 * @param menuList：传入的总菜单
		 * @param roleRights：加密的权限字符串
		 * @return
		 */
		public static List<Integer> readMenuGetMenuID(List<Menu> menuList,String roleRights,List<Integer> listMenuID){
			if (null!=menuList) {
			for(int i=0;i<menuList.size();i++){
				//set是否对该菜单有权限
				String menu_ID = menuList.get(i).getMENU_ID();
				boolean testRights = RightsHelper.testRights(roleRights, menu_ID);
				if (testRights) {
					listMenuID.add(Integer.valueOf(menu_ID));
				}
				menuList.get(i).setHasMenu(testRights);
				if(menuList.get(i).isHasMenu()){		//判断是否有此菜单权限
					List<Menu> subMenu = menuList.get(i).getSubMenu();
					//当有子节点的时候，前台不能要父节点ID，所以要去掉
					if(subMenu!=null && subMenu.size()>0){
						listMenuID.remove(Integer.valueOf(menu_ID));
					}
					readMenuGetMenuID(menuList.get(i).getSubMenu(), roleRights,listMenuID);//是：继续排查其子菜单
				}
			}
			}
			return listMenuID;
		}
}
