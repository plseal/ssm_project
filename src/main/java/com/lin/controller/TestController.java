package com.lin.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.dao.RectiNewDao;
import com.lin.entity.UserEntity;
import com.lin.service.UserService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/test")
public class TestController  {
	@Autowired
	private UserService userService;
	private static Logger logger = Logger.getLogger(TestController.class);
	/**
	 * ��ȡ����
	 * @author guangshuai.wang
	 * 2014-10-14����12:10:40
	 * @param type
	 * @param request
	 * @param nowpage 			��ǰҳ�������jquery-easyui�Լ������ύ���܅���������������Ϊpage
	 * @param rows				ÿҳ��ʾ�ļ�¼���������jquery-easyui�Լ������ύ�ą���������������Ϊrows
	 * @return
	 */
	@RequestMapping("getAll")
    public @ResponseBody Map<String, Object>  getAll(HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("["+this.getClass().getName()+"][getAll][start]");

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", "111");
		result.put("msg", "�ɹ�");  
		logger.info("["+this.getClass().getName()+"][getAll][end]");
		return result;
    }
	

		
	
}
