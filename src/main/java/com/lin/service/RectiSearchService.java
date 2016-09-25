package com.lin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.lin.entity.RectiSearchEntity;
import com.lin.util.common;
@Service
public class RectiSearchService
{

	public RectiSearchService()
	{
	}

	public HttpServletRequest setRequestFromEntity(HttpServletRequest request,RectiSearchEntity entity){
		
		request.setAttribute("s_danwei", entity.getS_danwei());
		request.setAttribute("s_location", entity.getS_location());
		request.setAttribute("s_rectitype", entity.getS_rectitype());
		request.setAttribute("s_rectilevel", entity.getS_rectilevel());
		request.setAttribute("s_checkdate", entity.getS_checkdate());
		request.setAttribute("s_searchForAllFlg", entity.getS_searchForAllFlg());
		request.setAttribute("s_zhiwei", entity.getS_zhiwei());

		

		return request;
	}
	public RectiSearchEntity setEntityFromRequest(HttpServletRequest request,RectiSearchEntity entity){
				
		String s_danwei = common.toBlank(request.getParameter("s_danwei"));
		String s_location = common.toBlank(request.getParameter("s_location"));
		String s_rectitype = common.toBlank(request.getParameter("s_rectitype"));
		String s_rectilevel = common.toBlank(request.getParameter("s_rectilevel"));
		String s_checkdate = common.toBlank(request.getParameter("s_checkdate"));
		String s_searchForAllFlg = common.toBlank(request.getParameter("s_searchForAllFlg"));
		String s_zhiwei = common.toBlank(request.getParameter("s_zhiwei"));
		

		
		
		entity.setS_danwei(s_danwei);
		entity.setS_checkdate(s_checkdate);
		entity.setS_rectitype(s_rectitype);
		entity.setS_location(s_location);
		entity.setS_rectilevel(s_rectilevel);
		entity.setS_searchForAllFlg(s_searchForAllFlg);

		entity.setS_zhiwei(s_zhiwei);
		
		return entity;
	}

}
