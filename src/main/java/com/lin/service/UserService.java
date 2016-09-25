
package com.lin.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lin.dao.UserDao;
import com.lin.entity.RectiSearchEntity;
import com.lin.entity.UserEntity;
import com.lin.util.common;
@Service
public class UserService
{
	private static Logger logger = Logger.getLogger(UserService.class);
	@Resource(name="userDao")
	private UserDao userDao;

	public UserService()
	{
	}

	public String insert(UserEntity entity)
	{
		return userDao.insert(entity);
	}

	public String update(UserEntity entity)
	{
		return userDao.update(entity);
	}

	public String delete(UserEntity entity)
	{
		return userDao.delete(entity);
	}

	public UserEntity getById(String rid)
	{
		return userDao.getById(rid);
	}
	public UserEntity getGeofficerById(String rid)
	{
		return userDao.getGeofficerById(rid);
	}
	
	public UserEntity getTownofficerByDanwei(String rid)
	{
		return userDao.getTownofficerByDanwei(rid);
	}
	public UserEntity getGeofficerByDanwei(String rid)
	{

		return userDao.getGeofficerByDanwei(rid);
	}
	
	public UserEntity getSafetymanagerById(String rid)
	{
		return userDao.getSafetymanagerById(rid);
	}
	public UserEntity getSafetymanagerByDanwei(String rid)
	{
		return userDao.getSafetymanagerByDanwei(rid);
	}
	
	public UserEntity getTownofficerById(String rid)
	{
		return userDao.getTownofficerById(rid);
	}
	public UserEntity getCityofficerById(String rid)
	{
		return userDao.getCityofficerById(rid);
	}
	public UserEntity getApprover1ById(String rid)
	{
		return userDao.getApprover1ById(rid);
	}
	public UserEntity getApprover2ById(String rid)
	{
		return userDao.getApprover2ById(rid);
	}
	public Integer checkIdExist(String rid) 
	{
		return userDao.checkIdExist(rid);
	}
	
	public Integer checkPas(String rid,String pas)
	{
		return userDao.checkPas(rid,pas);
	}
	
	public List<UserEntity> getUserListByDanwei(RectiSearchEntity entity)
	{
		return userDao.getUserListByDanwei(entity);
	}
	public List<UserEntity> getUserListByLeader(String user_danwei)
	{
				
		return userDao.getUserListByLeader(user_danwei);
	}
	public List<UserEntity> getUserListByManager(String user_danwei)
	{
				
		return userDao.getUserListByManager(user_danwei);
	}
	public List<UserEntity> getUserListByTownOfficer(String user_danwei)
	{
				
		return userDao.getUserListByTownOfficer(user_danwei);
	}
	public List<UserEntity> getUserListByNGname()
	{
				
		return userDao.getUserListByNGname();
	}
	public List<UserEntity> getLevel2ListByDanweiFullName(String user_danwei)
	{
				
		return userDao.getLevel2ListByDanweiFullName(user_danwei);
	}
	
	public List<UserEntity> getLevel3ListByDanweiFullName(String user_danwei)
	{
				
		return userDao.getLevel3ListByDanweiFullName(user_danwei);
	}
	public HttpServletRequest setRequestFromEntity(HttpServletRequest request,UserEntity entitySaved){
		request.setAttribute("id", entitySaved.getId());
		request.setAttribute("pas", entitySaved.getPas());
		request.setAttribute("name", entitySaved.getName());
		request.setAttribute("position", entitySaved.getPosition());
		request.setAttribute("bumen", entitySaved.getBumen());
		request.setAttribute("positionname", entitySaved.getPositionname());
		request.setAttribute("role", entitySaved.getRole());
		request.setAttribute("ngnameflg", entitySaved.getNgnameflg());
		request.setAttribute("leader", entitySaved.getLeader());
		request.setAttribute("leadername", entitySaved.getLeadername());
		request.setAttribute("manager", entitySaved.getManager());
		request.setAttribute("geofficer", entitySaved.getGeofficer());

		return request;
	}
	public UserEntity setEntityFromRequest(HttpServletRequest request,UserEntity entity){
		String id = common.toBlank(request.getParameter("id"));
		String pas = common.toBlank(request.getParameter("pas"));
		String name = common.toBlank(request.getParameter("name"));
		String position = common.toBlank(request.getParameter("position"));
		String bumen = common.toBlank(request.getParameter("bumen"));
		String positionname = common.toBlank(request.getParameter("positionname"));
		String role = common.toBlank(request.getParameter("role"));
		String ngnameflg = common.toBlank(request.getParameter("ngnameflg"));
		String leader = common.toBlank(request.getParameter("leader"));
		String leadername = common.toBlank(request.getParameter("leadername"));
		String manager = common.toBlank(request.getParameter("manager"));
		String geofficer = common.toBlank(request.getParameter("geofficer"));
		
		entity.setId(id);
		entity.setPas(pas);
		entity.setName(name);
		entity.setPosition(position);
		entity.setBumen(bumen);
		entity.setPositionname(positionname);
		entity.setRole(role);
		entity.setNgnameflg(ngnameflg);
		entity.setLeader(leader);
		entity.setLeadername(leadername);
		entity.setManager(manager);
		entity.setGeofficer(geofficer);

		
		
		return entity;
	}
	
}
