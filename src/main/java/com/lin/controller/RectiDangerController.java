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
import com.lin.entity.RectiDangerEntity;
import com.lin.entity.RectiSearchEntity;
import com.lin.entity.UserEntity;
import com.lin.service.DanweiService;
import com.lin.service.LocationService;
import com.lin.service.MyJbpmService;
import com.lin.service.RectiDangerService;
import com.lin.service.RectiSearchService;
import com.lin.service.UserService;
import com.lin.util.CustomXWPFDocument;
import com.lin.util.Pagination;
import com.lin.util.WordUtil;
import com.lin.util.common;
@Controller
@RequestMapping("rectiDanger")
public class RectiDangerController{ 
	private static Logger logger = Logger.getLogger(RectiDangerController.class);
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;//-流程管理，部署发布
	
	@Resource(name="taskService")
	private TaskService taskService;//任务管理
	
	@Resource(name="userService")
	private UserService userService;//任务管理
	
	@Resource(name="executionService")
	private ExecutionService executionService;//-流程实例管理
	
	@Resource(name="rectiDangerService")
	private RectiDangerService rectiDangerService;//业务逻辑
	
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

	public RectiDangerController()
	{
	}
	@RequestMapping("initRectiDanger")
	public String initRectiDanger(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][initRectiDanger][start]");
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
		RectiDangerEntity entity = new RectiDangerEntity();
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
		//List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		//request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		//检查地点list取得
		//List<LocationEntity>  locationList =locationService.getAll();
		//request.setAttribute("locationList",locationList);

		
		request.setAttribute("entity",entity);
		logger.info("["+this.getClass().getName()+"][initRectiDanger][taskname]:"+taskname);
		logger.info("["+this.getClass().getName()+"][initRectiDanger][goto][rectiDanger.jsp]");
		logger.info("["+this.getClass().getName()+"][initRectiDanger][end]");
		return "rectiDanger";
	}
	
	@RequestMapping("start_")
	public String start_(HttpServletRequest request){
		
		logger.info("["+this.getClass().getName()+"][start_][start]");
		
		
		RectiDangerEntity entity = new RectiDangerEntity();
		
		//表单内容设置到entity
		entity = rectiDangerService.setEntityFromRequest(request, entity);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("javaid", entity.getJavaid());
		map.put("starter", entity.getStarter());
		
		//发起流程
		ProcessInstance ins = executionService.startProcessInstanceByKey("rectidanger3", map);
		logger.info("["+this.getClass().getName()+"][start_][process start]");
		logger.info("["+this.getClass().getName()+"][start_][process id]:"+ins.getId());
		
		//取得发起人所在单位的[安全管理部门负责人]
		String approver1 = userService.getApprover1ById(entity.getStarter()).getId();
		//清单的流程信息持久化
		entity.setProcessid(ins.getId());
		entity.setTaskname("安全管理部门负责人:"+approver1+"审批");
		//javaid存在
		if (rectiDangerService.checkIdExist(entity)>0) {
			rectiDangerService.update(entity);
		//javaid不存在
		} else {
			rectiDangerService.insert(entity);
		} 
		
		//完成第一个节点
		Map<String, String> map2 = new HashMap<String, String>();
		
		map2.put("leader", approver1);
		
		String taskId =myJbmpService.getTaskIdByProcessId(ins.getId());
		logger.info("["+this.getClass().getName()+"][start_][task id]:"+taskId);
		taskService.setVariables(taskId, map2);
		
		taskService.completeTask(taskId, "发起申请");
		
		
		request.setAttribute("listRectiAc", "this_month");
		
		//隐患类型list取得
		//List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		//request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		common.clear_session(request);
		
		logger.info("["+this.getClass().getName()+"][start_][goto][listRectiDanger.jsp]");
		logger.info("["+this.getClass().getName()+"][start_][end]");
		
		return "listRectiDanger";
	}
	@RequestMapping("save_")
	public String save_(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][save_][start]");
		String pageid    = common.toBlank(request.getParameter("pageid"));
		
		RectiDangerEntity entity = new RectiDangerEntity();
		
		//表单内容设置到entity
		entity = rectiDangerService.setEntityFromRequest(request, entity);
		
		
		//javaid存在
		if (rectiDangerService.checkIdExist(entity)>0) {
			rectiDangerService.update(entity);
		//javaid不存在
		} else {
			rectiDangerService.insert(entity);
		}
		
		//数据库的审批等内容取得
		RectiDangerEntity entitySaved = rectiDangerService.getById(entity.getJavaid());
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
		//List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		//request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		//检查地点list取得
		//List<LocationEntity>  locationList =locationService.getAll();
		//request.setAttribute("locationList",locationList);
		
		request.setAttribute("entity",entity);
		
		request.setAttribute("messeageForSave", "保存成功！点击按钮查看。");
			
		request = rectiDangerService.setRequestFromEntity(request, entitySaved);

		logger.info("["+this.getClass().getName()+"][save_][end]---goto[rectiDanger.jsp]");
		return "rectiDanger";
		
	}



	//删除（隐患信息管理页面用）
	@RequestMapping("deleteListRectiDanger")
	public String  deleteListRectiDanger(String listRectiAc,HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][deleteListRectiDanger][start]");
		String javaid   = (String)request.getParameter("javaid");
		String processid   = (String)request.getParameter("processid");
		String taskstatus   = (String)request.getParameter("taskstatus");
		
		
		logger.info("["+this.getClass().getName()+"][deleteListRectiDanger][javaid]:"+javaid);
		logger.info("["+this.getClass().getName()+"][deleteListRectiDanger][processid]:"+processid);
		logger.info("["+this.getClass().getName()+"][deleteListRectiDanger][taskstatus]:"+taskstatus);
		//executionService.deleteProcessInstanceCascade(executionId);
		//taskService.deleteTask(taskId);
		RectiDangerEntity entity = rectiDangerService.getById(javaid);
		//删除rectiDanger的数据
		rectiDangerService.delete(entity,request);
		//删除已经发起的流程
		//下列情况不需要删除流程
		//processid为空的
		if ( common.isEmpty(processid)) {
			//不删除
			logger.info("["+this.getClass().getName()+"][deleteListRectiDanger][不删除]");
		} else {
			executionService.deleteProcessInstanceCascade(processid);
		}
		
		//隐患类型list取得
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		request.setAttribute("listRectiAc", listRectiAc);
		logger.info("["+this.getClass().getName()+"][deleteListRectiDanger][goto][listRectiDanger.jsp]");
		logger.info("["+this.getClass().getName()+"][deleteListRectiDanger][end]");
		return "listRectiDanger";
	}
	
	@RequestMapping("toListRectiDanger")
	public String  toListRectiDanger(String listRectiAc,HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][toListRectiDanger][start]");
		
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
		//List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		//request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		logger.info("["+this.getClass().getName()+"][toListRectiDanger][end]");
		return "listRectiDanger";
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
		Pagination pag = rectiDangerService.getByUserId(id
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
		
		
		RectiDangerEntity entity = new RectiDangerEntity();
		
		//表单内容设置到entity
		entity = rectiDangerService.setEntityFromRequest(request, entity);
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
		
		//取得发起人所在单位的[分管安全领导]
		String approver2 = userService.getApprover2ById(entity.getStarter()).getId();

		if (entity.getTaskname().contains("部门负责人") ){
			if ( strTrans.equals("审批") ){
				entity.setLeader(id);
				entity.setLeadername(name);
				entity.setLeaderokdate(df.format(date));
				entity.setTaskname("分管领导:"+approver2+"审批");
			} else {
				entity.setLeadername("");
				entity.setLeaderokdate("");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("manager", approver2);
			taskService.setVariables(taskid, map);
			
		} else if (entity.getTaskname().contains("分管领导") ){
			if ( strTrans.equals("审批") ){
				entity.setManager(id);
				entity.setManagername(name);
				entity.setManagerokdate(df.format(date));
				entity.setTaskname("发起人验收导出审批单:"+entity.getStarter()+"负责");
			} else {
				entity.setManager("");
				entity.setManagername("");
				
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("starter", entity.getStarter());
			
			taskService.setVariables(taskid, map);
		
		} else if (entity.getTaskname().contains("发起人验收导出审批单") ) {
			if  (strTrans.equals("结束")) {
				entity.setProcessid("");				
				entity.setTaskname("发起人验收结束流程。"+entity.getStarter());
				
				entity.setStarterokdate(df.format(date));
			} else {
				
				
			}
		}
		//javaid存在
		if (rectiDangerService.checkIdExist(entity)>0) {
			rectiDangerService.update(entity);
		//javaid不存在
		} else {
			rectiDangerService.insert(entity);
		}
		
		//Map<String, String> map2 = new HashMap<String, String>();
		//map2.put("safetymanager", safetymanager);
		//taskService.setVariables(taskid, map2);
		
		taskService.completeTask(taskid, strTrans);
		request.setAttribute("strTrans", strTrans);
		request.setAttribute("entity", entity);
		request.setAttribute("listRectiAc", "this_month");
		
		//隐患类型list取得
		//List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		//request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		common.clear_session(request);
		
		logger.info("["+this.getClass().getName()+"][complete][goto][listRectiDanger.jsp]");
		logger.info("["+this.getClass().getName()+"][complete][end]");
		return "listRectiDanger";
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
		
		RectiDangerEntity entity = rectiDangerService.getById(javaid);
		entity.setTaskid(taskid);
		//javaid存在
		if (rectiDangerService.checkIdExist(entity)>0) {
			rectiDangerService.update(entity);
		//javaid不存在
		} else {
			rectiDangerService.insert(entity);
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
		//List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		//request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		//检查地点list取得
		//List<LocationEntity>  locationList =locationService.getAll();
		//request.setAttribute("locationList",locationList);
		
		request.setAttribute("entity", entity);
		
		request = rectiDangerService.setRequestFromEntity(request, entity);
		
		request.setAttribute("listRectiAc", listRectiAc);
		logger.info("["+this.getClass().getName()+"][get_][listRectiAc]:"+listRectiAc);
		logger.info("["+this.getClass().getName()+"][get_][goto][rectiDanger.jsp]");
		logger.info("["+this.getClass().getName()+"][get_][end]");
		return "rectiDanger";
	}


	
	


	
	@RequestMapping("printword")
	public String printword(HttpServletRequest request) throws IOException{
		logger.info("["+this.getClass().getName()+"][printword][start]");
		
		RectiDangerEntity entity = new RectiDangerEntity();
		entity = rectiDangerService.setEntityFromRequest(request, entity);
		
		logger.info("["+this.getClass().getName()+"][printword][location:]"+entity.getLocation());
		
	    Map<String, Object> param = new HashMap<String, Object>();
	    
	    param.put("あ", entity.getDanwei());
	    param.put("い", entity.getStarter());
	    param.put("う", entity.getDangertype());
	    if (common.isEmpty(entity.getStartdate()) || common.isEmpty(entity.getEnddate())) {
	    	param.put("え", "");	
	    } else {
	    	param.put("え", entity.getStartdate() + "至" + entity.getEnddate());
	    }
	    param.put("お", entity.getLocation());
	    param.put("か", entity.getOperator());
	    param.put("き", entity.getPrecaution());
	    param.put("く", entity.getPrecautionchecker());
	    param.put("け", entity.getInform());
	    param.put("こ", entity.getInformer()); 
	    param.put("さ", entity.getLeaderokmessage()); 
	    param.put("し", entity.getLeader()); 
	    param.put("す", entity.getLeaderokdate());
	    param.put("せ", entity.getManagerokmessage());
	    param.put("そ", entity.getManager());
	    param.put("な", entity.getManagerokdate());
     
	    	    
	    String strModelFileOfRealServerPath = 
	    		request.getRealPath("/") + "modelfile\\rectidanger01.docx";
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
	    request.setAttribute("strFileNameForView", "危险作业审批单");
	    
	    logger.info("["+this.getClass().getName()+"][printword][end]");
	    logger.info("["+this.getClass().getName()+"][printword][goto][printword.jsp]");
	    
	    
	    return "printword";
	}
}
