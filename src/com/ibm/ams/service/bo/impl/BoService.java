package com.ibm.ams.service.bo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.ams.dao.DaoSupport;
import com.ibm.ams.entity.system.Bo;
import com.ibm.ams.entity.system.User;
import com.ibm.ams.service.bo.BoManager;
import com.ibm.ams.util.PageData;

@Service("boService")
public class BoService implements BoManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<Bo> queryBo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<Bo>) dao.findForList("BoMapper.queryBo", pd);
	}
	public PageData queryBoCount(String value) throws Exception {
		// TODO Auto-generated method stub
		return (PageData)dao.findForObject("BoMapper.queryBoCount", value);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> QueryBokey() throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("BoMapper.queryBokey");
	}
	@SuppressWarnings("unchecked")
	public List<Bo> queryFYBoInfo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<Bo>) dao.findForList("BoMapper.queryBoInfo", pd);
	}
	@SuppressWarnings("unchecked")
	public List<User> queryUserByBoidAndBP(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<User>) dao.findForList("BoMapper.queryUserByBoidAndBP", pd);
	}
	public int CreateBo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int)dao.save("BoMapper.insertBo", pd);
	}

	public int UpdateBo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int) dao.update("BoMapper.updateBo", pd);
	}

	public int DeleteBo(String db_id) throws Exception {
		// TODO Auto-generated method stub
		return (int)dao.save("BoMapper.deleteBoById", db_id);
	}

}
