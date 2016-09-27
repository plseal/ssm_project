package com.lin.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.entity.DanweiEntity;
import com.lin.entity.LocationEntity;
import com.lin.entity.RectiCheckEntity;
import com.lin.entity.RectiSearchEntity;
import com.lin.entity.UserEntity;
import com.lin.service.DanweiService;
import com.lin.service.LocationService;
import com.lin.service.MyJbpmService;
import com.lin.service.RectiCheckService;
import com.lin.service.RectiSearchService;
import com.lin.service.UserService;
import com.lin.util.CustomXWPFDocument;
import com.lin.util.Pagination;
import com.lin.util.WordUtil;
import com.lin.util.common;
@Controller
@RequestMapping("rectiCheck")
public class RectiCheckController
{
	private static Logger logger = Logger.getLogger(RectiCheckController.class);
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;//-流程管理，部署发布
	
	@Resource(name="taskService")
	private TaskService taskService;//任务管理
	
	@Resource(name="userService")
	private UserService userService;//任务管理
	
	@Resource(name="executionService")
	private ExecutionService executionService;//-流程实例管理
	
	@Resource(name="rectiCheckService")
	private RectiCheckService rectiCheckService;//业务逻辑
	
	@Resource(name="danweiService")
	private DanweiService danweiService;//业务逻辑
	
	@Resource(name="locationService")
	private LocationService locationService;//业务逻辑
	
	@Resource(name="rectiSearchService")
	private RectiSearchService rectiSearchService;//业务逻辑
	
	@Resource(name="myJbpmService")
	private MyJbpmService myJbmpService;//业务逻辑
	@Resource(name="historyService")
	private HistoryService historyService;//业务逻辑

	public RectiCheckController()
	{
	}
	@RequestMapping("initRectiCheck")
	public String initRectiCheck(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][initRectiCheck][start]");
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String todayyyyMMddHHmmss = df.format(date);
		
		String id = (String)request.getSession().getAttribute("id");
		String name = (String)request.getSession().getAttribute("name");
		
		
		SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHH");
		String no = df2.format(date);
		
		SimpleDateFormat df3 = new SimpleDateFormat("yyyyMM");
		String ac = df3.format(date);
		
		String javaid = id + todayyyyMMddHHmmss;
		
		request.getSession().setAttribute("javaid", javaid);
		
		String taskname = "开始节点";
		RectiCheckEntity entity = new RectiCheckEntity();
		entity.setStarter(id);
		entity.setJavaid(javaid);
		
		entity.setTaskname(taskname);
		
		request.setAttribute("javaid", javaid);
		request.setAttribute("ac", ac);
		request.setAttribute("no", no);
		request.setAttribute("taskid", "");
		request.setAttribute("taskname", taskname);
		request.setAttribute("starter", id);
		request.setAttribute("name", name);
		request.setAttribute("location", "");
		request.setAttribute("checkdate", "");
		request.setAttribute("content", "");
		request.setAttribute("okstandard", "");
		request.setAttribute("okmeasure", "");
		request.setAttribute("leader", "");
		request.setAttribute("leaderokmessage", "");
		request.setAttribute("leadername", "");
		request.setAttribute("leaderokdate", "");
		request.setAttribute("managerokmessage", "");
		request.setAttribute("managername", "");
		request.setAttribute("managerokdate", "");
		
		
		request.setAttribute("entity",entity);
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//局领导list取得
		List<UserEntity>  townofficerList =userService.getUserListByTownOfficer(user_danwei);
		request.setAttribute("townofficerList",townofficerList);
		//单位list取得
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//发起人所在单位取得
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//检查地点list取得
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
		logger.info("["+this.getClass().getName()+"][initRectiCheck][taskname]:"+taskname);
		logger.info("["+this.getClass().getName()+"][initRectiCheck][goto][rectiCheck.jsp]");
		logger.info("["+this.getClass().getName()+"][initRectiCheck][end]");
		return "rectiCheck";
	}
	
	
	@RequestMapping("toRectiCheck")
	public String toRectiCheck(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][toRectiCheck][start]");

		RectiCheckEntity entity = new RectiCheckEntity();
		//表单内容设置到entity
		entity = rectiCheckService.setEntityFromRequest(request, entity);
		
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//局领导list取得
		List<UserEntity>  townofficerList =userService.getUserListByTownOfficer(user_danwei);
		request.setAttribute("townofficerList",townofficerList);
		//单位list取得
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//发起人所在单位取得
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//检查地点list取得
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		logger.info("["+this.getClass().getName()+"][toRectiCheck][单位简称]"+entity.getDanwei());
		
		
		
		String danweilongname = "市局公司";
		String danweishortname ="市局";
		
		for (int i=0;i<danweiList.size();i++) {
			danweishortname = danweiList.get(i).getId();
			if (danweishortname.equals(entity.getDanwei())) {
				danweilongname =danweiList.get(i).getName(); 
			}
		}
		logger.info("["+this.getClass().getName()+"][toRectiCheck][单位全称]"+danweilongname);
		
		UserEntity entity_danwei1bashou = userService.getTownofficerByDanwei(danweilongname);
		logger.info("["+this.getClass().getName()+"][toRectiCheck][单位1把手]"+entity_danwei1bashou.getId());
		entity.setBecheckedbuleader(entity_danwei1bashou.getId());
		
		request = rectiCheckService.setRequestFromEntity(request, entity);
		
		request.setAttribute("entity",entity);
		
		logger.info("["+this.getClass().getName()+"][toRectiCheck][goto][rectiCheck.jsp]");
		logger.info("["+this.getClass().getName()+"][toRectiCheck][end]");
		return "rectiCheck";
	}
	
	

	//发起申请
	@RequestMapping("start_")
	public String start_(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][start_][start]");
		RectiCheckEntity entity = new RectiCheckEntity();
		//表单内容设置到entity
		entity = rectiCheckService.setEntityFromRequest(request, entity);		
		logger.info("["+this.getClass().getName()+"][start_][becheckedbuleader]:"+entity.getBecheckedbuleader());				
		Map<String, String> map = new HashMap<String, String>();
		map.put("javaid", entity.getJavaid());
		map.put("starter", entity.getStarter());
		
		//发起流程
		ProcessInstance ins = executionService.startProcessInstanceByKey("rectification6", map);
		logger.info("["+this.getClass().getName()+"][start_][process start]");
		logger.info("["+this.getClass().getName()+"][start_][process id]:"+ins.getId());
		

		//清单的流程信息持久化
		entity.setProcessid(ins.getId());
		entity.setTaskname("主要负责人:"+entity.getBecheckedbuleader()+"审批");
		//javaid存在
		if (rectiCheckService.checkIdExist(entity)>0) {
			rectiCheckService.update(entity);
		//javaid不存在
		} else {
			rectiCheckService.insert(entity);
		}
		
		//完成第一个节点
		Map<String, String> map2 = new HashMap<String, String>();
		String becheckedbuleader = entity.getBecheckedbuleader();
		logger.info("["+this.getClass().getName()+"][start_][becheckedbuleader]:"+becheckedbuleader);
		map2.put("leader", becheckedbuleader);
		
		String taskId =myJbmpService.getTaskIdByProcessId(ins.getId());
		logger.info("["+this.getClass().getName()+"][start_][task id]:"+taskId);
		taskService.setVariables(taskId, map2);
		taskService.completeTask(taskId, "发起流程");
		
		request.setAttribute("prepagejsp", "rectiCheck.jsp");
		request.setAttribute("listRectiAc", "this_month");
		
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		common.clear_session(request);
		
		logger.info("["+this.getClass().getName()+"][start_][goto][listRectiCheck.jsp]");
		logger.info("["+this.getClass().getName()+"][start_][end]");
		
		return "listRectiCheck";
				
	}
	@RequestMapping("save_")
	public String save_(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][save_][start]");
		String pageid    = common.toBlank(request.getParameter("pageid"));
		
		RectiCheckEntity entity = new RectiCheckEntity();
		
		//表单内容设置到entity
		entity = rectiCheckService.setEntityFromRequest(request, entity);
		
		
		//javaid存在
		if (rectiCheckService.checkIdExist(entity)>0) {
			rectiCheckService.update(entity);
		//javaid不存在
		} else {
			rectiCheckService.insert(entity);
		}
		
		//数据库的审批等内容取得
		RectiCheckEntity entitySaved = rectiCheckService.getById(entity.getJavaid());
		request.setAttribute("entity",entity);
		
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//局领导list取得
		List<UserEntity>  townofficerList =userService.getUserListByTownOfficer(user_danwei);
		request.setAttribute("townofficerList",townofficerList);
		//单位list取得
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//发起人所在单位取得
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//检查地点list取得
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		if (pageid.equals("rectiCheck.jsp")) {
			request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
			request.setAttribute("messeageForSave", "保存成功！点击按钮查看。");
			
			
			request = rectiCheckService.setRequestFromEntity(request, entitySaved);

			logger.info("["+this.getClass().getName()+"][save_][end]---goto[rectiCheck.jsp]");
			return "rectiCheck";
		} else {
			request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
			request.setAttribute("taskName", "隐患发起人");
			logger.info("["+this.getClass().getName()+"][save_][end]---goto[detail.jsp]");
			return "detail";
		}

	}


	//删除（隐患信息管理页面用）
	@RequestMapping("deleteListRectiCheck")
	public String  deleteListRectiCheck(String listRectiAc, HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][deleteListRectiCheck][start]");
		String javaid   = (String)request.getParameter("javaid");
		String processid   = (String)request.getParameter("processid");
		String taskstatus   = (String)request.getParameter("taskstatus");	
		
		logger.info("["+this.getClass().getName()+"][deleteListRectiCheck][javaid]:"+javaid);
		logger.info("["+this.getClass().getName()+"][deleteListRectiCheck][processid]:"+processid);
		logger.info("["+this.getClass().getName()+"][deleteListRectiCheck][taskstatus]:"+taskstatus);
		//executionService.deleteProcessInstanceCascade(executionId);
		//taskService.deleteTask(taskId);
		RectiCheckEntity entity = rectiCheckService.getById(javaid);
		//删除rectiCheck的数据
		rectiCheckService.delete(entity,request);
		
		//删除已经发起的流程
		//下列情况不需要删除流程
		//processid为空的
		if ( common.isEmpty(processid)) {
			//不删除
		} else {
			executionService.deleteProcessInstanceCascade(processid);
		}
		request.setAttribute("listRectiAc", listRectiAc);
		
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		logger.info("["+this.getClass().getName()+"][deleteListRectiCheck][goto][listRectiCheck.jsp]");
		logger.info("["+this.getClass().getName()+"][deleteListRectiCheck][end]");
		return "listRectiCheck";
	}

			
	@RequestMapping("toListRectiCheck")
	public String  toListRectiCheck(String listRectiAc,HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][toListRectiCheck][start]");
		RectiSearchEntity entity = new RectiSearchEntity();
		entity = rectiSearchService.setEntityFromRequest(request, entity);
		if (common.isEmpty(listRectiAc)) {
			listRectiAc = "this_month";
		}
		request.setAttribute("listRectiAc", listRectiAc);
		request.getSession().setAttribute("s_danwei",     entity.getS_danwei());
		request.getSession().setAttribute("s_location",   entity.getS_location());
		request.getSession().setAttribute("s_rectitype",  entity.getS_rectitype());
		request.getSession().setAttribute("s_rectilevel", entity.getS_rectilevel());
		request.getSession().setAttribute("s_checkdate",  entity.getS_checkdate());
		request.getSession().setAttribute("s_searchForAllFlg",  entity.getS_searchForAllFlg());
		request = rectiSearchService.setRequestFromEntity(request, entity);
		
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		logger.info("["+this.getClass().getName()+"][toListRectiCheck][listRectiAc]:"+listRectiAc);
		
		logger.info("["+this.getClass().getName()+"][toListRectiCheck][end]");
		return "listRectiCheck";
	}
	
	@RequestMapping("listRectiGet")
	public @ResponseBody Map<String, Object> listRectiGet(String listRectiAc
			,HttpServletRequest request
			,Integer page
			,Integer rows
			) throws UnsupportedEncodingException{
		logger.info("["+this.getClass().getName()+"][listRectiGet][start]");
		Map<String, Object> result = new HashMap<String, Object>(); 
		String id   = (String)request.getSession().getAttribute("id");
		String role   = (String)request.getSession().getAttribute("role");
		
		String danwei     = (String)request.getSession().getAttribute("s_danwei");
		String location   = (String)request.getSession().getAttribute("s_location");
		String rectitype  = (String)request.getSession().getAttribute("s_rectitype");
		String rectilevel = (String)request.getSession().getAttribute("s_rectilevel");
		String checkdate  = (String)request.getSession().getAttribute("s_checkdate");
		String s_searchForAllFlg  = (String)request.getSession().getAttribute("s_searchForAllFlg");
		
		
		//设置当前页
        int intPage = (page==null||page<=0)?1:page;
        //设置每页显示的数量
        int intPageSize = rows==null||rows<=0?10:rows;;

		logger.info("["+this.getClass().getName()+"][listRectiGet][listRectiAc]:"+listRectiAc);
				
		Pagination  pag = rectiCheckService.getByUserId(id
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
		//request.getSession().setAttribute("s_danwei",     "");
		//request.getSession().setAttribute("s_location",   "");
		//request.getSession().setAttribute("s_rectitype",  "");
		//request.getSession().setAttribute("s_rectilevel", "");
		//request.getSession().setAttribute("s_checkdate",  "");
		//request.getSession().setAttribute("s_searchForAllFlg",  "");
		

		result.put("total", pag.getTotalRecordCnt()); 
		result.put("rows", pag.getResultList());
		
		logger.info("["+this.getClass().getName()+"][listRectiGet][end]");
		
		return result;
	}
	//完成任务
	@RequestMapping("complete")
	public String complete(String taskid, HttpServletRequest request) throws UnsupportedEncodingException {
		logger.info("["+this.getClass().getName()+"][complete][start]");
		String strResult = request.getParameter("strResult");
		String strTrans = request.getParameter("strTrans");
		
		logger.info("["+this.getClass().getName()+"][complete]["+strResult+"]["+strTrans+"]");
		logger.info("["+this.getClass().getName()+"][complete][taskid]:"+taskid);
		
		RectiCheckEntity entity = new RectiCheckEntity();
		
		//表单内容设置到entity
		entity = rectiCheckService.setEntityFromRequest(request, entity);
		entity.setTaskid(taskid);
		
		String id = (String)request.getSession().getAttribute("id");
		String name = (String)request.getSession().getAttribute("name");
		String role = (String)request.getSession().getAttribute("role");
		String leader = (String)request.getSession().getAttribute("leader");
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		logger.info("["+this.getClass().getName()+"][complete][id]:"+id);
		logger.info("["+this.getClass().getName()+"][complete][name]:"+name);
		logger.info("["+this.getClass().getName()+"][complete][role]:"+role);
		logger.info("["+this.getClass().getName()+"][complete][leader]:"+leader);
		logger.info("["+this.getClass().getName()+"][complete][user_danwei]:"+user_danwei);
		
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		
		//String geofficer = userService.getGeofficerById(entity.getStarter()).getId();
		//String townofficer = userService.getTownofficerById(entity.getStarter()).getId();
		//市局综合办公室
		String cityofficer = userService.getGeofficerByDanwei("市局公司").getId();
		logger.info("["+this.getClass().getName()+"][complete][cityofficer]:"+cityofficer);
		//受检单位的综合办公室
		DanweiEntity  entity_danwei2 =danweiService.getFullName(entity.getDanwei());
		String danwei_fullname2 = entity_danwei2.getName();
		logger.info("["+this.getClass().getName()+"][complete][danwei_fullname]:"+danwei_fullname2);
		String becheckedDanweiGeOfficer = userService.getGeofficerByDanwei(danwei_fullname2).getId();
		logger.info("["+this.getClass().getName()+"][complete][becheckedDanweiGeOfficer]:"+becheckedDanweiGeOfficer);
		//市局安全管理员
		String safetymanager = userService.getSafetymanagerByDanwei("市局公司").getId();
		
		if (entity.getTaskname().contains("主要负责人") ){
			if ( strTrans.equals("提交") ){
				entity.setTownofficer(id);
				entity.setTownofficerokdate(df.format(date));
				entity.setTaskname("综合办公室："+becheckedDanweiGeOfficer+"审批");
			} else {
				entity.setTownofficer("");
				entity.setTownofficerokdate("");
			}
			
			Map<String, String> map = new HashMap<String, String>();

			map.put("geofficer", becheckedDanweiGeOfficer);
			
			taskService.setVariables(taskid, map);
		//先进行判断	
		} else if (entity.getTaskname().contains("综合办公室再次审批") ) {
			
			if  (strTrans.equals("审批")) {
				entity.setGeofficer(id);
				entity.setGeofficername(name);
				entity.setGeofficerokdate(df.format(date));
				entity.setTaskname("市局安全管理员确认验收:"+safetymanager);
			} else {
				entity.setGeofficer("");
				entity.setGeofficername("");
			}
	
		} else if (entity.getTaskname().contains("综合办公室") ){
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("cityofficer", cityofficer);
			taskService.setVariables(taskid, map1);
			if ( strTrans.equals("审批") ){
				if ("rectiLevelYes".equals(strResult)){
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("rectiLevel", Integer.valueOf(1));
					taskService.setVariables(taskid, map);
					entity.setRectilevel("重大隐患");
					entity.setTaskname("市局办公室："+cityofficer+"审批");
				} else if ("rectiLevelNo".equals(strResult)){
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("rectiLevel", Integer.valueOf(0));
					taskService.setVariables(taskid, map);
					entity.setRectilevel("非重大隐患");
					entity.setTaskname("市局安全管理员确认验收。"+safetymanager);
				}
				entity.setGeofficer(id);
				entity.setGeofficername(name);
				entity.setGeofficerokdate(df.format(date));
			} else {
				entity.setGeofficer("");
				entity.setGeofficername("");
			}
			
		} else if (entity.getTaskname().contains("市局办公室") ) {
			
			if  (strTrans.equals("审批")) {
				entity.setCityofficer(id);
				entity.setCityofficername(name);
				entity.setCityofficerokdate(df.format(date));
				entity.setTaskname("综合办公室再次审批:"+becheckedDanweiGeOfficer);
			} else {
				entity.setCityofficer("");
				entity.setCityofficername("");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("geofficer", becheckedDanweiGeOfficer);
			taskService.setVariables(taskid, map);
			
		} else if (entity.getTaskname().contains("市局安全管理员确认验收") ) {
			if  (strTrans.equals("验收")) {
				entity.setSafetymanager(id);
				entity.setSafetymanagername(name);
				entity.setSafetymanagerokdate(df.format(date));
				entity.setTaskname("市局安全管理员验收结束流程。"+safetymanager);
			} else {
				entity.setSafetymanager("");
				entity.setSafetymanagername("");
			}
			
		}
		//javaid存在
		if (rectiCheckService.checkIdExist(entity)>0) {
			rectiCheckService.update(entity);
		//javaid不存在
		} else {
			rectiCheckService.insert(entity);
		}
		
		Map<String, String> map2 = new HashMap<String, String>();
		
		map2.put("safetymanager", safetymanager);
		taskService.setVariables(taskid, map2);
		
		
		taskService.completeTask(taskid, strTrans);
		request.setAttribute("strTrans", strTrans);
		request.setAttribute("entity", entity);
		request.setAttribute("listRectiAc", "this_month");
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		common.clear_session(request);
		
		logger.info("["+this.getClass().getName()+"][complete][goto][listRectiCheck.jsp]");
		logger.info("["+this.getClass().getName()+"][complete][end]");
		return "listRectiCheck";
	}
	
	@RequestMapping("get_")
	public String get_(String taskid, String taskname,String javaid, String fromPage,String listRectiAc
			,HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][get_][start]");
		logger.info("["+this.getClass().getName()+"][get_][taskid:]"+taskid);
		if (javaid == null) {
			Task task = taskService.getTask(taskid);	
			javaid = (String)taskService.getVariable(task.getId(), "javaid");
			
		} else{
			
		}

		RectiCheckEntity entity = rectiCheckService.getById(javaid);
		
		entity.setTaskid(taskid);
		//javaid存在
		if (rectiCheckService.checkIdExist(entity)>0) {
			rectiCheckService.update(entity);
		//javaid不存在
		} else {
			rectiCheckService.insert(entity);
		}
		
				
		request.setAttribute("entity", entity);

		request = rectiCheckService.setRequestFromEntity(request, entity);
		
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//局领导list取得
		List<UserEntity>  townofficerList =userService.getUserListByTownOfficer(user_danwei);
		request.setAttribute("townofficerList",townofficerList);
		//单位list取得
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//发起人所在单位取得
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//检查地点list取得
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		
		request.setAttribute("listRectiAc", listRectiAc);
		logger.info("["+this.getClass().getName()+"][get_][listRectiAc]:"+listRectiAc);
		request.setAttribute("prepagejsp", fromPage);
		logger.info("["+this.getClass().getName()+"][get_][goto][rectiCheck.jsp]");
		logger.info("["+this.getClass().getName()+"][get_][end]");
		return "rectiCheck";
	}

	

	
	
	@RequestMapping("printword")
	public String printword(HttpServletRequest request) throws IOException{
		logger.info("["+this.getClass().getName()+"][printword][start]");
		
		RectiCheckEntity entity = new RectiCheckEntity();
		entity = rectiCheckService.setEntityFromRequest(request, entity);
		
		
	    Map<String, Object> param = new HashMap<String, Object>();
	    param.put("ぁ", entity.getNo());
	    param.put("ぃ", entity.getDanwei());
	    param.put("ぅ", entity.getCheckdate());
	    param.put("ぇ", entity.getDeadlinedate());
	    param.put("ぉ", entity.getName());
	    param.put("か", entity.getCheckname());
	    param.put("き", entity.getContent());
	    param.put("く", entity.getRecti1());
	    param.put("け", entity.getRecti2());
	    param.put("こ", entity.getRecti3());
	    param.put("さ", entity.getRecti4());
	    param.put("し", entity.getNrecti1());
	    param.put("す", entity.getNrecti2());
	    param.put("せ", entity.getNrecti3());
	    param.put("そ", entity.getNrecti4());
	    param.put("た", entity.getNrecti5());
	    param.put("ち", entity.getNrecti6());
	    param.put("つ", entity.getNrecti7());
	    param.put("て", entity.getNrecti8());
	    param.put("と", entity.getRectisuggest());
	    param.put("な", entity.getRectistatus());
	    param.put("に", entity.getBecheckedbuleadername()); 
	    param.put("ぬ", entity.getRecticheckteamleadername());
	    param.put("ね", entity.getRectimakesurename());
	    param.put("の", entity.getTownofficerokmessage());
	    param.put("は", entity.getTownofficer());
	    param.put("ひ", entity.getTownofficerokdate());
	    param.put("ふ", entity.getGeofficerokmessage());
	    param.put("へ", entity.getGeofficername());
	    param.put("ほ", entity.getGeofficerokdate());
	    param.put("ま", entity.getCityofficerokmessage());
	    param.put("み", entity.getCityofficername());
	    param.put("む", entity.getCityofficerokdate());
	    param.put("め", entity.getSafetymanagerokmessage());
	    param.put("も", entity.getSafetymanagername());
	    param.put("ゃ", entity.getSafetymanagerokdate());
	    param.put("よ", entity.getLocation1() + " " + entity.getLocation2() + " "+ entity.getLocation3());
	
	    String strModelFileOfRealServerPath = 
	    		request.getRealPath("/") + "modelfile\\recticheck3.docx";
	    String strOutPutRealServerPath = 
	    		request.getRealPath("/") + "modelfileoutput\\"+entity.getJavaid()+"check.docx";
	
	    
	    CustomXWPFDocument doc = WordUtil.generateWord(param, strModelFileOfRealServerPath);
	    FileOutputStream fopts = new FileOutputStream(strOutPutRealServerPath);
	    doc.write(fopts);
	    fopts.close();
	    String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		
		String strRealHTTPPath = basePath+"modelfileoutput\\"+entity.getJavaid()+"check.docx";
		
		logger.info("["+this.getClass().getName()+"][printword][strOutPutRealServerPath]:"+strOutPutRealServerPath);
		logger.info("["+this.getClass().getName()+"][printword][strRealHTTPPath]:"+strRealHTTPPath);
	    request.setAttribute("strRealHTTPPath", strRealHTTPPath);
	    request.setAttribute("strFileNameForView", "安全检查记录表 ");
	    
	    
	    logger.info("["+this.getClass().getName()+"][printword][end]");
	    logger.info("["+this.getClass().getName()+"][printword][goto][printword.jsp]");
	    return "printword";   
	}
}
