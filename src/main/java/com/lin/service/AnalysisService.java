

package com.lin.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.lin.dao.AnalysisDao;
import com.lin.entity.AnalysisEntity;
import com.lin.util.common;
@Service
public class AnalysisService
{
	@Resource(name="analysisDao")
	private AnalysisDao analysisDao;




	public List<AnalysisEntity> getAllAnalysis_rectiNew(AnalysisEntity entity){
		return analysisDao.getAllAnalysis_rectiNew(entity);
	}
	
	public List<AnalysisEntity> getAllAnalysis_rectiCheck(AnalysisEntity entity){
		return analysisDao.getAllAnalysis_rectiCheck(entity);
	}
	
	public HttpServletRequest setRequestFromEntity(HttpServletRequest request,AnalysisEntity entitySaved){
		
		request.setAttribute("rectitype", entitySaved.getRectitype());
		request.setAttribute("rectitype_cnt", entitySaved.getRectitype_cnt());
		request.setAttribute("rectilevel", entitySaved.getRectilevel());
		request.setAttribute("rectilevel_cnt", entitySaved.getRectilevel_cnt());
		request.setAttribute("danwei", entitySaved.getDanwei());
		request.setAttribute("danwei_cnt", entitySaved.getDanwei_cnt());
		request.setAttribute("location", entitySaved.getLocation());
		request.setAttribute("location_cnt", entitySaved.getLocation_cnt());
		request.setAttribute("location_cnt_sum", entitySaved.getLocation_cnt_sum());
		request.setAttribute("analysisfrom", entitySaved.getAnalysisfrom());
		request.setAttribute("analysistype", entitySaved.getAnalysistype());
		request.setAttribute("analysischart", entitySaved.getAnalysischart());

		

		return request;
	}
	public AnalysisEntity setEntityFromRequest(HttpServletRequest request,AnalysisEntity entity){
				
		String rectitype = common.toBlank(request.getParameter("rectitype"));
		String rectitype_cnt = common.toBlank(request.getParameter("rectitype_cnt"));
		String rectilevel = common.toBlank(request.getParameter("rectilevel"));
		String rectilevel_cnt = common.toBlank(request.getParameter("rectilevel_cnt"));
		String danwei = common.toBlank(request.getParameter("danwei"));
		String danwei_cnt = common.toBlank(request.getParameter("danwei_cnt"));
		String location = common.toBlank(request.getParameter("location"));
		String location_cnt = common.toBlank(request.getParameter("location_cnt"));
		String location_cnt_sum = common.toBlank(request.getParameter("location_cnt_sum"));
		String analysisfrom = common.toBlank(request.getParameter("analysisfrom"));
		String analysistype = common.toBlank(request.getParameter("analysistype"));
		String analysischart = common.toBlank(request.getParameter("analysischart"));

		
		
		entity.setRectitype(rectitype);
		entity.setRectitype_cnt(rectitype_cnt);
		entity.setRectilevel(rectilevel);
		entity.setRectilevel_cnt(rectilevel_cnt);
		entity.setDanwei(danwei);
		entity.setDanwei_cnt(danwei_cnt);
		entity.setLocation(location);
		entity.setLocation_cnt(location_cnt);
		entity.setLocation_cnt(location_cnt_sum);
		entity.setAnalysisfrom(analysisfrom);
		entity.setAnalysistype(analysistype);
		entity.setAnalysischart(analysischart);

		
		
		
		return entity;
	}
	
}
