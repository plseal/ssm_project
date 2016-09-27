
package com.lin.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lin.entity.AnalysisEntity;
@Repository
public class AnalysisDao
{
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static Logger logger = Logger.getLogger(AnalysisDao.class);
	
	public AnalysisDao()
	{
	}

	

	public List <AnalysisEntity> getAllAnalysis_rectiNew(AnalysisEntity entity){
		logger.info("["+this.getClass().getName()+"][getAllAnalysis_rectiNew][start]");
		//按单位（部门）统计
		String sql = " select danwei,count(1) as danwei_cnt from rectinew where deleteflg ='notdeleted' group by danwei";
		if ("按地点统计".equals(entity.getAnalysistype())) {
			sql = " select location,count(1) as location_cnt_sum from rectinew where deleteflg ='notdeleted' group by location";
		} else if ("按隐患类型统计".equals(entity.getAnalysistype())) {
			sql = " select rectitype,count(1) as rectitype_cnt from rectinew where deleteflg ='notdeleted' group by rectitype";
		} else if ("按隐患等级统计".equals(entity.getAnalysistype())) {
			sql = " select rectilevel,count(1) as rectilevel_cnt from rectinew where deleteflg ='notdeleted' group by rectilevel";
		}
		
		logger.info("["+this.getClass().getName()+"][getAllAnalysis_rectiNew][SQL]"+sql);
		List<AnalysisEntity> list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(AnalysisEntity.class));
		logger.info("["+this.getClass().getName()+"][getAllAnalysis_rectiNew][end]");
		return list;
	}
	public List <AnalysisEntity> getAllAnalysis_rectiCheck(AnalysisEntity entity){
		logger.info("["+this.getClass().getName()+"][getAllAnalysis_rectiCheck][start]");
		//按单位（部门）统计
		String sql = " select danwei,count(1) as danwei_cnt from recticheck where deleteflg ='notdeleted' group by danwei";
		if ("按地点统计".equals(entity.getAnalysistype())) {
			sql = " ";
			sql = sql + " SELECT T1.location,";
			sql = sql + " sum(T1.location_cnt) as location_cnt_sum";
			sql = sql + " from (";
			sql = sql + " SELECT";
			sql = sql + " location1 as location,";
			sql = sql + " count(1) AS location_cnt";
			sql = sql + " FROM";
			sql = sql + " recticheck";
			sql = sql + " WHERE";
			sql = sql + " deleteflg = 'notdeleted'";
			sql = sql + " and location1 is not null";
			sql = sql + " GROUP BY";
			sql = sql + " location1";
			sql = sql + " UNION";
			sql = sql + " SELECT";
			sql = sql + " location2 as location,";
			sql = sql + " count(1) AS location_cnt";
			sql = sql + " FROM";
			sql = sql + " recticheck";
			sql = sql + " WHERE";
			sql = sql + " deleteflg = 'notdeleted'";
			sql = sql + " and location2 is not null";
			sql = sql + " GROUP BY";
			sql = sql + " location2";
			sql = sql + " UNION";
			sql = sql + " SELECT";
			sql = sql + " location3 as location,";
			sql = sql + " count(1) AS location_cnt";
			sql = sql + " FROM";
			sql = sql + " recticheck";
			sql = sql + " WHERE";
			sql = sql + " deleteflg = 'notdeleted'";
			sql = sql + " and location3 is not null";
			sql = sql + " GROUP BY";
			sql = sql + " location3";
			sql = sql + " ) T1 ";
			sql = sql + " GROUP by T1.location";


			
			
		} else if ("按隐患类型统计".equals(entity.getAnalysistype())) {
			sql = " select rectitype,count(1) as rectitype_cnt from recticheck where deleteflg ='notdeleted' group by rectitype";
		} else if ("按隐患等级统计".equals(entity.getAnalysistype())) {
			sql = " select rectilevel,count(1) as rectilevel_cnt from recticheck where deleteflg ='notdeleted' group by rectilevel";
		}
		logger.info("["+this.getClass().getName()+"][getAllAnalysis_rectiCheck][SQL]"+sql);
		List<AnalysisEntity> list = namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper(AnalysisEntity.class));
		logger.info("["+this.getClass().getName()+"][getAllAnalysis_rectiCheck][end]");
		return list;
	}
	
}
