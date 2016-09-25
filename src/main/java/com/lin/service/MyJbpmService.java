package com.lin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lin.dao.MyJbpmDao;
@Service
public class MyJbpmService
{
	@Resource(name="myJbpmDao")
	private MyJbpmDao myJbpmDao;

	public MyJbpmService()
	{
	}



	public String getTaskIdByProcessId(String processid)
	{
		return myJbpmDao.getTaskIdByProcessId(processid);
	}

	public List<Map<String, Object>> getPersonalTasks(String strASSIGNEE){
		
		return myJbpmDao.getPersonalTasks(strASSIGNEE);
	}
	public List<Map<String, Object>> getPersonalMsg(String strASSIGNEE){
		
		return myJbpmDao.getPersonalMsg(strASSIGNEE);
	}
	
}
