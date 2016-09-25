package com.lin.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.lin.dao.RectiCheckDao;
import com.lin.entity.RectiCheckEntity;
import com.lin.util.Pagination;
import com.lin.util.common;
@Service
public class RectiCheckService
{
	@Resource(name="rectiCheckDao")
	private RectiCheckDao rectiCheckDao;

	public RectiCheckService()
	{
	}
	public Integer checkIdExist(RectiCheckEntity entity) {
		return rectiCheckDao.checkIdExist(entity.getJavaid());
	}
	public String insert(RectiCheckEntity entity)
	{
		return rectiCheckDao.insert(entity);
	}

	public String update(RectiCheckEntity entity)
	{
		return rectiCheckDao.update(entity);
	}
	
	public String delete(RectiCheckEntity entity,HttpServletRequest request)
	{
		return rectiCheckDao.delete(entity,request);
	}

	public RectiCheckEntity getById(String rid)
	{
		return rectiCheckDao.getById(rid);
	}

	public List<RectiCheckEntity> getAll()
	{
		return rectiCheckDao.getAll();
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
			,Integer page
			,Integer rows
			)
	{
		return rectiCheckDao.getByUserId(userId
				,role
				,listRectiAc
				,danwei
				,location
				,rectitype
				,rectilevel
				,s_searchForAllFlg
				,checkdate
				,page
				,rows
				); 
	}
	public HttpServletRequest setRequestFromEntity(HttpServletRequest request,RectiCheckEntity entity){
		request.setAttribute("javaid", entity.getJavaid());
		request.setAttribute("ac", entity.getAc());
		request.setAttribute("taskid", entity.getTaskid());
		request.setAttribute("taskname", entity.getTaskname());
		request.setAttribute("processid", entity.getProcessid());
		request.setAttribute("starter", entity.getStarter());
		request.setAttribute("no", entity.getNo());
		request.setAttribute("danwei", entity.getDanwei());
		request.setAttribute("checkdate", entity.getCheckdate());
		request.setAttribute("deadlinedate", entity.getDeadlinedate());
		request.setAttribute("name", entity.getName());
		request.setAttribute("checkname", entity.getCheckname());
		request.setAttribute("content", entity.getContent());
		request.setAttribute("rectitype", entity.getRectitype());
		request.setAttribute("location1", entity.getLocation1());
		request.setAttribute("location2", entity.getLocation2());
		request.setAttribute("location3", entity.getLocation3());

		request.setAttribute("recti1", entity.getRecti1());
		request.setAttribute("recti2", entity.getRecti2());
		request.setAttribute("recti3", entity.getRecti3());
		request.setAttribute("recti4", entity.getRecti4());
		request.setAttribute("nrecti1", entity.getNrecti1());
		request.setAttribute("nrecti2", entity.getNrecti2());
		request.setAttribute("nrecti3", entity.getNrecti3());
		request.setAttribute("nrecti4", entity.getNrecti4());
		request.setAttribute("nrecti5", entity.getNrecti5());
		request.setAttribute("nrecti6", entity.getNrecti6());
		request.setAttribute("nrecti7", entity.getNrecti7());
		request.setAttribute("nrecti8", entity.getNrecti8());
		request.setAttribute("rectisuggest", entity.getRectisuggest());
		request.setAttribute("rectistatus", entity.getRectistatus());
		request.setAttribute("becheckedbuleader", entity.getBecheckedbuleader());
		request.setAttribute("becheckedbuleadername", entity.getBecheckedbuleadername());
		request.setAttribute("recticheckteamleadername", entity.getRecticheckteamleadername());
		request.setAttribute("rectimakesurename", entity.getRectimakesurename());
		request.setAttribute("rectilevel", entity.getRectilevel());
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
		request.setAttribute("cityofficer", entity.getCityofficer());
		request.setAttribute("cityofficername", entity.getCityofficername());
		request.setAttribute("cityofficerokdate", entity.getCityofficerokdate());
		request.setAttribute("cityofficerokmessage", entity.getCityofficerokmessage());
		request.setAttribute("safetymanager", entity.getSafetymanager());
		request.setAttribute("safetymanagername", entity.getSafetymanagername());
		request.setAttribute("safetymanagerokdate", entity.getSafetymanagerokdate());
		request.setAttribute("safetymanagerokmessage", entity.getSafetymanagerokmessage());
		request.setAttribute("townofficer", entity.getTownofficer());
		request.setAttribute("townofficerokdate", entity.getTownofficerokdate());
		request.setAttribute("townofficerokmessage", entity.getTownofficerokmessage());
		request.setAttribute("starterokdate", entity.getStarterokdate());
		request.setAttribute("starterokmessage", entity.getStarterokmessage());
		request.setAttribute("fenguanlingdao", entity.getFenguanlingdao());


		return request;
	}
	public RectiCheckEntity setEntityFromRequest(HttpServletRequest request,RectiCheckEntity entity){
		String javaid = common.toBlank(request.getParameter("javaid"));
		String ac = common.toBlank(request.getParameter("ac"));
		String taskid = common.toBlank(request.getParameter("taskid"));
		String taskname = common.toBlank(request.getParameter("taskname"));
		String processid = common.toBlank(request.getParameter("processid"));
		String starter = common.toBlank(request.getParameter("starter"));
		String no = common.toBlank(request.getParameter("no"));
		String danwei = common.toBlank(request.getParameter("danwei"));
		String checkdate = common.toBlank(request.getParameter("checkdate"));
		String deadlinedate = common.toBlank(request.getParameter("deadlinedate"));
		String name = common.toBlank(request.getParameter("name"));
		String checkname = common.toBlank(request.getParameter("checkname"));
		String content = common.toBlank(request.getParameter("content"));
		String rectitype = common.toBlank(request.getParameter("rectitype"));
		String location1 = common.toBlank(request.getParameter("location1"));
		String location2 = common.toBlank(request.getParameter("location2"));
		String location3 = common.toBlank(request.getParameter("location3"));

		String recti1 = common.toBlank(request.getParameter("recti1"));
		String recti2 = common.toBlank(request.getParameter("recti2"));
		String recti3 = common.toBlank(request.getParameter("recti3"));
		String recti4 = common.toBlank(request.getParameter("recti4"));
		String nrecti1 = common.toBlank(request.getParameter("nrecti1"));
		String nrecti2 = common.toBlank(request.getParameter("nrecti2"));
		String nrecti3 = common.toBlank(request.getParameter("nrecti3"));
		String nrecti4 = common.toBlank(request.getParameter("nrecti4"));
		String nrecti5 = common.toBlank(request.getParameter("nrecti5"));
		String nrecti6 = common.toBlank(request.getParameter("nrecti6"));
		String nrecti7 = common.toBlank(request.getParameter("nrecti7"));
		String nrecti8 = common.toBlank(request.getParameter("nrecti8"));
		String rectisuggest = common.toBlank(request.getParameter("rectisuggest"));
		String rectistatus = common.toBlank(request.getParameter("rectistatus"));
		String becheckedbuleader = common.toBlank(request.getParameter("becheckedbuleader"));
		String becheckedbuleadername = common.toBlank(request.getParameter("becheckedbuleadername"));
		String recticheckteamleadername = common.toBlank(request.getParameter("recticheckteamleadername"));
		String rectimakesurename = common.toBlank(request.getParameter("rectimakesurename"));
		String rectilevel = common.toBlank(request.getParameter("rectilevel"));
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
		String cityofficer = common.toBlank(request.getParameter("cityofficer"));
		String cityofficername = common.toBlank(request.getParameter("cityofficername"));
		String cityofficerokdate = common.toBlank(request.getParameter("cityofficerokdate"));
		String cityofficerokmessage = common.toBlank(request.getParameter("cityofficerokmessage"));
		String safetymanager = common.toBlank(request.getParameter("safetymanager"));
		String safetymanagername = common.toBlank(request.getParameter("safetymanagername"));
		String safetymanagerokdate = common.toBlank(request.getParameter("safetymanagerokdate"));
		String safetymanagerokmessage = common.toBlank(request.getParameter("safetymanagerokmessage"));

		String townofficer = common.toBlank(request.getParameter("townofficer"));
		String townofficerokdate = common.toBlank(request.getParameter("townofficerokdate"));
		String townofficerokmessage = common.toBlank(request.getParameter("townofficerokmessage"));
		String starterokdate = common.toBlank(request.getParameter("starterokdate"));
		String starterokmessage = common.toBlank(request.getParameter("starterokmessage"));
		String fenguanlingdao = common.toBlank(request.getParameter("fenguanlingdao"));

		entity.setJavaid(javaid);
		entity.setAc(ac);
		entity.setTaskid(taskid);
		entity.setTaskname(taskname);
		entity.setProcessid(processid);
		entity.setStarter(starter);
		entity.setNo(no);
		entity.setDanwei(danwei);
		entity.setCheckdate(checkdate);
		entity.setDeadlinedate(deadlinedate);
		entity.setName(name);
		entity.setCheckname(checkname);
		entity.setContent(content);
		entity.setRectitype(rectitype);
		entity.setLocation1(location1);
		entity.setLocation2(location2);
		entity.setLocation3(location3);

		entity.setRecti1(recti1);
		entity.setRecti2(recti2);
		entity.setRecti3(recti3);
		entity.setRecti4(recti4);
		entity.setNrecti1(nrecti1);
		entity.setNrecti2(nrecti2);
		entity.setNrecti3(nrecti3);
		entity.setNrecti4(nrecti4);
		entity.setNrecti5(nrecti5);
		entity.setNrecti6(nrecti6);
		entity.setNrecti7(nrecti7);
		entity.setNrecti8(nrecti8);
		entity.setRectisuggest(rectisuggest);
		entity.setRectistatus(rectistatus);
		entity.setBecheckedbuleader(becheckedbuleader);
		entity.setBecheckedbuleadername(becheckedbuleadername);
		
		entity.setRecticheckteamleadername(recticheckteamleadername);
		entity.setRectimakesurename(rectimakesurename);
		entity.setRectilevel(rectilevel);
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
		entity.setCityofficer(cityofficer);
		entity.setCityofficername(cityofficername);
		entity.setCityofficerokdate(cityofficerokdate);
		entity.setCityofficerokmessage(cityofficerokmessage);
		entity.setSafetymanager(safetymanager);
		entity.setSafetymanagername(safetymanagername);
		entity.setSafetymanagerokdate(safetymanagerokdate);
		entity.setSafetymanagerokmessage(safetymanagerokmessage);

		entity.setTownofficer(townofficer);
		entity.setTownofficerokdate(townofficerokdate);
		entity.setTownofficerokmessage(townofficerokmessage);
		entity.setStarterokdate(starterokdate);
		entity.setStarterokmessage(starterokmessage);
		
		entity.setFenguanlingdao(fenguanlingdao);

		
		return entity;
	}

}
