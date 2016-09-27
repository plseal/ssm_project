
package com.lin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.lin.entity.RectiDangerEntity;
import com.lin.util.Pagination;
import com.lin.util.common;
@Repository
public class RectiDangerDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static Logger logger = Logger.getLogger(RectiDangerDao.class);
	
	public RectiDangerDao()
	{
	}

	public String insert(RectiDangerEntity entity){
		logger.info("["+this.getClass().getName()+"][insert][start]");
		String sql = "insert into t_rectidanger values("
				+ "'"+ entity.getJavaid()+"'"
				+ ",'"+ entity.getAc()+"'"
				+ ",'"+ entity.getTaskid()+"'"
				+ ",'"+ entity.getTaskname()+"'"
				+ ",'"+ entity.getProcessid()+"'"
				+ ",'"+ entity.getNo()+"'"
				+ ",'"+ entity.getDanwei()+"'"
				+ ",'"+ entity.getStarter()+"'"
				+ ",'"+ entity.getDangertype()+"'"
				+ ",'"+ entity.getStartdate()+"'"
				+ ",'"+ entity.getEnddate()+"'"
				+ ",'"+ entity.getLocation()+"'"
				+ ",'"+ entity.getOperator()+"'"
				+ ",'"+ entity.getPrecaution()+"'"
				+ ",'"+ entity.getPrecautionchecker()+"'"
				+ ",'"+ entity.getInform()+"'"
				+ ",'"+ entity.getInformer()+"'"
				+ ",'"+ entity.getLeader()+"'"
				+ ",'"+ entity.getLeadername()+"'"
				+ ",'"+ entity.getLeaderokdate()+"'"
				+ ",'"+ entity.getLeaderokmessage()+"'"
				+ ",'"+ entity.getManager()+"'"
				+ ",'"+ entity.getManagername()+"'"
				+ ",'"+ entity.getManagerokdate()+"'"
				+ ",'"+ entity.getManagerokmessage()+"'"
				+ ",'"+ entity.getTownofficer()+"'"
				+ ",'"+ entity.getTownofficername()+"'"
				+ ",'"+ entity.getTownofficerokdate()+"'"
				+ ",'"+ entity.getTownofficerokmessage()+"'"
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
				+ ",'"+ entity.getStarterokdate()+"'"
				+ ",'"+ entity.getStarterokmessage()+"'"

				+ ")";
		logger.info("["+this.getClass().getName()+"][insert][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][insert][end]");
		return entity.getJavaid();
	}

	public RectiDangerEntity getById(String javaid)
	{
		logger.info("["+this.getClass().getName()+"][getById][start]");
		String sql = "SELECT  recti.* "
					+ " FROM t_rectidanger  recti "
					+ " WHERE  recti.javaid='"+javaid+"'"
					+ " and recti.deleteflg ='notdeleted' ";
		
		logger.info("["+this.getClass().getName()+"][getById][SQL]"+sql);
		RowMapper<RectiDangerEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(RectiDangerEntity.class);
		
		RectiDangerEntity sp = (RectiDangerEntity)namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, rm);
		logger.info("["+this.getClass().getName()+"][getById][end]");
		return sp;
	}
	public Integer checkIdExist(String id){
		logger.info("["+this.getClass().getName()+"][checkIdExist][start]");
		String sql = "SELECT count(1) as CNT from t_rectidanger WHERE javaid='"+id+"' and deleteflg ='notdeleted' ";
		
		logger.info("["+this.getClass().getName()+"][checkIdExist][SQL]"+sql);
		
		Integer cnt = namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql);
		logger.info("["+this.getClass().getName()+"][checkIdExist][cnt]:"+cnt);
		logger.info("["+this.getClass().getName()+"][checkIdExist][end]");
		return cnt;
	}
	public String update(RectiDangerEntity entity)
	{
		logger.info("["+this.getClass().getName()+"][update][start]");
		String sql = "update t_rectidanger set "
				
				+ "ac='"+ entity.getAc()+"'"
				+ ",taskid='"+ entity.getTaskid()+"'"
				+ ",taskname='"+ entity.getTaskname()+"'"
				+ ",processid='"+ entity.getProcessid()+"'"
				+ ",no='"+ entity.getNo()+"'"
				+ ",danwei='"+ entity.getDanwei()+"'"
				+ ",starter='"+ entity.getStarter()+"'"
				+ ",dangertype='"+ entity.getDangertype()+"'"
				+ ",startdate='"+ entity.getStartdate()+"'"
				+ ",enddate='"+ entity.getEnddate()+"'"
				+ ",location='"+ entity.getLocation()+"'"
				+ ",operator='"+ entity.getOperator()+"'"
				+ ",precaution='"+ entity.getPrecaution()+"'"
				+ ",precautionchecker='"+ entity.getPrecautionchecker()+"'"
				+ ",inform='"+ entity.getInform()+"'"
				+ ",informer='"+ entity.getInformer()+"'"
				+ ",leader='"+ entity.getLeader()+"'"
				+ ",leadername='"+ entity.getLeadername()+"'"
				+ ",leaderokdate='"+ entity.getLeaderokdate()+"'"
				+ ",leaderokmessage='"+ entity.getLeaderokmessage()+"'"
				+ ",manager='"+ entity.getManager()+"'"
				+ ",managername='"+ entity.getManagername()+"'"
				+ ",managerokdate='"+ entity.getManagerokdate()+"'"
				+ ",managerokmessage='"+ entity.getManagerokmessage()+"'"
				+ ",townofficer='"+ entity.getTownofficer()+"'"
				+ ",townofficername='"+ entity.getTownofficername()+"'"
				+ ",townofficerokdate='"+ entity.getTownofficerokdate()+"'"
				+ ",townofficerokmessage='"+ entity.getTownofficerokmessage()+"'"
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
				//+ ",deleteflg='"+ entity.getDeleteflg()+"'"
				//+ ",deletedby='"+ entity.getDeletedby()+"'"
				+ ",starterokdate='"+ entity.getStarterokdate()+"'"
				+ ",starterokmessage='"+ entity.getStarterokmessage()+"'"


				+ " where javaid='"+entity.getJavaid()+"'";
	
		logger.info("["+this.getClass().getName()+"][insert][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][update][end]");
		return entity.getJavaid();
	}
	
	public String delete(RectiDangerEntity entity,HttpServletRequest request) {
		logger.info("["+this.getClass().getName()+"][delete][start]");
		String sql = "update t_rectidanger set "
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
			,String startdate
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
		String sql = "SELECT  "
					+ "task.dbid_ taskid"
					+ ",task.name_ taskstatus"
					+ ",recti.javaid "
					+ ",recti.no "
					+ ",recti.danwei "
					+ ",recti.startdate "
					+ ",recti.enddate "
					+ ",recti.location "
					+ ",recti.taskname "
					+ ",recti.processid "
					
                    + " from t_rectidanger recti "
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
						sql = sql + " and recti.dangertype='"+rectitype+"'";	
					}

					if (!"".equals(startdate)){
						sql = sql + " and recti.startdate='"+startdate+"'";	
					}
				    sql=sql + " order by right(javaid,14) desc ";
		logger.info("["+this.getClass().getName()+"][getByUserId][SQL]"+sql);
		
		//List<RectiDangerEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(RectiDangerEntity.class));
		Pagination pag = new Pagination( sql,  intPage,  intPageSize,namedParameterJdbcTemplate);
		logger.info("["+this.getClass().getName()+"][getByUserId][end]");
		return pag;
	}
	
}
