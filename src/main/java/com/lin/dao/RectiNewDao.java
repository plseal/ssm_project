
package com.lin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.lin.entity.RectiNewEntity;
import com.lin.util.Pagination;
import com.lin.util.common;


@Repository
public class RectiNewDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static Logger logger = Logger.getLogger(RectiNewDao.class);
	
	public RectiNewDao()
	{
	}

	public String insert(RectiNewEntity entity){
		logger.info("["+this.getClass().getName()+"][insert][start]");
		String sql = "insert into rectinew values("
				+ "'"+entity.getJavaid()+"'"
				+ ",'"+entity.getAc()+"'"
				+ ",'"+entity.getTaskid()+"'"
				+ ",'"+entity.getTaskname()+"'"
				+ ",'"+entity.getProcessid()+"'"
				+ ",'"+entity.getStarter()+"'"
				+ ",'"+entity.getNo()+"'"
				+ ",'"+entity.getName()+"'"
				+ ",'"+entity.getLocation()+"'"
				+ ",'"+entity.getCheckdate()+"'"
				+ ",'"+entity.getContent()+"'"
				+ ",'"+entity.getOkstandard()+"'"
				+ ",'"+entity.getOkmeasure()+"'"	
				+ ",''"
				+ ",'"+entity.getRectitype()+"'"
				+ ",'"+entity.getDanwei()+"'"
				+",'','',''"
				+ ",'','','','',''"
				+ ",'','','','',''"
				+ ",'','','','',''"
				+ ",'','','','','','','notdeleted','','','')";
		logger.info("["+this.getClass().getName()+"][insert][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][insert][end]");
		return entity.getJavaid();
	}

	public RectiNewEntity getById(String javaid)
	{
		logger.info("["+this.getClass().getName()+"][getById][start]");
		String sql = "SELECT  recti.* "
					+ " FROM rectinew  recti "
					+ " WHERE  recti.javaid='"+javaid+"'"
					+ " and recti.deleteflg ='notdeleted' ";
		
		logger.info("["+this.getClass().getName()+"][getById][SQL]"+sql);
		RowMapper<RectiNewEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(RectiNewEntity.class);
		
		RectiNewEntity sp = (RectiNewEntity)namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, rm);
		logger.info("["+this.getClass().getName()+"][getById][end]");
		return sp;
	}
	public Integer checkIdExist(String id){
		logger.info("["+this.getClass().getName()+"][checkIdExist][start]");
		String sql = "SELECT count(1) as CNT from rectinew WHERE javaid='"+id+"' and deleteflg ='notdeleted' ";
		
		logger.info("["+this.getClass().getName()+"][checkIdExist][SQL]"+sql);
		
		Integer cnt = namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql);
		logger.info("["+this.getClass().getName()+"][checkIdExist][cnt]:"+cnt);
		logger.info("["+this.getClass().getName()+"][checkIdExist][end]");
		return cnt;
	}
	public String update(RectiNewEntity entity)
	{
		logger.info("["+this.getClass().getName()+"][update][start]");
		String sql = "update rectinew set "
				+ "ac='"+entity.getAc()+"'"
				+ ",taskid='"+entity.getTaskid()+"'"
				+ ",taskname='"+entity.getTaskname()+"'"
				+ ",processid='"+entity.getProcessid()+"'"
				+ ",starter='"+entity.getStarter()+"'"
				+ ",no='"+entity.getNo()+"'"
				+ ",name='"+entity.getName()+"'"
				+ ",location='"+entity.getLocation()+"'"
				+ ",checkdate='"+entity.getCheckdate()+"'"
				+ ",content='"+entity.getContent()+"'"
				+ ",okstandard='"+entity.getOkstandard()+"'"
				+ ",okmeasure='"+entity.getOkmeasure()+"'"
				+ ",rectilevel='"+entity.getRectilevel()+"'"
				+ ",rectitype='"+entity.getRectitype()+"'"
				+ ",danwei='"+entity.getDanwei()+"'"
				+ ",leader='"+entity.getLeader()+"'"
				+ ",leadername='"+entity.getLeadername()+"'"
				+ ",leaderokdate='"+entity.getLeaderokdate()+"'"
				+ ",leaderokmessage='"+entity.getLeaderokmessage()+"'"
				+ ",manager='"+entity.getManager()+"'"
				+ ",managername='"+entity.getManagername()+"'"
				+ ",managerokdate='"+entity.getManagerokdate()+"'"
				+ ",managerokmessage='"+entity.getManagerokmessage()+"'"
				+ ",geofficer='"+entity.getGeofficer()+"'"
				+ ",geofficername='"+entity.getGeofficername()+"'"
				+ ",geofficerokdate='"+entity.getGeofficerokdate()+"'"
				+ ",geofficerokmessage='"+entity.getGeofficerokmessage()+"'"
				+ ",townofficer='"+entity.getTownofficer()+"'"
				+ ",townofficername='"+entity.getTownofficername()+"'"
				+ ",townofficerokdate='"+entity.getTownofficerokdate()+"'"
				+ ",townofficerokmessage='"+entity.getTownofficerokmessage()+"'"
				+ ",cityofficer='"+entity.getCityofficer()+"'"
				+ ",cityofficername='"+entity.getCityofficername()+"'"
				+ ",cityofficerokdate='"+entity.getCityofficerokdate()+"'"
				+ ",cityofficerokmessage='"+entity.getCityofficerokmessage()+"'"
				+ ",safetymanager='"+entity.getSafetymanager()+"'"
				+ ",safetymanagername='"+entity.getSafetymanagername()+"'"
				+ ",safetymanagerokdate='"+entity.getSafetymanagerokdate()+"'"
				+ ",safetymanagerokmessage='"+entity.getSafetymanagerokmessage()+"'"
				+ ",deleteflg='notdeleted'"
				+ ",starterokdate='"+ entity.getStarterokdate()+"'"
				+ ",starterokmessage='"+ entity.getStarterokmessage()+"'"

				+ " where javaid='"+entity.getJavaid()+"'";
	
		logger.info("["+this.getClass().getName()+"][insert][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][update][end]");
		return entity.getJavaid();
	}
	
	public String delete(RectiNewEntity entity,HttpServletRequest request) {
		logger.info("["+this.getClass().getName()+"][delete][start]");
		String sql = "update rectinew set "
				+ " deleteflg='deleted'"
				+ ",deletedby='"+request.getSession().getAttribute("id")+"'"
				+ " where javaid='"+entity.getJavaid()+"'";
	
		logger.info("["+this.getClass().getName()+"][delete][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][delete][end]");
		return entity.getJavaid();
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
		
		String strAc = "recti.ac = '"+ac+"'";
		
		logger.info("["+this.getClass().getName()+"][getByUserId][listRectiAc]:"+listRectiAc);
		if (!"".equals(listRectiAc)) {
			if ("this_month".equals(listRectiAc)){
				strAc = "recti.ac = '"+ac+"'";
			} else if ("last_month".equals(listRectiAc)){
				ac = df3.format(common.getLastMonthDate(date));
				strAc = "recti.ac = '"+ac+"'";
			} else if ("this_year".equals(listRectiAc)){
				strAc = "recti.ac like '"+ac.substring(0,4)+"%'";
			}
			
		}
		String sql = "SELECT  recti.javaid "
					+ ",recti.ac"
					+ ",recti.taskname"
					+ ",task.dbid_ taskid"
					+ ",task.name_ taskstatus"
					+ ",recti.processid"
					+ ",recti.starter"
					+ ",recti.no"
					+ ",recti.danwei"
					+ ",recti.name"
					+ ",recti.location"
					+ ",recti.checkdate"
					+ ",recti.content"
					+ ",recti.rectilevel"
					+ ",recti.rectitype "
					+ ",recti.leader"
					+ ",recti.safetymanagername "
					+ " from rectinew recti "
					+ " left join JBPM4_TASK task on recti.processid=task.execution_id_ "
					
					+ " where "
					+ strAc
					+ " and recti.deleteflg = 'notdeleted'";
					
					if (s_searchForAllFlg.contains("所有人隐患信息")) {
						//可以查看所有用户录入的数据
					} else {
						sql = sql 
						+ "  and (recti.starter='"+userId+"' "
						+ " or recti.leader='"+userId+"'"
						+ " or recti.manager='"+userId+"'"
						+ " or recti.geofficer='"+userId+"'"
						+ " or recti.townofficer='"+userId+"'"
						+ " or recti.cityofficer='"+userId+"'"
						+ " or recti.safetymanager='"+userId+"')";
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

					
				    sql=sql + " order by right(javaid,14) desc ";
				    
		logger.info("["+this.getClass().getName()+"][getByUserId][SQL]"+sql);
		
		//List<RectiNewEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(RectiNewEntity.class));
		Pagination pag = new Pagination( sql,  intPage,  intPageSize,namedParameterJdbcTemplate);
		logger.info("["+this.getClass().getName()+"][getByUserId][end]");
		return pag;
	}
	
}
