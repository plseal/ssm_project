
package com.lin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MyJbpmDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static Logger logger = Logger.getLogger(MyJbpmDao.class);
	
	public MyJbpmDao()
	{
	}



	public String getTaskIdByProcessId(String processid){
		logger.info("["+this.getClass().getName()+"][getTaskIdByProcessId][start]");
		String sql = "select htask_ from jbpm4_hist_actinst where EXECUTION_ = '"+processid+"' ";
		
		String taskId;
		taskId ="";
		
		
		logger.info("["+this.getClass().getName()+"][getByUserId][SQL]"+sql);
		List<Map<String, Object>> listTaskId = namedParameterJdbcTemplate.getJdbcOperations().queryForList(sql);
		if (listTaskId.size() > 0) {
			taskId = listTaskId.get(0).get("htask_").toString();
		}
		logger.info("["+this.getClass().getName()+"][getTaskIdByProcessId][taskId]:"+taskId);
		logger.info("["+this.getClass().getName()+"][getTaskIdByProcessId][end]");
		return taskId;
	}
	
	public List<Map<String, Object>> getPersonalTasks(String strASSIGNEE){
		logger.info("["+this.getClass().getName()+"][getPersonalTasks][start]");
		String sql = getPersonalTaskSql(strASSIGNEE);
		
		logger.info("["+this.getClass().getName()+"][getPersonalTasks][SQL]"+sql);
		List<Map<String, Object>> listTask = namedParameterJdbcTemplate.getJdbcOperations().queryForList(sql);
		
		logger.info("["+this.getClass().getName()+"][getPersonalTasks][end]");
		return listTask;
	}
	
	
	public List<Map<String, Object>> getPersonalMsg(String strASSIGNEE){
		//ÂÖÑ¯²»³ölog
		String sql =  getPersonalTaskSql(strASSIGNEE);
		
		List<Map<String, Object>> listTask = namedParameterJdbcTemplate.getJdbcOperations().queryForList(sql);
		
		
		return listTask;
	}
	
	public String getPersonalTaskSql(String strASSIGNEE){
		
		String sql = "select  "
				+ "T1.DBID_ as taskid"
				+ ",T1.NAME_ as taskname"
				+ ",T1.EXECUTION_ID_ as processid"
				+ ",T1.ASSIGNEE_ as assignee"
				+ ",T3.name as starter"
				+ ",T2.javaid as javaid"
				+ " from JBPM4_TASK T1,rectinew T2,user T3"
				+ " where T1.ASSIGNEE_='"+strASSIGNEE+"'"
				+ " and T1.EXECUTION_ID_ = T2.processid"
				+ " and T2.starter = T3.id "
				+ " and T2.deleteflg = 'notdeleted' "
				+ " UNION"
				+ " SELECT"
				+ " T1.DBID_ as taskid"
				+ ",T1.NAME_ as taskname"
				+ ",T1.EXECUTION_ID_ as processid"
				+ ",T1.ASSIGNEE_ as assignee"
				+ ",T3.name as starter"
				+ ",T2.javaid as javaid"
				+ " from JBPM4_TASK T1,recticheck T2,user T3"
				+ " where T1.ASSIGNEE_='"+strASSIGNEE+"'"
				+ " and T1.EXECUTION_ID_ = T2.processid"
				+ " and T2.starter = T3.id "
				+ " and T2.deleteflg = 'notdeleted' "
				+ " UNION"
				+ " SELECT"
				+ " T1.DBID_ as taskid"
				+ ",T1.NAME_ as taskname"
				+ ",T1.EXECUTION_ID_ as processid"
				+ ",T1.ASSIGNEE_ as assignee"
				+ ",T3.name as starter"
				+ ",T2.javaid as javaid"
				+ " from JBPM4_TASK T1,t_rectidanger T2,user T3"
				+ " where T1.ASSIGNEE_='"+strASSIGNEE+"'"
				+ " and T1.EXECUTION_ID_ = T2.processid"
				+ " and T2.starter = T3.id "
				+ " and T2.deleteflg = 'notdeleted' "
				;
		
		return sql;
	}

	
}
