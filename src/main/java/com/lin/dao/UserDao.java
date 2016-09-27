package com.lin.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.lin.entity.RectiSearchEntity;
import com.lin.entity.UserEntity;
import com.lin.util.common;
@Repository
public class UserDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static Logger logger = Logger.getLogger(UserDao.class);
	public UserDao()
	{
	}

	public String insert(UserEntity entity){
		
		
		logger.info("["+this.getClass().getName()+"][insert][start]");
		String sql = "insert into user values("
				+ "'"+ entity.getId()+"'"
				+ ",'"+ entity.getPas()+"'"
				+ ",'"+ entity.getName()+"'"
				+ ",'"+ entity.getPosition()+"'"
				+ ",'"+ entity.getBumen()+"'"
				+ ",'"+ entity.getRole()+"'"
				+ ",'"+ entity.getNgnameflg()+"'"
				+ ",'"+ entity.getLeader()+"'"
				+ ",'"+ entity.getManager()+"'"
				+ ",'"+ entity.getGeofficer()+"'"
				+ ",''"
				+ ")";
		logger.info("["+this.getClass().getName()+"][insert][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][insert][end]");
	
		return entity.getId();
	}

	public String update(UserEntity entity){
			
		logger.info("["+this.getClass().getName()+"][update][start]");
		String sql = "update user set "
				+ "id='"+ entity.getId()+"'"
				+ ",pas='"+ entity.getPas()+"'"
				+ ",name='"+ entity.getName()+"'"
				+ ",position='"+ entity.getPosition()+"'"
				+ ",bumen='"+ entity.getBumen()+"'"
				+ ",role='"+ entity.getRole()+"'"
				+ ",ngnameflg='"+ entity.getNgnameflg()+"'"
				+ ",leader='"+ entity.getLeader()+"'"
				+ ",manager='"+ entity.getManager()+"'"
				+ ",geofficer='"+ entity.getGeofficer()+"'"
				+ " where id='"+entity.getId()+"'";
	
		logger.info("["+this.getClass().getName()+"][insert][SQL]"+sql);
		namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		logger.info("["+this.getClass().getName()+"][update][end]");
		return entity.getId();
	}

	public String delete(UserEntity entity)
	{
		String sql = "delete from user where id=:id ";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", entity.getId());
		logger.info("["+this.getClass().getName()+"][delete][SQL]"+common.getSQL(sql,namedParameters));
		
		namedParameterJdbcTemplate.update(sql, namedParameters);
		return entity.getId();
	}

	public UserEntity getById(String id){
		logger.info("["+this.getClass().getName()+"][getById][start]");
		String sql = "SELECT * from user WHERE id=:id ";
		
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		logger.info("["+this.getClass().getName()+"][getById][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getById][end]");
		return ue;
	}
	
	public UserEntity getGeofficerById(String id){
		logger.info("["+this.getClass().getName()+"][getGeofficerById][start]");
		String sql = "select * from user where user_danwei IN (SELECT user_danwei from user WHERE id=:id ) AND role like '%geofficer%'";
		
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		logger.info("["+this.getClass().getName()+"][getGeofficerById][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getGeofficerById][end]");
		return ue;
	}
	public UserEntity getTownofficerByDanwei(String danwei){
		logger.info("["+this.getClass().getName()+"][getTownofficerByDanwei][start]");
		String sql = "select * from user where user_danwei = :danwei AND role like '%townofficer%'";
		if ("市局公司".equals(danwei)) {
			 sql = "select * from user where user_danwei = :danwei AND role like '%cityofficer%'";
		}
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("danwei", danwei);
		logger.info("["+this.getClass().getName()+"][getTownofficerByDanwei][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getTownofficerByDanwei][end]");
		return ue;
	}
	public UserEntity getGeofficerByDanwei(String danwei){
		logger.info("["+this.getClass().getName()+"][getGeofficerByDanwei][start]");
		String sql = "select * from user where user_danwei =:danwei AND role like '%geofficer%'";
		
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("danwei", danwei);
		logger.info("["+this.getClass().getName()+"][getGeofficerByDanwei][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getGeofficerByDanwei][end]");
		return ue;
	}
	public UserEntity getSafetymanagerById(String id){
		logger.info("["+this.getClass().getName()+"][getSafetymanagerById][start]");
		String sql = "select * from user where user_danwei IN (SELECT user_danwei from user WHERE id=:id ) AND role like '%safetyofficer%'";
		
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		logger.info("["+this.getClass().getName()+"][getSafetymanagerById][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getSafetymanagerById][end]");
		return ue;
	}
	public UserEntity getSafetymanagerByDanwei(String danwei){
		logger.info("["+this.getClass().getName()+"][getSafetymanagerByDanwei][start]");
		String sql = "select * from user where user_danwei =:danwei AND role like '%safetyofficer%'";
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("danwei", danwei);
		logger.info("["+this.getClass().getName()+"][getSafetymanagerByDanwei][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getSafetymanagerByDanwei][end]");
		return ue;
	}
	
	public UserEntity getTownofficerById(String id){
		logger.info("["+this.getClass().getName()+"][getTownofficerById][start]");
		String sql = "select * from user where user_danwei IN (SELECT user_danwei from user WHERE id=:id ) AND role like '%townofficer%'";
		
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		logger.info("["+this.getClass().getName()+"][getTownofficerById][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getTownofficerById][end]");
		return ue;
	}
	public UserEntity getCityofficerById(String id){
		logger.info("["+this.getClass().getName()+"][getCityofficerById][start]");
		String sql = "select * from user where (user_danwei IN (SELECT user_danwei from user WHERE id=:id ) OR user_danwei = '市局公司') AND role like '%cityofficer%'";
		
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		logger.info("["+this.getClass().getName()+"][getCityofficerById][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getCityofficerById][end]");
		return ue;
	}
	public UserEntity getApprover1ById(String id){
		logger.info("["+this.getClass().getName()+"][getApprover1ById][start]");
		String sql = "select * from user where user_danwei IN (SELECT user_danwei from user WHERE id=:id ) AND role like '%approver1%'";
		
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		logger.info("["+this.getClass().getName()+"][getApprover1ById][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getApprover1ById][end]");
		return ue;
	}
	public UserEntity getApprover2ById(String id){
		logger.info("["+this.getClass().getName()+"][getApprover2ById][start]");
		String sql = "select * from user where user_danwei IN (SELECT user_danwei from user WHERE id=:id ) AND role like '%approver2%'";
		
		RowMapper<UserEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(UserEntity.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		logger.info("["+this.getClass().getName()+"][getApprover2ById][SQL]"+common.getSQL(sql,namedParameters));
		UserEntity ue = (UserEntity)namedParameterJdbcTemplate.queryForObject(sql, namedParameters, rm);
		logger.info("["+this.getClass().getName()+"][getApprover2ById][end]");
		return ue;
	}
	public List<UserEntity> getUserListByLeader(String user_danwei){
		logger.info("["+this.getClass().getName()+"][getUserListByLeader][start]");
		String sql = "SELECT * from user WHERE role like'%leader%' and user_danwei='"+user_danwei+"'";
		logger.info("["+this.getClass().getName()+"][getUserListByLeader][SQL]"+sql);
		List<UserEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		logger.info("["+this.getClass().getName()+"][getUserListByLeader][end]");
		return list;
	}
	public List<UserEntity> getUserListByManager(String user_danwei){
		logger.info("["+this.getClass().getName()+"][getUserListByManager][start]");
		//String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		String sql = "SELECT * from user WHERE role like'%manager%' and user_danwei='"+user_danwei+"'";
				
		logger.info("["+this.getClass().getName()+"][getUserListByManager][SQL]"+sql);
		
		
		List<UserEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		
			
		logger.info("["+this.getClass().getName()+"][getUserListByManager][end]");
		return list;
	}
	public List<UserEntity> getLevel2ListByDanweiFullName(String user_danwei){
		logger.info("["+this.getClass().getName()+"][getLevel2ListByDanweiFullName][start]");
		//String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		String sql = "SELECT * from user WHERE user_level ='2'  "
				+ " order by user_level_orderid";
				
		logger.info("["+this.getClass().getName()+"][getLevel2ListByDanweiFullName][SQL]"+sql);
		
		
		List<UserEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		
			
		logger.info("["+this.getClass().getName()+"][getLevel2ListByDanweiFullName][end]");
		return list;
	}
	public List<UserEntity> getLevel3ListByDanweiFullName(String user_danwei){
		logger.info("["+this.getClass().getName()+"][getLevel3ListByDanweiFullName][start]");
		//String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		String sql = "SELECT * from user WHERE user_level ='3'  "
				+ " order by user_level_orderid";
				
		logger.info("["+this.getClass().getName()+"][getLevel3ListByDanweiFullName][SQL]"+sql);
		
		
		List<UserEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		
			
		logger.info("["+this.getClass().getName()+"][getLevel3ListByDanweiFullName][end]");
		return list;
	}
	public List<UserEntity> getUserListByTownOfficer(String user_danwei){
		logger.info("["+this.getClass().getName()+"][getUserListByTownOfficer][start]");
		//String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		String sql = "SELECT * from user WHERE role like'%townofficer%' or role like'%cityofficer%'";
				
		logger.info("["+this.getClass().getName()+"][getUserListByTownOfficer][SQL]"+sql);
		List<UserEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		logger.info("["+this.getClass().getName()+"][getUserListByTownOfficer][end]");
		return list;
	}
	public List<UserEntity> getUserListByNGname(){
		logger.info("["+this.getClass().getName()+"][getUserListByNGname][start]");
		String sql = "SELECT * from user WHERE ngnameflg like'%NG%' order by user_danwei";
		logger.info("["+this.getClass().getName()+"][getUserListByNGname][SQL]"+sql);
		List<UserEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		logger.info("["+this.getClass().getName()+"][getUserListByNGname][end]");
		return list;
	}
	public Integer checkIdExist(String id){
		logger.info("["+this.getClass().getName()+"][checkIdExist][start]");
		String sql = "SELECT count(1) as CNT from user WHERE id=:id ";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		logger.info("["+this.getClass().getName()+"][checkIdExist][SQL]"+common.getSQL(sql,namedParameters));
		Integer cnt = namedParameterJdbcTemplate.queryForInt(sql, namedParameters);
		logger.info("["+this.getClass().getName()+"][checkIdExist][end]");
		return cnt;
	}
	public Integer checkPas(String id,String pas){
		logger.info("["+this.getClass().getName()+"][checkPas][start]");
		String sql = "SELECT count(1) as CNT from user WHERE id=:id and pas=:pas";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		namedParameters.addValue("pas", pas);
		logger.info("["+this.getClass().getName()+"][checkPas][pas]"+pas);
		logger.info("["+this.getClass().getName()+"][checkPas][SQL]"+common.getSQL(sql,namedParameters));
		Integer cnt = namedParameterJdbcTemplate.queryForInt(sql, namedParameters);
		
		logger.info("["+this.getClass().getName()+"][checkPas][end]");
		return cnt;
	}

	public List<UserEntity> getUserListByDanwei(RectiSearchEntity entity){
		logger.info("["+this.getClass().getName()+"][getUserListByDanwei][start]");
		String sql =  "SELECT * from user where 1=1 ";
		if ("".equals(entity.getS_danwei()) && "".equals(entity.getS_zhiwei())) {
			sql = "SELECT * from user where 1=0";
		}
		if (!"".equals(entity.getS_danwei())) {
		sql = sql + "and user_danwei = '" + entity.getS_danwei() + "'";
		}
		if (!"".equals(entity.getS_zhiwei())) {
			if("市局局长".equals(entity.getS_zhiwei())){
				sql =  sql + " and role like '%cityofficer%'";
			} else if ("县局局长".equals(entity.getS_zhiwei())) {
				sql =  sql + " and role like '%townofficer%'";
			} else if ("分管领导".equals(entity.getS_zhiwei())) {
				sql =  sql + " and role like '%manager%'";
			} else if ("办公室主任".equals(entity.getS_zhiwei())) {
				sql =  sql + " and role like '%geofficer%'";
			} else if ("部门负责人".equals(entity.getS_zhiwei())) {
				sql =  sql + " and role like '%leader%'";
			} else if ("安全管理员".equals(entity.getS_zhiwei())) {
				sql =  sql + " and role like '%safetyofficer%'";
			} else if ("安全管理部门负责人".equals(entity.getS_zhiwei())) {
				sql =  sql + " and role like '%approver1%'";
			} else if ("分管安全领导".equals(entity.getS_zhiwei())) {
				sql =  sql + " and role like '%approver2%'";
			} else if ("员工".equals(entity.getS_zhiwei())) {
				sql =  sql + " and role like '%yuangong%'";
			}
			
		}
		logger.info("["+this.getClass().getName()+"][getUserListByDanwei][SQL]"+sql);
		List<UserEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		logger.info("["+this.getClass().getName()+"][getUserListByDanwei][end]");
		return list;
	}
}
