

package com.lin.service;

import com.lin.dao.RectiNewDao;
import com.lin.entity.RectiNewEntity;
import com.lin.util.Pagination;
import com.lin.util.common;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
@Service
public class RectiNewService
{
	@Resource(name="rectiNewDao")
	private RectiNewDao rectiNewDao;

	public RectiNewService()
	{
	}

	public String update(RectiNewEntity entity)	{	
		return rectiNewDao.update(entity);
	}
	public String insert(RectiNewEntity entity){	
		return rectiNewDao.insert(entity);
	}
	public Integer checkIdExist(RectiNewEntity entity) {
		return rectiNewDao.checkIdExist(entity.getJavaid());
	}
	public String delete(RectiNewEntity entity,HttpServletRequest request) {
		return rectiNewDao.delete(entity,request);
		
	}

	public RectiNewEntity getById(String rid)
	{
		return rectiNewDao.getById(rid);
	}


	public Pagination getByUserId(String userId
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
			)
	{
		return rectiNewDao.getByUserId(userId
				,role
				,listRectiAc
				,danwei
				,location
				,rectitype
				,rectilevel
				,s_searchForAllFlg
				,checkdate
				,intPage
				,intPageSize
				);
	}
	
	public HttpServletRequest setRequestFromEntity(HttpServletRequest request,RectiNewEntity entity){
		request.setAttribute("javaid", entity.getJavaid());
		request.setAttribute("ac", entity.getAc());
		request.setAttribute("taskid", entity.getTaskid());
		request.setAttribute("taskname", entity.getTaskname());
		request.setAttribute("processid", entity.getProcessid());
		request.setAttribute("starter", entity.getStarter());
		request.setAttribute("no", entity.getNo());
		request.setAttribute("name", entity.getName());
		request.setAttribute("location", entity.getLocation());
		request.setAttribute("checkdate", entity.getCheckdate());
		request.setAttribute("content", entity.getContent());
		request.setAttribute("okstandard", entity.getOkstandard());
		request.setAttribute("okmeasure", entity.getOkmeasure());
		request.setAttribute("rectilevel", entity.getRectilevel());
		request.setAttribute("rectitype", entity.getRectitype());
		request.setAttribute("danwei", entity.getDanwei());
		request.setAttribute("leader", entity.getLeader());
		request.setAttribute("leadername", entity.getLeadername());
		request.setAttribute("leaderokdate", entity.getLeaderokdate());
		request.setAttribute("leaderokmessage", entity.getLeaderokmessage());
		request.setAttribute("manager", entity.getManager());
		request.setAttribute("managername", entity.getManagername());
		request.setAttribute("managerokdate", entity.getManagerokdate());
		request.setAttribute("managerokmessage", entity.getManagerokmessage());
		request.setAttribute("geofficer", entity.getGeofficer());
		request.setAttribute("geofficername", entity.getGeofficername());
		request.setAttribute("geofficerokdate", entity.getGeofficerokdate());
		request.setAttribute("geofficerokmessage", entity.getGeofficerokmessage());
		request.setAttribute("townofficer", entity.getTownofficer());
		request.setAttribute("townofficername", entity.getTownofficername());
		request.setAttribute("townofficerokdate", entity.getTownofficerokdate());
		request.setAttribute("townofficerokmessage", entity.getTownofficerokmessage());
		request.setAttribute("cityofficer", entity.getCityofficer());
		request.setAttribute("cityofficername", entity.getCityofficername());
		request.setAttribute("cityofficerokdate", entity.getCityofficerokdate());
		request.setAttribute("cityofficerokmessage", entity.getCityofficerokmessage());
		request.setAttribute("safetymanager", entity.getSafetymanager());
		request.setAttribute("safetymanagername", entity.getSafetymanagername());
		request.setAttribute("safetymanagerokdate", entity.getSafetymanagerokdate());
		request.setAttribute("safetymanagerokmessage", entity.getSafetymanagerokmessage());
		request.setAttribute("bumenfuzeren", entity.getBumenfuzeren());
		request.setAttribute("fenguanlingdao", entity.getFenguanlingdao());

		request.setAttribute("starterokdate", entity.getStarterokdate());
		request.setAttribute("starterokmessage", entity.getStarterokmessage());

		
		return request;
	}
	
	public RectiNewEntity setEntityFromRequest(HttpServletRequest request,RectiNewEntity entity){
		String javaid    = common.toBlank(request.getParameter("javaid"));
		String ac    = common.toBlank(request.getParameter("ac"));
		String taskid = common.toBlank(request.getParameter("taskid"));
		String taskname = common.toBlank(request.getParameter("taskname"));
		String processid = common.toBlank(request.getParameter("processid"));
		String starter = common.toBlank(request.getParameter("starter"));
		String no = common.toBlank(request.getParameter("no"));
		String name = common.toBlank(request.getParameter("name"));
		String location = common.toBlank(request.getParameter("location"));
		String checkdate = common.toBlank(request.getParameter("checkdate"));
		String content = common.toBlank(request.getParameter("content"));
		String okstandard = common.toBlank(request.getParameter("okstandard"));
		String okmeasure = common.toBlank(request.getParameter("okmeasure"));
		String rectilevel = common.toBlank(request.getParameter("rectilevel"));
		String rectitype = common.toBlank(request.getParameter("rectitype"));
		String danwei = common.toBlank(request.getParameter("danwei"));
		String leader = common.toBlank(request.getParameter("leader"));
		String leadername = common.toBlank(request.getParameter("leadername"));
		String leaderokdate = common.toBlank(request.getParameter("leaderokdate"));
		String leaderokmessage = common.toBlank(request.getParameter("leaderokmessage"));
		String manager = common.toBlank(request.getParameter("manager"));
		String managername = common.toBlank(request.getParameter("managername"));
		String managerokdate = common.toBlank(request.getParameter("managerokdate"));
		String managerokmessage = common.toBlank(request.getParameter("managerokmessage"));
		String geofficer = common.toBlank(request.getParameter("geofficer"));
		String geofficername = common.toBlank(request.getParameter("geofficername"));
		String geofficerokdate = common.toBlank(request.getParameter("geofficerokdate"));
		String geofficerokmessage = common.toBlank(request.getParameter("geofficerokmessage"));
		String townofficer = common.toBlank(request.getParameter("townofficer"));
		String townofficername = common.toBlank(request.getParameter("townofficername"));
		String townofficerokdate = common.toBlank(request.getParameter("townofficerokdate"));
		String townofficerokmessage = common.toBlank(request.getParameter("townofficerokmessage"));
		String cityofficer = common.toBlank(request.getParameter("cityofficer"));
		String cityofficername = common.toBlank(request.getParameter("cityofficername"));
		String cityofficerokdate = common.toBlank(request.getParameter("cityofficerokdate"));
		String cityofficerokmessage = common.toBlank(request.getParameter("cityofficerokmessage"));
		String safetymanager = common.toBlank(request.getParameter("safetymanager"));
		String safetymanagername = common.toBlank(request.getParameter("safetymanagername"));
		String safetymanagerokdate = common.toBlank(request.getParameter("safetymanagerokdate"));
		String safetymanagerokmessage = common.toBlank(request.getParameter("safetymanagerokmessage"));
		String bumenfuzeren = common.toBlank(request.getParameter("bumenfuzeren"));
		String fenguanlingdao = common.toBlank(request.getParameter("fenguanlingdao"));
		String starterokdate = common.toBlank(request.getParameter("starterokdate"));
		String starterokmessage = common.toBlank(request.getParameter("starterokmessage"));



		entity.setJavaid(javaid);
		entity.setAc(ac);
		entity.setTaskid(taskid);
		entity.setTaskname(taskname);
		entity.setProcessid(processid);
		entity.setStarter(starter);
		entity.setNo(no);
		entity.setName(name);
		entity.setLocation(location);
		entity.setCheckdate(checkdate);
		entity.setContent(content);
		entity.setOkstandard(okstandard);
		entity.setOkmeasure(okmeasure);
		entity.setRectilevel(rectilevel);
		entity.setRectitype(rectitype);
		entity.setDanwei(danwei);
		entity.setLeader(leader);
		entity.setLeadername(leadername);
		entity.setLeaderokdate(leaderokdate);
		entity.setLeaderokmessage(leaderokmessage);
		entity.setManager(manager);
		entity.setManagername(managername);
		entity.setManagerokdate(managerokdate);
		entity.setManagerokmessage(managerokmessage);
		entity.setGeofficer(geofficer);
		entity.setGeofficername(geofficername);
		entity.setGeofficerokdate(geofficerokdate);
		entity.setGeofficerokmessage(geofficerokmessage);
		entity.setTownofficer(townofficer);
		entity.setTownofficername(townofficername);
		entity.setTownofficerokdate(townofficerokdate);
		entity.setTownofficerokmessage(townofficerokmessage);
		entity.setCityofficer(cityofficer);
		entity.setCityofficername(cityofficername);
		entity.setCityofficerokdate(cityofficerokdate);
		entity.setCityofficerokmessage(cityofficerokmessage);
		entity.setSafetymanager(safetymanager);
		entity.setSafetymanagername(safetymanagername);
		entity.setSafetymanagerokdate(safetymanagerokdate);
		entity.setSafetymanagerokmessage(safetymanagerokmessage);
		entity.setBumenfuzeren(bumenfuzeren);
		entity.setFenguanlingdao(fenguanlingdao);
		entity.setStarterokdate(starterokdate);
		entity.setStarterokmessage(starterokmessage);

		
		return entity;
	}
	
}
