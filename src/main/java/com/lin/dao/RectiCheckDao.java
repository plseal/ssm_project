
package com.lin.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.lin.entity.RectiCheckEntity;
import com.lin.entity.RectiNewEntity;
import com.lin.util.Pagination;
import com.lin.util.common;
@Repository
public class RectiCheckDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static Logger logger = Logger.getLogger(RectiCheckDao.class);
	public RectiCheckDao()
	{
	}
	public Integer checkIdExist(String javaid){
		logger.info("["+this.getClass().getName()+"][checkIdExist][start]");
		String sql = "SELECT count(1) as CNT from recticheck WHERE javaid='"+javaid+"' and deleteflg ='notdeleted' ";
		
		logger.info("["+this.getClass().getName()+"][checkIdExist][SQL]"+sql);
		
		Integer cnt = namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql);
		logger.info("["+this.getClass().getName()+"][checkIdExist][cnt]:"+cnt);
		logger.info("["+this.getClass().getName()+"][checkIdExist][end]");
		return cnt;
	}
	public String insert(RectiCheckEntity entity){
		logger.info("["+this.getClass().getName()+"][insert][start]");
		String sql = "insert into recticheck values("
				+ "'"+ entity.getJavaid()+"'"
				+ ",'"+ entity.getAc()+"'"
				+ ",'"+ entity.getTaskid()+"'"
				+ ",'"+ entity.getTaskname()+"'"
				+ ",'"+ entity.getProcessid()+"'"
				+ ",'"+ entity.getStarter()+"'"
				+ ",'"+ entity.getNo()+"'"
				+ ",'"+ entity.getDanwei()+"'"
				+ ",'"+ entity.getCheckdate()+"'"
				+ ",'"+ entity.getDeadlinedate()+"'"
				+ ",'"+ entity.getName()+"'"
				+ ",'"+ entity.getCheckname()+"'"
				+ ",'"+ entity.getContent()+"'"
				+ ",'"+ entity.getRectitype()+"'"
				+ ",'"+ entity.getLocation1()+"'"
				+ ",'"+ entity.getLocation2()+"'"
				+ ",'"+ entity.getLocation3()+"'"

				+ ",'"+ entity.getRecti1()+"'"
				+ ",'"+ entity.getRecti2()+"'"
				+ ",'"+ entity.getRecti3()+"'"
				+ ",'"+ entity.getRecti4()+"'"
				+ ",'"+ entity.getNrecti1()+"'"
				+ ",'"+ entity.getNrecti2()+"'"
				+ ",'"+ entity.getNrecti3()+"'"
				+ ",'"+ entity.getNrecti4()+"'"
				+ ",'"+ entity.getNrecti5()+"'"
				+ ",'"+ entity.getNrecti6()+"'"
				+ ",'"+ entity.getNrecti7()+"'"
				+ ",'"+ entity.getNrecti8()+"'"
				+ ",'"+ entity.getRectisuggest()+"'"
				+ ",'"+ entity.getRectistatus()+"'"
				+ ",'"+ entity.getBecheckedbuleader()+"'"
				+ ",'"+ entity.getRecticheckteamleadername()+"'"
				+ ",'"+ entity.getRectimakesurename()+"'"
				+ ",'"+ entity.getRectilevel()+"'"
				+ ",'"+ entity.getLeader()+"'"
				+ ",'"+ entity.getLeadername()+"'"
				+ ",'"+ entity.getLeaderokdate()+"'"
				+ ",'"+ entity.getLeaderokmessage()+"'"
				+ ",'"+ entity.getManager()+"'"
				+ ",'"+ entity.getManagername()+"'"
				+ ",'"+ entity.getManagerokdate()+"'"
				+ ",'"+ entity.getManagerokmessage()+"'"
				+ ",'"+ entity.getGeofficer()+"'"
				+ ",'"+ entity.getGeofficername()+"'"
				+ ",'"+ entity.getGeofficerokdate()+"'"
				+ ",'"+ entity.getGeofficerokmessage()+"'"
				+ ",'"+ entity.getCityofficer()+"'"
				+ ",'"+ entity.getCityofficername()+"'"
				+ ",'"+ entity.getCityofficerokdate()+"'"
				+ ",'"+ entity.getCityofficerokmessage()+"'"
				+ ",'"+ entity.getSafetymanager()+"'"
				+ ",'"+ entity.getSafetymanagername()+"'"
				+ ",'"+ entity.getSafetymanagerokdate()+"'"
				+ ",'"+ entity.getSafetymanagerokmessage()+"'"
				+ ",'notdeleted'"
				+ ",'"+ entity.getDeletedby()+"'"
				+ ",'"+ entity.getTownofficer()+"'"
				+ ",'"+ entity.getTownofficerokdate()+"'"
				+ ",'"+ entity.getTownofficerokmessage()+"'"
				+ ",'"+ entity.getStarterokdate()+"'"
				+ ",'"+ entity.getStarterokmessage()+"'"


				+ ")";
		logger.info("["+this.getClass().getName()+"][insert][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][insert][end]");
		return entity.getJavaid();
	}


	public RectiCheckEntity getById(String javaid){
		logger.info("["+this.getClass().getName()+"][getById][start]");
		String sql = "SELECT  recti.*  "
		+ " ,u.name becheckedbuleadername"
		+ " FROM "
		+ " recticheck  recti left join user u on recti.becheckedbuleader = u.id"
		+ " WHERE  recti.javaid='"+javaid+"'"
		+ " and recti.deleteflg ='notdeleted' ";
		
		logger.info("["+this.getClass().getName()+"][getById][SQL]"+sql);
		RowMapper<RectiCheckEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(RectiCheckEntity.class);
		
		RectiCheckEntity sp = (RectiCheckEntity)namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, rm);
		logger.info("["+this.getClass().getName()+"][getById][end]");
		return sp;
	}
	public String update(RectiCheckEntity entity)	{
		logger.info("["+this.getClass().getName()+"][update][start]");
		String sql = "update recticheck set "
				+ "ac='"+ entity.getAc()+"'"
				+ ",taskid='"+ entity.getTaskid()+"'"
				+ ",taskname='"+ entity.getTaskname()+"'"
				+ ",processid='"+ entity.getProcessid()+"'"
				+ ",starter='"+ entity.getStarter()+"'"
				+ ",no='"+ entity.getNo()+"'"
				+ ",danwei='"+ entity.getDanwei()+"'"
				+ ",checkdate='"+ entity.getCheckdate()+"'"
				+ ",deadlinedate='"+ entity.getDeadlinedate()+"'"
				+ ",name='"+ entity.getName()+"'"
				+ ",checkname='"+ entity.getCheckname()+"'"
				+ ",content='"+ entity.getContent()+"'"
				+ ",rectitype='"+ entity.getRectitype()+"'"
				+ ",location1='"+ entity.getLocation1()+"'"
				+ ",location2='"+ entity.getLocation2()+"'"
				+ ",location3='"+ entity.getLocation3()+"'"

				+ ",recti1='"+ entity.getRecti1()+"'"
				+ ",recti2='"+ entity.getRecti2()+"'"
				+ ",recti3='"+ entity.getRecti3()+"'"
				+ ",recti4='"+ entity.getRecti4()+"'"
				+ ",nrecti1='"+ entity.getNrecti1()+"'"
				+ ",nrecti2='"+ entity.getNrecti2()+"'"
				+ ",nrecti3='"+ entity.getNrecti3()+"'"
				+ ",nrecti4='"+ entity.getNrecti4()+"'"
				+ ",nrecti5='"+ entity.getNrecti5()+"'"
				+ ",nrecti6='"+ entity.getNrecti6()+"'"
				+ ",nrecti7='"+ entity.getNrecti7()+"'"
				+ ",nrecti8='"+ entity.getNrecti8()+"'"
				+ ",rectisuggest='"+ entity.getRectisuggest()+"'"
				+ ",becheckedbuleader='"+ entity.getBecheckedbuleader()+"'"
				
				+ ",recticheckteamleadername='"+ entity.getRecticheckteamleadername()+"'"
				+ ",rectimakesurename='"+ entity.getRectimakesurename()+"'"
				+ ",rectilevel='"+ entity.getRectilevel()+"'"
				+ ",leader='"+ entity.getLeader()+"'"
				+ ",leadername='"+ entity.getLeadername()+"'"
				+ ",leaderokdate='"+ entity.getLeaderokdate()+"'"
				+ ",leaderokmessage='"+ entity.getLeaderokmessage()+"'"
				+ ",manager='"+ entity.getManager()+"'"
				+ ",managername='"+ entity.getManagername()+"'"
				+ ",managerokdate='"+ entity.getManagerokdate()+"'"
				+ ",managerokmessage='"+ entity.getManagerokmessage()+"'"
				+ ",geofficer='"+ entity.getGeofficer()+"'"
				+ ",geofficername='"+ entity.getGeofficername()+"'"
				+ ",geofficerokdate='"+ entity.getGeofficerokdate()+"'"
				+ ",geofficerokmessage='"+ entity.getGeofficerokmessage()+"'"
				+ ",cityofficer='"+ entity.getCityofficer()+"'"
				+ ",cityofficername='"+ entity.getCityofficername()+"'"
				+ ",cityofficerokdate='"+ entity.getCityofficerokdate()+"'"
				+ ",cityofficerokmessage='"+ entity.getCityofficerokmessage()+"'"
				+ ",safetymanager='"+ entity.getSafetymanager()+"'"
				+ ",safetymanagername='"+ entity.getSafetymanagername()+"'"
				+ ",safetymanagerokdate='"+ entity.getSafetymanagerokdate()+"'"
				+ ",safetymanagerokmessage='"+ entity.getSafetymanagerokmessage()+"'"
				+ ",townofficer='"+ entity.getTownofficer()+"'"
				+ ",townofficerokdate='"+ entity.getTownofficerokdate()+"'"
				+ ",townofficerokmessage='"+ entity.getTownofficerokmessage()+"'"
				+ ",starterokdate='"+ entity.getStarterokdate()+"'"
				+ ",starterokmessage='"+ entity.getStarterokmessage()+"'"


				+ " where javaid='"+entity.getJavaid()+"'";
		
		logger.info("["+this.getClass().getName()+"][insert][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][update][end]");
		
		return entity.getJavaid();
	}
	

	
	public String delete(RectiCheckEntity entity,HttpServletRequest request) {
		logger.info("["+this.getClass().getName()+"][delete][start]");
		String sql = "update recticheck set "
				+ " deleteflg='deleted'"
				+ ",deletedby='"+request.getSession().getAttribute("id")+"'"
				+ " where javaid='"+entity.getJavaid()+"'";
	
		logger.info("["+this.getClass().getName()+"][delete][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][delete][end]");
		return entity.getJavaid();
	}

	public List<RectiCheckEntity>  getAll()
	{
		StringBuffer sb = new StringBuffer("SELECT  t1.javaid,  t2.dbid_ taskid,  t2.name_ taskstatus,  t1.processid,  t1.starter,  t1.id,  t1.name,  t1.location,  t1.checkdate,  t1.content,  t1.okstandard,  t1.okmeasure,  t1.rectilevel,  t1.leader,  t1.safetymanagername  "
				+ "from rectinew t1 left join JBPM4_TASK t2  on  t1.processid=t2.execution_id_ ");
		
		List<RectiCheckEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sb.toString(), new BeanPropertyRowMapper(RectiCheckEntity.class));
		
		return list;
	}
	public Pagination  getByUserId(String userId
			,String role
			,String listRectiAc
			,String danwei
			,String location
			,String rectitype
			,String rectilevel
			,String s_searchForAllFlg
			,String checkdate
			,Integer intPage
			,Integer intPageSize
			){
		logger.info("["+this.getClass().getName()+"][getByUserId][start]");
		//get this month ac
		Date date = new Date();
		SimpleDateFormat df3 = new SimpleDateFormat("yyyyMM");
		String ac = df3.format(date);
		String strAc = " and recti.ac = '"+ac+"'";
        
		
		logger.info("["+this.getClass().getName()+"][getByUserId][listRectiAc]:"+listRectiAc);

		String sql = "SELECT  recti.javaid"
				+ ",  recti.ac "
				+ ",  task.dbid_ taskid"
				+ ",  recti.taskname"
				+ ",  task.name_ taskstatus"
				+ ",  recti.processid"
				+ ",  recti.starter"
				+ ",  recti.no"
				+ ",  recti.name"
				+ ",  recti.danwei"
				+ ",  recti.location1"
				+ ",  recti.location2"
				+ ",  recti.location3"
				+ ",  recti.content"
				+ ",  recti.checkdate"
				+ ",  recti.rectilevel"
				+ ",  recti.rectitype"
				+ ",  recti.leader"
				+ ",  recti.safetymanagername"
				+ " from recticheck recti left join JBPM4_TASK task  on  recti.processid=task.execution_id_ "
				+ "   where recti.deleteflg = 'notdeleted'";
				if (!"".equals(listRectiAc)) {
					if ("this_month".equals(listRectiAc)){
						strAc = " and recti.ac = '"+ac+"'";
					} else if ("last_month".equals(listRectiAc)){
						ac = df3.format(common.getLastMonthDate(date));
						strAc = " and recti.ac = '"+ac+"'";
					} else if ("this_year".equals(listRectiAc)){
						strAc = " and recti.ac like '"+ac.substring(0,4)+"%'";
					}
				}
				sql = sql + strAc;
				if (s_searchForAllFlg.contains("所有人隐患信息")) {
					//可以查看所有用户录入的数据
				} else {
				    sql = sql + "    and (starter='"+userId+"' "
						+ "or leader='"+userId+"'"
						+ "or manager='"+userId+"'"
						+ "or geofficer='"+userId+"'"
						+ "or cityofficer='"+userId+"'"
						+ "or townofficer='"+userId+"'"
						+ "or safetymanager='"+userId+"')";
				}
				if (!"".equals(danwei)){
					sql = sql + " and recti.danwei='"+danwei+"'";	
				}
				if (!"".equals(location)){
					sql = sql + " and recti.location='"+location+"'";	
				}
				if (!"".equals(rectitype)){
					sql = sql + " and recti.rectitype='"+rectitype+"'";	
				}
				if (!"".equals(rectilevel)){
					sql = sql + " and recti.rectilevel='"+rectilevel+"'";	
				}
				if (!"".equals(checkdate)){
					sql = sql + " and recti.checkdate='"+checkdate+"'";	
				}
				sql = sql + " order by  right(processid, 7) desc ";
		logger.info("["+this.getClass().getName()+"][getByUserId][SQL]"+sql);
		
		//List<RectiCheckEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(RectiCheckEntity.class));
		Pagination pag = new Pagination( sql,  intPage,  intPageSize,namedParameterJdbcTemplate);
		logger.info("["+this.getClass().getName()+"][getByUserId][end]");

		return pag;
	}
	
}
