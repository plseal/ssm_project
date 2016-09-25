package com.lin.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lin.entity.StandardDatabaseEntity;
@Repository
public class StandardDatabaseDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public StandardDatabaseDao()
	{
	}



	public List<StandardDatabaseEntity> getAll()
	{
		StringBuffer sb = new StringBuffer("SELECT * from standarddatabase WHERE 1=1 ");
		System.out.println((new StringBuilder("SQL||||")).append(sb.toString()).append("||||").toString());
		List<StandardDatabaseEntity>  list = namedParameterJdbcTemplate.getJdbcOperations().query(sb.toString(), new BeanPropertyRowMapper(StandardDatabaseEntity.class));
		return list;
	}
}
