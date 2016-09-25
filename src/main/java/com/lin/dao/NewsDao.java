package com.lin.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lin.entity.NewsEntity;
import com.lin.entity.StandardDatabaseEntity;
@Repository
public class NewsDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static Logger logger = Logger.getLogger(NewsDao.class);
	public NewsDao()
	{
	}



	public List<NewsEntity> getAll()
	{
		logger.info("["+this.getClass().getName()+"][getAll][start]");
		String sql = "SELECT * from t_news WHERE 1=1 ";
		logger.info("["+this.getClass().getName()+"][getAll][SQL]"+sql);
		
		List<NewsEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(NewsEntity.class));
		
		logger.info("["+this.getClass().getName()+"][getAll][end]");
		return list;
	}
}
