package com.ibm.ams.controller.base;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.ams.exception.AuthorizationException;
import com.ibm.ams.util.Const;

@ControllerAdvice
public class ExceptionController extends BaseController{
	
	@ExceptionHandler({AuthorizationException.class})
	@ResponseBody
	public String handleException(Exception ex){
		JSONObject rspJson = new JSONObject();
		try { 
			rspJson.put(Const.RESULT_CODE, "401");
			rspJson.put(Const.RESULT_MSG, "用户无权限！");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return rspJson.toString();
	}

}
