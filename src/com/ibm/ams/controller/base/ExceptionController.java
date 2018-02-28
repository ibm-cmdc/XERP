package com.ibm.ams.controller.base;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.ams.exception.AuthorizationException;
import com.ibm.ams.util.Const;

@ControllerAdvice
public class ExceptionController extends BaseController{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@ExceptionHandler({AuthorizationException.class})
	@ResponseBody
	public String handleAuthorizationException(Exception ex){
		JSONObject rspJson = new JSONObject();
		try { 
			rspJson.put(Const.RESULT_CODE, "401");
			rspJson.put(Const.RESULT_MSG, ex.getMessage());
			logger.error("请求无权限:"+"code_401,message_"+ex.getMessage());
		} catch (JSONException e) {
			logger.error("AuthorizationException:"+e.getMessage());
		}
		
		return rspJson.toString();
	}
	
	//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public String handleException(Exception ex){
		JSONObject rspJson = new JSONObject();
		try { 
			rspJson.put(Const.RESULT_CODE, "500");
			rspJson.put(Const.RESULT_MSG, "系统内部异常！");
			logger.error("系统内部错误:"+"code_500,message_系统内部异常！");
		} catch (JSONException e) {
			logger.error("Exception:"+e.getMessage());
		}
		
		return rspJson.toString();
	}

}
