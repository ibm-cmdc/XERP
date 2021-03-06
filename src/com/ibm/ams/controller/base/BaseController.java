package com.ibm.ams.controller.base;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.ams.util.Const;
import com.ibm.ams.util.PageData;

public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	/** new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	public String returnMessage(String type,String message) throws JSONException{
		JSONObject rspJson = new JSONObject();
		rspJson.put(Const.RESULT_CODE, type);
		rspJson.put(Const.RESULT_MSG, message);
		return rspJson.toString();
	}
	
}
