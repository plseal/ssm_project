package com.lin.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lin.entity.LocationEntity;
import com.lin.entity.UserEntity;
@Repository
public class LocationDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static Logger logger = Logger.getLogger(LocationDao.class);
	public LocationDao()
	{
	}


	public List<LocationEntity> getAll(){
		logger.info("["+this.getClass().getName()+"][getAll][start]");
		String sql =  "SELECT id,name,orderid  FROM t_location where used = '地点' order by orderid";
		logger.info("["+this.getClass().getName()+"][getAll][SQL]"+sql);
		List<LocationEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		logger.info("["+this.getClass().getName()+"][getAll][end]");
		return list;
	}
	public List<LocationEntity> getYinhuanType(){
		logger.info("["+this.getClass().getName()+"][getYinhuanType][start]");
		String sql =  "SELECT id,name,orderid  FROM t_location where used = '隐患类型' order by orderid";
		logger.info("["+this.getClass().getName()+"][getYinhuanType][SQL]"+sql);
		List<LocationEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		logger.info("["+this.getClass().getName()+"][getYinhuanType][end]");
		return list;
	}
	public List<LocationEntity> getBeCheckedBu(){
		logger.info("["+this.getClass().getName()+"][getBeCheckedBu][start]");
		String sql =  "SELECT id,name,orderid  FROM t_location where used = '受检部门' order by orderid";
		logger.info("["+this.getClass().getName()+"][getBeCheckedBu][SQL]"+sql);
		List<LocationEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(UserEntity.class));
		logger.info("["+this.getClass().getName()+"][getBeCheckedBu][end]");
		return list;
	}
}
