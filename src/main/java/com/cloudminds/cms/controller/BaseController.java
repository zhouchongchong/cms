package com.cloudminds.cms.controller;

import com.cloudminds.cms.constant.ConstantBean;
import com.cloudminds.cms.vo.BaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BaseController
{
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@InitBinder
	public void InitBinder(WebDataBinder binder)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		
		binder.setFieldMarkerPrefix(null);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseResp handleException(Exception e)
	{
		logger.error("====================请求出错：", e);
		
		BaseResp resp = new BaseResp();
		
		resp.setStatus(ConstantBean.RESPONSE_ERR);
		resp.setMessage(e.getLocalizedMessage());
		
		return resp;
	}
	
}
