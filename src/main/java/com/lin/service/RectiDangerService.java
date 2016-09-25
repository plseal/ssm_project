

package com.lin.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.lin.dao.RectiDangerDao;
import com.lin.entity.RectiDangerEntity;
import com.lin.util.Pagination;
import com.lin.util.common;
@Service
public class RectiDangerService
{
	@Resource(name="rectiDangerDao")
	private RectiDangerDao rectiDangerDao;

	public RectiDangerService()
	{
	}

	public String update(RectiDangerEntity entity)	{	
		return rectiDangerDao.update(entity);
	}
	public String insert(RectiDangerEntity entity){	
		return rectiDangerDao.insert(entity);
	}
	public Integer checkIdExist(RectiDangerEntity entity) {
		return rectiDangerDao.checkIdExist(entity.getJavaid());
	}
	public String delete(RectiDangerEntity entity,HttpServletRequest request) {
		return rectiDangerDao.delete(entity,request);
		
	}

	public RectiDangerEntity getById(String rid)
	{
		return rectiDangerDao.getById(rid);
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
		return rectiDangerDao.getByUserId(userId
				,role
				,listRectiAc
				,danwei
				,location
				,rectitype
				,rectilevel
				,s_searchForAllFlg
				,checkdate
				, intPage
				, intPageSize
				);
	}
	
	public HttpServletRequest setRequestFromEntity(HttpServletRequest request,RectiDangerEntity entity){
		request.setAttribute("javaid", entity.getJavaid());
		request.setAttribute("ac", entity.getAc());
		request.setAttribute("taskid", entity.getTaskid());
		request.setAttribute("taskname", entity.getTaskname());
		request.setAttribute("processid", entity.getProcessid());
		request.setAttribute("no", entity.getNo());
		request.setAttribute("danwei", entity.getDanwei());
		request.setAttribute("starter", entity.getStarter());
		request.setAttribute("dangertype", entity.getDangertype());
		request.setAttribute("startdate", entity.getStartdate());
		request.setAttribute("enddate", entity.getEnddate());
		request.setAttribute("location", entity.getLocation());
		request.setAttribute("operator", entity.getOperator());
		request.setAttribute("precaution", entity.getPrecaution());
		request.setAttribute("precautionchecker", entity.getPrecautionchecker());
		request.setAttribute("inform", entity.getInform());
		request.setAttribute("informer", entity.getInformer());
		request.setAttribute("leader", entity.getLeader());
		request.setAttribute("leadername", entity.getLeadername());
		request.setAttribute("leaderokdate", entity.getLeaderokdate());
		request.setAttribute("leaderokmessage", entity.getLeaderokmessage());
		request.setAttribute("manager", entity.getManager());
		request.setAttribute("managername", entity.getManagername());
		request.setAttribute("managerokdate", entity.getManagerokdate());
		request.setAttribute("managerokmessage", entity.getManagerokmessage());
		request.setAttribute("townofficer", entity.getTownofficer());
		request.setAttribute("townofficername", entity.getTownofficername());
		request.setAttribute("townofficerokdate", entity.getTownofficerokdate());
		request.setAttribute("townofficerokmessage", entity.getTownofficerokmessage());
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
		request.setAttribute("deleteflg", entity.getDeleteflg());
		request.setAttribute("deletedby", entity.getDeletedby());
		request.setAttribute("starterokdate", entity.getStarterokdate());
		request.setAttribute("starterokmessage", entity.getStarterokmessage());

		request.setAttribute("bumenfuzeren", entity.getBumenfuzeren());
		request.setAttribute("fenguanlingdao", entity.getFenguanlingdao());
		
		return request;
	}
	
	public RectiDangerEntity setEntityFromRequest(HttpServletRequest request,RectiDangerEntity entity){
		String javaid = common.toBlank(request.getParameter("javaid"));
		String ac = common.toBlank(request.getParameter("ac"));
		String taskid = common.toBlank(request.getParameter("taskid"));
		String taskname = common.toBlank(request.getParameter("taskname"));
		String processid = common.toBlank(request.getParameter("processid"));
		String no = common.toBlank(request.getParameter("no"));
		String danwei = common.toBlank(request.getParameter("danwei"));
		String starter = common.toBlank(request.getParameter("starter"));
		String dangertype = common.toBlank(request.getParameter("dangertype"));
		String startdate = common.toBlank(request.getParameter("startdate"));
		String enddate = common.toBlank(request.getParameter("enddate"));
		String location = common.toBlank(request.getParameter("location"));
		String operator = common.toBlank(request.getParameter("operator"));
		String precaution = common.toBlank(request.getParameter("precaution"));
		String precautionchecker = common.toBlank(request.getParameter("precautionchecker"));
		String inform = common.toBlank(request.getParameter("inform"));
		String informer = common.toBlank(request.getParameter("informer"));
		String leader = common.toBlank(request.getParameter("leader"));
		String leadername = common.toBlank(request.getParameter("leadername"));
		String leaderokdate = common.toBlank(request.getParameter("leaderokdate"));
		String leaderokmessage = common.toBlank(request.getParameter("leaderokmessage"));
		String manager = common.toBlank(request.getParameter("manager"));
		String managername = common.toBlank(request.getParameter("managername"));
		String managerokdate = common.toBlank(request.getParameter("managerokdate"));
		String managerokmessage = common.toBlank(request.getParameter("managerokmessage"));
		String townofficer = common.toBlank(request.getParameter("townofficer"));
		String townofficername = common.toBlank(request.getParameter("townofficername"));
		String townofficerokdate = common.toBlank(request.getParameter("townofficerokdate"));
		String townofficerokmessage = common.toBlank(request.getParameter("townofficerokmessage"));
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
		String deleteflg = common.toBlank(request.getParameter("deleteflg"));
		String deletedby = common.toBlank(request.getParameter("deletedby"));
		String starterokdate = common.toBlank(request.getParameter("starterokdate"));
		String starterokmessage = common.toBlank(request.getParameter("starterokmessage"));
		String bumenfuzeren = common.toBlank(request.getParameter("bumenfuzeren"));
		String fenguanlingdao = common.toBlank(request.getParameter("fenguanlingdao"));

		entity.setJavaid(javaid);
		entity.setAc(ac);
		entity.setTaskid(taskid);
		entity.setTaskname(taskname);
		entity.setProcessid(processid);
		entity.setNo(no);
		entity.setDanwei(danwei);
		entity.setStarter(starter);
		entity.setDangertype(dangertype);
		entity.setStartdate(startdate);
		entity.setEnddate(enddate);
		entity.setLocation(location);
		entity.setOperator(operator);
		entity.setPrecaution(precaution);
		entity.setPrecautionchecker(precautionchecker);
		entity.setInform(inform);
		entity.setInformer(informer);
		entity.setLeader(leader);
		entity.setLeadername(leadername);
		entity.setLeaderokdate(leaderokdate);
		entity.setLeaderokmessage(leaderokmessage);
		entity.setManager(manager);
		entity.setManagername(managername);
		entity.setManagerokdate(managerokdate);
		entity.setManagerokmessage(managerokmessage);
		entity.setTownofficer(townofficer);
		entity.setTownofficername(townofficername);
		entity.setTownofficerokdate(townofficerokdate);
		entity.setTownofficerokmessage(townofficerokmessage);
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
		entity.setDeleteflg(deleteflg);
		entity.setDeletedby(deletedby);
		entity.setStarterokdate(starterokdate);
		entity.setStarterokmessage(starterokmessage);

		entity.setBumenfuzeren(bumenfuzeren);
		entity.setFenguanlingdao(fenguanlingdao);
		
		return entity;
	}
	
}
