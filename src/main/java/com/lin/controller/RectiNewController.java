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
import com.lin.entity.RectiNewEntity;
import com.lin.entity.RectiSearchEntity;
import com.lin.entity.UserEntity;
import com.lin.service.DanweiService;
import com.lin.service.LocationService;
import com.lin.service.MyJbpmService;
import com.lin.service.RectiNewService;
import com.lin.service.RectiSearchService;
import com.lin.service.UserService;
import com.lin.util.CustomXWPFDocument;
import com.lin.util.Pagination;
import com.lin.util.WordUtil;
import com.lin.util.common;
@Controller
@RequestMapping("rectiNew")
public class RectiNewController{ 
	private static Logger logger = Logger.getLogger(RectiNewController.class);
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;//-流程管理，部署发布
	
	@Resource(name="taskService")
	private TaskService taskService;//任务管理
	
	@Resource(name="userService")
	private UserService userService;//任务管理
	
	@Resource(name="executionService")
	private ExecutionService executionService;//-流程实例管理
	
	@Resource(name="rectiNewService")
	private RectiNewService rectiNewService;//业务逻辑
	
	@Resource(name="rectiSearchService")
	private RectiSearchService rectiSearchService;//业务逻辑
	
	@Resource(name="danweiService")
	private DanweiService danweiService;//业务逻辑
	
	@Resource(name="locationService")
	private LocationService locationService;//业务逻辑
	
	@Resource(name="myJbpmService")
	private MyJbpmService myJbmpService;//业务逻辑
	
	@Resource(name="historyService")
	private HistoryService historyService;//业务逻辑

	public RectiNewController()
	{
	}
	@RequestMapping("initRectiNew")
	public String initRectiNew(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][initRectiNew][start]");
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String todayyyyMMddHHmmss = df.format(date);
		
		String id = (String)request.getSession().getAttribute("id");
		String name = (String)request.getSession().getAttribute("name");
		
		
		SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
		String no = df2.format(date)+"001";
		
		SimpleDateFormat df3 = new SimpleDateFormat("yyyyMM");
		String ac = df3.format(date);
		
		String javaid = id + todayyyyMMddHHmmss;
		
		request.getSession().setAttribute("javaid", javaid);
		
		String taskname = "开始节点";
		RectiNewEntity entity = new RectiNewEntity();
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
		request.setAttribute("leaderokmessage", "");
		request.setAttribute("leadername", "");
		request.setAttribute("leaderokdate", "");
		request.setAttribute("managerokmessage", "");
		request.setAttribute("managername", "");
		request.setAttribute("managerokdate", "");
		
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//部门负责人list取得
		List<UserEntity>  leaderList =userService.getUserListByLeader(user_danwei);
		request.setAttribute("leaderList",leaderList);
		//分管领导list取得
		List<UserEntity>  managerList =userService.getUserListByManager(user_danwei);
		request.setAttribute("managerList",managerList);
		//单位list取得
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//发起人所在单位取得
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		//检查地点list取得
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		
		request.setAttribute("entity",entity);
		logger.info("["+this.getClass().getName()+"][initRectiNew][taskname]:"+taskname);
		logger.info("["+this.getClass().getName()+"][initRectiNew][goto][rectiNew.jsp]");
		logger.info("["+this.getClass().getName()+"][initRectiNew][end]");
		request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
		return "rectiNew";
	}
	
	@RequestMapping("start_")
	public String start_(HttpServletRequest request){
		
		logger.info("["+this.getClass().getName()+"][start_][start]");
		
		
		RectiNewEntity entity = new RectiNewEntity();
		
		//表单内容设置到entity
		entity = rectiNewService.setEntityFromRequest(request, entity);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("javaid", entity.getJavaid());
		map.put("starter", entity.getStarter());
		
		//发起流程
		ProcessInstance ins = executionService.startProcessInstanceByKey("rectification", map);
		logger.info("["+this.getClass().getName()+"][start_][process start]");
		logger.info("["+this.getClass().getName()+"][start_][process id]:"+ins.getId());
		
		
		//清单的流程信息持久化
		entity.setProcessid(ins.getId());
		entity.setTaskname("部门负责人:"+entity.getBumenfuzeren()+"审批");
		//javaid存在
		if (rectiNewService.checkIdExist(entity)>0) {
			rectiNewService.update(entity);
		//javaid不存在
		} else {
			rectiNewService.insert(entity);
		} 
		
		//完成第一个节点
		Map<String, String> map2 = new HashMap<String, String>();
		
		map2.put("leader", entity.getBumenfuzeren());
		
		String taskId =myJbmpService.getTaskIdByProcessId(ins.getId());
		logger.info("["+this.getClass().getName()+"][start_][task id]:"+taskId);
		taskService.setVariables(taskId, map2);
		
		taskService.completeTask(taskId, "发起申请");
		
		
		request.setAttribute("prepagejsp", "rectiNew.jsp");
		request.setAttribute("listRectiAc", "this_month");
		
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		common.clear_session(request);
		
		logger.info("["+this.getClass().getName()+"][start_][goto][listRectiNew.jsp]");
		logger.info("["+this.getClass().getName()+"][start_][end]");
		
		return "listRectiNew";
	}
	@RequestMapping("save_")
	public String save_(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][save_][start]");
		String pageid    = common.toBlank(request.getParameter("pageid"));
		
		RectiNewEntity entity = new RectiNewEntity();
		
		//表单内容设置到entity
		entity = rectiNewService.setEntityFromRequest(request, entity);
		
		
		//javaid存在
		if (rectiNewService.checkIdExist(entity)>0) {
			rectiNewService.update(entity);
		//javaid不存在
		} else {
			rectiNewService.insert(entity);
		}
		
		//数据库的审批等内容取得
		RectiNewEntity entitySaved = rectiNewService.getById(entity.getJavaid());
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//部门负责人list取得
		List<UserEntity>  leaderList =userService.getUserListByLeader(user_danwei);
		request.setAttribute("leaderList",leaderList);
		//分管领导list取得
		List<UserEntity>  managerList =userService.getUserListByManager(user_danwei);
		request.setAttribute("managerList",managerList);
		//单位list取得
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//发起人所在单位取得
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		//检查地点list取得
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		
		request.setAttribute("entity",entity);
		
		if (pageid.equals("rectiNew.jsp")) {
			request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
			request.setAttribute("messeageForSave", "保存成功！点击按钮查看。");
			
			
			request = rectiNewService.setRequestFromEntity(request, entitySaved);

			logger.info("["+this.getClass().getName()+"][save_][end]---goto[rectiNew.jsp]");
			return "rectiNew";
		} else {
			request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
			request.setAttribute("taskName", "隐患发起人");
			logger.info("["+this.getClass().getName()+"][save_][end]---goto[detail.jsp]");
			return "detail";
		}

	}



	//删除（隐患信息管理页面用）
	@RequestMapping("deleteListRectiNew")
	public String  deleteListRectiNew(String listRectiAc,HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][deleteListRectiNew][start]");
		String javaid   = (String)request.getParameter("javaid");
		String processid   = (String)request.getParameter("processid");
		String taskstatus   = (String)request.getParameter("taskstatus");
		
		
		logger.info("["+this.getClass().getName()+"][deleteListRectiNew][javaid]:"+javaid);
		logger.info("["+this.getClass().getName()+"][deleteListRectiNew][processid]:"+processid);
		logger.info("["+this.getClass().getName()+"][deleteListRectiNew][taskstatus]:"+taskstatus);
		//executionService.deleteProcessInstanceCascade(executionId);
		//taskService.deleteTask(taskId);
		RectiNewEntity entity = rectiNewService.getById(javaid);
		//删除rectiNew的数据
		rectiNewService.delete(entity,request);
		//删除已经发起的流程
		//下列情况不需要删除流程
		//processid为空的
		if ( common.isEmpty(processid)) {
			//不删除
		} else {
			executionService.deleteProcessInstanceCascade(processid);
		}
		
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		request.setAttribute("listRectiAc", listRectiAc);
		logger.info("["+this.getClass().getName()+"][deleteListRectiNew][goto][listRectiNew.jsp]");
		logger.info("["+this.getClass().getName()+"][deleteListRectiNew][end]");
		return "listRectiNew";
	}
	
	@RequestMapping("toListRectiNew")
	public String  toListRectiNew(String listRectiAc,HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][toListRectiNew][start]");
		
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
		
		logger.info("["+this.getClass().getName()+"][toListRectiNew][end]");
		return "listRectiNew";
	}
	
	@RequestMapping("listRectiGet")
	public @ResponseBody Map<String, Object> listRectiGet(HttpServletRequest request
			,String listRectiAc
			,Integer page
			,Integer rows
			){
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
		
		logger.info("["+this.getClass().getName()+"][listRectiGet][intPage]:"+intPage);
		logger.info("["+this.getClass().getName()+"][listRectiGet][intPageSize]:"+intPageSize);
		Pagination  pag = rectiNewService.getByUserId(id
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
		//request.getSession().setAttribute("s_danwei",     "");
		//request.getSession().setAttribute("s_location",   "");
		//request.getSession().setAttribute("s_rectitype",  "");
		//request.getSession().setAttribute("s_rectilevel", "");
		//request.getSession().setAttribute("s_checkdate",  "");
		//request.getSession().setAttribute("s_searchForAllFlg",  "");
		
		
		result.put("total", pag.getTotalRecordCnt()); 
		result.put("rows", pag.getResultList());
		
		logger.info("["+this.getClass().getName()+"][listRectiGet][total]"+pag.getTotalRecordCnt());
		//logger.info("["+this.getClass().getName()+"][listRectiGet][result]"+result.toString());
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
		
		
		RectiNewEntity entity = new RectiNewEntity();
		
		//表单内容设置到entity
		entity = rectiNewService.setEntityFromRequest(request, entity);
		entity.setTaskid(taskid);
		
		
		String id = (String)request.getSession().getAttribute("id");
		String name = (String)request.getSession().getAttribute("name");
		String role = (String)request.getSession().getAttribute("role");
		String leader = (String)request.getSession().getAttribute("leader");
		logger.info("["+this.getClass().getName()+"][complete][id]:"+id);
		logger.info("["+this.getClass().getName()+"][complete][name]:"+name);
		
		logger.info("["+this.getClass().getName()+"][complete][role]:"+role);
		logger.info("["+this.getClass().getName()+"][complete][leader]:"+leader);
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		
		String geofficer = userService.getGeofficerById(entity.getStarter()).getId();
		String townofficer = userService.getTownofficerById(entity.getStarter()).getId();
		String cityofficer = userService.getCityofficerById(entity.getStarter()).getId();
		String starterGeOfficer = userService.getGeofficerById(entity.getStarter()).getId();
		String safetymanager = userService.getSafetymanagerById(entity.getStarter()).getId();
		if (entity.getTaskname().contains("部门负责人") ){
			if ( strTrans.equals("审批") ){
				entity.setLeader(id);
				entity.setLeadername(name);
				entity.setLeaderokdate(df.format(date));
				entity.setTaskname("分管领导:"+entity.getFenguanlingdao()+"审批");
			} else {
				entity.setLeadername("");
				entity.setLeaderokdate("");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("manager", entity.getFenguanlingdao());
			taskService.setVariables(taskid, map);
			
		} else if (entity.getTaskname().contains("分管领导") ){
			if ( strTrans.equals("审批") ){
				entity.setManager(id);
				entity.setManagername(name);
				entity.setManagerokdate(df.format(date));
				entity.setTaskname("办公室:"+geofficer+"审批");
			} else {
				entity.setManager("");
				entity.setManagername("");
				
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("geofficer", geofficer);
			
			taskService.setVariables(taskid, map);
		} else if (entity.getTaskname().contains("办公室") ){
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("townofficer", townofficer);
			map1.put("cityofficer", cityofficer);
			taskService.setVariables(taskid, map1);
			if ( strTrans.equals("审批") ){
				if ("rectiLevelYes".equals(strResult)){
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("rectiLevel", Integer.valueOf(1));

					taskService.setVariables(taskid, map);
					entity.setRectilevel("重大隐患");
					entity.setTaskname("主要负责人:"+townofficer+"审批");
				} else if ("rectiLevelNo".equals(strResult)){
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("rectiLevel", Integer.valueOf(0));
					taskService.setVariables(taskid, map);
					entity.setRectilevel("非重大隐患");
					entity.setTaskname("隐患发起人验收:"+entity.getStarter());
				}
				entity.setGeofficer(id);
				entity.setGeofficername(name);
				entity.setGeofficerokdate(df.format(date));
			} else if (strTrans.equals("再审批")) {
				entity.setGeofficer(id);
				entity.setGeofficername(name);
				entity.setGeofficerokdate(df.format(date));

				entity.setTaskname("安全管理员验收"+safetymanager);
			} else {
				entity.setGeofficer("");
				entity.setGeofficername("");
			}
			
		} else if (entity.getTaskname().contains("主要负责人") ){
			if ( strTrans.equals("审批") ){
				entity.setTownofficer(id);
				entity.setTownofficername(name);
				entity.setTownofficerokdate(df.format(date));
				entity.setTaskname("市局:"+cityofficer+"审批");
			} else {
				entity.setTownofficer("");
				entity.setTownofficername("");
			}
			
		} else if (entity.getTaskname().contains("市局") ) {
			if  (strTrans.equals("审批")) {
				entity.setCityofficer(id);
				entity.setCityofficername(name);
				entity.setCityofficerokdate(df.format(date));
				entity.setTaskname("办公室再次审批:"+starterGeOfficer);
			} else {
				entity.setCityofficer("");
				entity.setCityofficername("");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("geofficer", starterGeOfficer);
			taskService.setVariables(taskid, map);
		} else if (entity.getTaskname().contains("安全管理员验收") ) {
			if  (strTrans.equals("验收")) {
				entity.setSafetymanager(id);
				entity.setSafetymanagername(name);
				entity.setSafetymanagerokdate(df.format(date));
				entity.setProcessid("");
				
				entity.setTaskname("安全管理员验收结束流程。"+safetymanager);
			} else {
				entity.setSafetymanager("");
				entity.setSafetymanagername("");
			}
		} else if (entity.getTaskname().contains("隐患发起人验收") ) {
			if  (strTrans.equals("验收")) {
				entity.setProcessid("");				
				entity.setTaskname("隐患发起人验收结束流程。"+entity.getStarter());
				
				entity.setStarterokdate(df.format(date));
			} else {
				
				
			}
		}
		//javaid存在
		if (rectiNewService.checkIdExist(entity)>0) {
			rectiNewService.update(entity);
		//javaid不存在
		} else {
			rectiNewService.insert(entity);
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
		
		logger.info("["+this.getClass().getName()+"][complete][goto][listRectiNew.jsp]");
		logger.info("["+this.getClass().getName()+"][complete][end]");
		return "listRectiNew";
	}
	@RequestMapping("get_")
	public String get_(String taskid, String taskname,String javaid,String fromPage,String listRectiAc,
			HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][get_][start]");
		logger.info("["+this.getClass().getName()+"][get_][taskid:]"+taskid);
		request.getSession().setAttribute("javaid", javaid);
		if (javaid == null) {
			Task task = taskService.getTask(taskid);	
			javaid = (String)taskService.getVariable(task.getId(), "javaid");
			
		} else{
			
		}
		
		RectiNewEntity entity = rectiNewService.getById(javaid);
		entity.setTaskid(taskid);
		//javaid存在
		if (rectiNewService.checkIdExist(entity)>0) {
			rectiNewService.update(entity);
		//javaid不存在
		} else {
			rectiNewService.insert(entity);
		}
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//部门负责人list取得
		List<UserEntity>  leaderList =userService.getUserListByLeader(user_danwei);
		request.setAttribute("leaderList",leaderList);
		//分管领导list取得
		List<UserEntity>  managerList =userService.getUserListByManager(user_danwei);
		request.setAttribute("managerList",managerList);
		//单位list取得
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//发起人所在单位取得
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		//检查地点list取得
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		
		request.setAttribute("entity", entity);
		
		request = rectiNewService.setRequestFromEntity(request, entity);
		
		request.setAttribute("listRectiAc", listRectiAc);
		logger.info("["+this.getClass().getName()+"][get_][listRectiAc]:"+listRectiAc);
		request.setAttribute("prepagejsp", fromPage);
		logger.info("["+this.getClass().getName()+"][get_][goto][rectiNew.jsp]");
		logger.info("["+this.getClass().getName()+"][get_][end]");
		return "rectiNew";
	}


	
	


	
	@RequestMapping("printword")
	public String printword(HttpServletRequest request) throws IOException{
		logger.info("["+this.getClass().getName()+"][printword][start]");
		
		RectiNewEntity entity = new RectiNewEntity();
		entity = rectiNewService.setEntityFromRequest(request, entity);
		
		logger.info("["+this.getClass().getName()+"][printword][no:]"+entity.getNo());
		logger.info("["+this.getClass().getName()+"][printword][name:]"+entity.getName());
		logger.info("["+this.getClass().getName()+"][printword][location:]"+entity.getLocation());
		
	    Map<String, Object> param = new HashMap<String, Object>();
	    param.put("ぁ", entity.getNo());    
	    param.put("ぃ", entity.getName()); 
	    param.put("ぅ", entity.getLocation());
	    param.put("ぇ", entity.getCheckdate()); 
	    param.put("ぉ", entity.getContent());
	    //param.put("か", entity.getOkstandard());
	    param.put("き", entity.getOkmeasure());
	    param.put("け", entity.getLeadername());
	    param.put("こ", entity.getLeaderokdate()); 
	    param.put("く", entity.getLeaderokmessage()); 
	    param.put("し", entity.getManagername()); 
	    param.put("す", entity.getManagerokdate());
	    param.put("さ", entity.getManagerokmessage()); 
	    param.put("そ", entity.getGeofficername());
	    param.put("た", entity.getGeofficerokdate());
	    param.put("せ", entity.getGeofficerokmessage());
	    param.put("つ", entity.getTownofficername());
	    param.put("て", entity.getTownofficerokdate());
	    param.put("ち", entity.getTownofficerokmessage());
	    param.put("な", entity.getCityofficername());
	    param.put("に", entity.getCityofficerokdate());
	    param.put("と", entity.getCityofficerokmessage()); 
	    //安全管理员验收信息
	    if (!"".equals(entity.getSafetymanagerokdate())) {
		    param.put("ね", entity.getSafetymanagername());
		    param.put("の", entity.getSafetymanagerokdate());  
		    param.put("ぬ", entity.getSafetymanagerokmessage());
		//隐患发起人验收信息
	    } else {
		    param.put("ね", entity.getStarter());
		    param.put("の", entity.getStarterokdate());  
		    param.put("ぬ", entity.getStarterokmessage());
	    	
	    }
	    param.put("は", entity.getDanwei());


	    	    
	    String strModelFileOfRealServerPath = 
	    		request.getRealPath("/") + "modelfile\\rectinew3.docx";
	    String strOutPutRealServerPath = 
	    		request.getRealPath("/") + "modelfileoutput\\"+entity.getJavaid()+".docx";
	
	    
	    CustomXWPFDocument doc = WordUtil.generateWord(param, strModelFileOfRealServerPath);
	    FileOutputStream fopts = new FileOutputStream(strOutPutRealServerPath);
	    doc.write(fopts);
	    fopts.close();
	    
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		
		String strRealHTTPPath = basePath+"modelfileoutput\\"+entity.getJavaid()+".docx";
		
		
	    request.setAttribute("strRealHTTPPath", strRealHTTPPath);
	    request.setAttribute("strFileNameForView", "安全隐患整改落实清单");
	    
	    logger.info("["+this.getClass().getName()+"][printword][end]");
	    logger.info("["+this.getClass().getName()+"][printword][goto][printword.jsp]");
	    
	    
	    return "printword";
	}
}
