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

import com.lin.entity.DanweiEntity;
import com.lin.entity.RectiNewEntity;
import com.lin.entity.UserEntity;
import com.lin.util.common;
@Repository
public class DanweiDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static Logger logger = Logger.getLogger(DanweiDao.class);
	public DanweiDao()
	{
	}


	public List<DanweiEntity> getAll(){
		logger.info("["+this.getClass().getName()+"][getAll][start]");
		String sql =  "SELECT id,name,orderid  FROM t_danwei order by orderid";
		logger.info("["+this.getClass().getName()+"][getAll][SQL]"+sql);
		List<DanweiEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(DanweiEntity.class));
		logger.info("["+this.getClass().getName()+"][getAll][end]");
		return list;
	}
	
	public DanweiEntity getShortName(String strFullName){
		logger.info("["+this.getClass().getName()+"][getShortName][start]");
		String sql =  "SELECT id,name,orderid  FROM t_danwei where name ='" + strFullName + "' order by orderid";
		logger.info("["+this.getClass().getName()+"][getShortName][SQL]"+sql);
		RowMapper<DanweiEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(DanweiEntity.class);
		DanweiEntity entity = (DanweiEntity)namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, rm);
		logger.info("["+this.getClass().getName()+"][getShortName][end]");
		return entity;
	}
	public DanweiEntity getFullName(String strShortName){
		logger.info("["+this.getClass().getName()+"][getFullName][start]");
		String sql =  "SELECT id,name,orderid  FROM t_danwei where id ='" + strShortName + "' order by orderid";
		
		logger.info("["+this.getClass().getName()+"][getFullName][SQL]"+sql);
		RowMapper<DanweiEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(DanweiEntity.class);
		DanweiEntity entity = (DanweiEntity)namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, rm);
		
		logger.info("["+this.getClass().getName()+"][getFullName][end]");
		return entity;
	}
	public DanweiEntity getFullNameByOrderId(String strOrderId){
		logger.info("["+this.getClass().getName()+"][getFullNameByOrderId][start]");
		String sql =  "SELECT id,name,orderid  FROM t_danwei where orderid ='" + strOrderId + "' order by orderid";
		
		logger.info("["+this.getClass().getName()+"][getFullNameByOrderId][SQL]"+sql);
		RowMapper<DanweiEntity> rm = ParameterizedBeanPropertyRowMapper.newInstance(DanweiEntity.class);
		DanweiEntity entity = (DanweiEntity)namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, rm);
		
		logger.info("["+this.getClass().getName()+"][getFullNameByOrderId][end]");
		return entity;
	}
}
