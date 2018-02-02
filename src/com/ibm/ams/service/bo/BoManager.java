package com.ibm.ams.service.bo;

import java.util.List;

import com.ibm.ams.entity.system.Bo;
import com.ibm.ams.entity.system.User;
import com.ibm.ams.util.PageData;

public interface BoManager {

	/**
	 * 查询数据对象
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<Bo>  queryBo(PageData pd)throws Exception;
	
	/**
	 * 获取总条数
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public PageData  queryBoCount(String value)throws Exception;
	
	/**
	 * 分组查询BO_Key
	 * @return
	 * @throws Exception
	 */
	public List<PageData>  QueryBokey()throws Exception;
	/**
	 * 分页查询数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<Bo>  queryFYBoInfo(PageData pd)throws Exception;
	/**
	 * 根据boid和系统查询用户
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<User>  queryUserByBoidAndBP(PageData pd)throws Exception;
	/**
	 * 创建数据对象
	 * @param bo
	 * @throws Exception
	 */
	public int CreateBo(PageData pd)throws Exception;
	/**
	 * 修改数据对象
	 * @param bo
	 * @throws Exception
	 */
	public int UpdateBo(PageData pd)throws Exception;
	/**
	 * 删除数据对象
	 * @param db_id
	 * @throws Exception
	 */
	public int DeleteBo(String db_id)throws Exception;
}
