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
	private RepositoryService repositoryService;//-���̹������𷢲�
	
	@Resource(name="taskService")
	private TaskService taskService;//�������
	
	@Resource(name="userService")
	private UserService userService;//�������
	
	@Resource(name="executionService")
	private ExecutionService executionService;//-����ʵ������
	
	@Resource(name="rectiCheckService")
	private RectiCheckService rectiCheckService;//ҵ���߼�
	
	@Resource(name="danweiService")
	private DanweiService danweiService;//ҵ���߼�
	
	@Resource(name="locationService")
	private LocationService locationService;//ҵ���߼�
	
	@Resource(name="rectiSearchService")
	private RectiSearchService rectiSearchService;//ҵ���߼�
	
	@Resource(name="myJbpmService")
	private MyJbpmService myJbmpService;//ҵ���߼�
	@Resource(name="historyService")
	private HistoryService historyService;//ҵ���߼�

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
		
		String taskname = "��ʼ�ڵ�";
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
		//���쵼listȡ��
		List<UserEntity>  townofficerList =userService.getUserListByTownOfficer(user_danwei);
		request.setAttribute("townofficerList",townofficerList);
		//��λlistȡ��
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//���������ڵ�λȡ��
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//���ص�listȡ��
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		//��������listȡ��
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
		//���������õ�entity
		entity = rectiCheckService.setEntityFromRequest(request, entity);
		
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//���쵼listȡ��
		List<UserEntity>  townofficerList =userService.getUserListByTownOfficer(user_danwei);
		request.setAttribute("townofficerList",townofficerList);
		//��λlistȡ��
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//���������ڵ�λȡ��
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//���ص�listȡ��
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		//��������listȡ��
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		logger.info("["+this.getClass().getName()+"][toRectiCheck][��λ���]"+entity.getDanwei());
		
		
		
		String danweilongname = "�оֹ�˾";
		String danweishortname ="�о�";
		
		for (int i=0;i<danweiList.size();i++) {
			danweishortname = danweiList.get(i).getId();
			if (danweishortname.equals(entity.getDanwei())) {
				danweilongname =danweiList.get(i).getName(); 
			}
		}
		logger.info("["+this.getClass().getName()+"][toRectiCheck][��λȫ��]"+danweilongname);
		
		UserEntity entity_danwei1bashou = userService.getTownofficerByDanwei(danweilongname);
		logger.info("["+this.getClass().getName()+"][toRectiCheck][��λ1����]"+entity_danwei1bashou.getId());
		entity.setBecheckedbuleader(entity_danwei1bashou.getId());
		
		request = rectiCheckService.setRequestFromEntity(request, entity);
		
		request.setAttribute("entity",entity);
		
		logger.info("["+this.getClass().getName()+"][toRectiCheck][goto][rectiCheck.jsp]");
		logger.info("["+this.getClass().getName()+"][toRectiCheck][end]");
		return "rectiCheck";
	}
	
	

	//��������
	@RequestMapping("start_")
	public String start_(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][start_][start]");
		RectiCheckEntity entity = new RectiCheckEntity();
		//���������õ�entity
		entity = rectiCheckService.setEntityFromRequest(request, entity);		
		logger.info("["+this.getClass().getName()+"][start_][becheckedbuleader]:"+entity.getBecheckedbuleader());				
		Map<String, String> map = new HashMap<String, String>();
		map.put("javaid", entity.getJavaid());
		map.put("starter", entity.getStarter());
		
		//��������
		ProcessInstance ins = executionService.startProcessInstanceByKey("rectification6", map);
		logger.info("["+this.getClass().getName()+"][start_][process start]");
		logger.info("["+this.getClass().getName()+"][start_][process id]:"+ins.getId());
		

		//�嵥��������Ϣ�־û�
		entity.setProcessid(ins.getId());
		entity.setTaskname("��Ҫ������:"+entity.getBecheckedbuleader()+"����");
		//javaid����
		if (rectiCheckService.checkIdExist(entity)>0) {
			rectiCheckService.update(entity);
		//javaid������
		} else {
			rectiCheckService.insert(entity);
		}
		
		//��ɵ�һ���ڵ�
		Map<String, String> map2 = new HashMap<String, String>();
		String becheckedbuleader = entity.getBecheckedbuleader();
		logger.info("["+this.getClass().getName()+"][start_][becheckedbuleader]:"+becheckedbuleader);
		map2.put("leader", becheckedbuleader);
		
		String taskId =myJbmpService.getTaskIdByProcessId(ins.getId());
		logger.info("["+this.getClass().getName()+"][start_][task id]:"+taskId);
		taskService.setVariables(taskId, map2);
		taskService.completeTask(taskId, "��������");
		
		request.setAttribute("prepagejsp", "rectiCheck.jsp");
		request.setAttribute("listRectiAc", "this_month");
		
		//��������listȡ��
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
		
		//���������õ�entity
		entity = rectiCheckService.setEntityFromRequest(request, entity);
		
		
		//javaid����
		if (rectiCheckService.checkIdExist(entity)>0) {
			rectiCheckService.update(entity);
		//javaid������
		} else {
			rectiCheckService.insert(entity);
		}
		
		//���ݿ������������ȡ��
		RectiCheckEntity entitySaved = rectiCheckService.getById(entity.getJavaid());
		request.setAttribute("entity",entity);
		
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//���쵼listȡ��
		List<UserEntity>  townofficerList =userService.getUserListByTownOfficer(user_danwei);
		request.setAttribute("townofficerList",townofficerList);
		//��λlistȡ��
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//���������ڵ�λȡ��
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//���ص�listȡ��
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		//��������listȡ��
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		if (pageid.equals("rectiCheck.jsp")) {
			request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
			request.setAttribute("messeageForSave", "����ɹ��������ť�鿴��");
			
			
			request = rectiCheckService.setRequestFromEntity(request, entitySaved);

			logger.info("["+this.getClass().getName()+"][save_][end]---goto[rectiCheck.jsp]");
			return "rectiCheck";
		} else {
			request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
			request.setAttribute("taskName", "����������");
			logger.info("["+this.getClass().getName()+"][save_][end]---goto[detail.jsp]");
			return "detail";
		}

	}


	//ɾ����������Ϣ����ҳ���ã�
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
		//ɾ��rectiCheck������
		rectiCheckService.delete(entity,request);
		
		//ɾ���Ѿ����������
		//�����������Ҫɾ������
		//processidΪ�յ�
		if ( common.isEmpty(processid)) {
			//��ɾ��
		} else {
			executionService.deleteProcessInstanceCascade(processid);
		}
		request.setAttribute("listRectiAc", listRectiAc);
		
		//��������listȡ��
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
		
		//��������listȡ��
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
		
		
		//���õ�ǰҳ
        int intPage = (page==null||page<=0)?1:page;
        //����ÿҳ��ʾ������
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
	//�������
	@RequestMapping("complete")
	public String complete(String taskid, HttpServletRequest request) throws UnsupportedEncodingException {
		logger.info("["+this.getClass().getName()+"][complete][start]");
		String strResult = request.getParameter("strResult");
		String strTrans = request.getParameter("strTrans");
		
		logger.info("["+this.getClass().getName()+"][complete]["+strResult+"]["+strTrans+"]");
		logger.info("["+this.getClass().getName()+"][complete][taskid]:"+taskid);
		
		RectiCheckEntity entity = new RectiCheckEntity();
		
		//���������õ�entity
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
		//�о��ۺϰ칫��
		String cityofficer = userService.getGeofficerByDanwei("�оֹ�˾").getId();
		logger.info("["+this.getClass().getName()+"][complete][cityofficer]:"+cityofficer);
		//�ܼ쵥λ���ۺϰ칫��
		DanweiEntity  entity_danwei2 =danweiService.getFullName(entity.getDanwei());
		String danwei_fullname2 = entity_danwei2.getName();
		logger.info("["+this.getClass().getName()+"][complete][danwei_fullname]:"+danwei_fullname2);
		String becheckedDanweiGeOfficer = userService.getGeofficerByDanwei(danwei_fullname2).getId();
		logger.info("["+this.getClass().getName()+"][complete][becheckedDanweiGeOfficer]:"+becheckedDanweiGeOfficer);
		//�оְ�ȫ����Ա
		String safetymanager = userService.getSafetymanagerByDanwei("�оֹ�˾").getId();
		
		if (entity.getTaskname().contains("��Ҫ������") ){
			if ( strTrans.equals("�ύ") ){
				entity.setTownofficer(id);
				entity.setTownofficerokdate(df.format(date));
				entity.setTaskname("�ۺϰ칫�ң�"+becheckedDanweiGeOfficer+"����");
			} else {
				entity.setTownofficer("");
				entity.setTownofficerokdate("");
			}
			
			Map<String, String> map = new HashMap<String, String>();

			map.put("geofficer", becheckedDanweiGeOfficer);
			
			taskService.setVariables(taskid, map);
		//�Ƚ����ж�	
		} else if (entity.getTaskname().contains("�ۺϰ칫���ٴ�����") ) {
			
			if  (strTrans.equals("����")) {
				entity.setGeofficer(id);
				entity.setGeofficername(name);
				entity.setGeofficerokdate(df.format(date));
				entity.setTaskname("�оְ�ȫ����Աȷ������:"+safetymanager);
			} else {
				entity.setGeofficer("");
				entity.setGeofficername("");
			}
	
		} else if (entity.getTaskname().contains("�ۺϰ칫��") ){
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("cityofficer", cityofficer);
			taskService.setVariables(taskid, map1);
			if ( strTrans.equals("����") ){
				if ("rectiLevelYes".equals(strResult)){
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("rectiLevel", Integer.valueOf(1));
					taskService.setVariables(taskid, map);
					entity.setRectilevel("�ش�����");
					entity.setTaskname("�оְ칫�ң�"+cityofficer+"����");
				} else if ("rectiLevelNo".equals(strResult)){
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("rectiLevel", Integer.valueOf(0));
					taskService.setVariables(taskid, map);
					entity.setRectilevel("���ش�����");
					entity.setTaskname("�оְ�ȫ����Աȷ�����ա�"+safetymanager);
				}
				entity.setGeofficer(id);
				entity.setGeofficername(name);
				entity.setGeofficerokdate(df.format(date));
			} else {
				entity.setGeofficer("");
				entity.setGeofficername("");
			}
			
		} else if (entity.getTaskname().contains("�оְ칫��") ) {
			
			if  (strTrans.equals("����")) {
				entity.setCityofficer(id);
				entity.setCityofficername(name);
				entity.setCityofficerokdate(df.format(date));
				entity.setTaskname("�ۺϰ칫���ٴ�����:"+becheckedDanweiGeOfficer);
			} else {
				entity.setCityofficer("");
				entity.setCityofficername("");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("geofficer", becheckedDanweiGeOfficer);
			taskService.setVariables(taskid, map);
			
		} else if (entity.getTaskname().contains("�оְ�ȫ����Աȷ������") ) {
			if  (strTrans.equals("����")) {
				entity.setSafetymanager(id);
				entity.setSafetymanagername(name);
				entity.setSafetymanagerokdate(df.format(date));
				entity.setTaskname("�оְ�ȫ����Ա���ս������̡�"+safetymanager);
			} else {
				entity.setSafetymanager("");
				entity.setSafetymanagername("");
			}
			
		}
		//javaid����
		if (rectiCheckService.checkIdExist(entity)>0) {
			rectiCheckService.update(entity);
		//javaid������
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
		//��������listȡ��
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
		//javaid����
		if (rectiCheckService.checkIdExist(entity)>0) {
			rectiCheckService.update(entity);
		//javaid������
		} else {
			rectiCheckService.insert(entity);
		}
		
				
		request.setAttribute("entity", entity);

		request = rectiCheckService.setRequestFromEntity(request, entity);
		
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//���쵼listȡ��
		List<UserEntity>  townofficerList =userService.getUserListByTownOfficer(user_danwei);
		request.setAttribute("townofficerList",townofficerList);
		//��λlistȡ��
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//���������ڵ�λȡ��
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//���ص�listȡ��
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		//��������listȡ��
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
	    param.put("��", entity.getNo());
	    param.put("��", entity.getDanwei());
	    param.put("��", entity.getCheckdate());
	    param.put("��", entity.getDeadlinedate());
	    param.put("��", entity.getName());
	    param.put("��", entity.getCheckname());
	    param.put("��", entity.getContent());
	    param.put("��", entity.getRecti1());
	    param.put("��", entity.getRecti2());
	    param.put("��", entity.getRecti3());
	    param.put("��", entity.getRecti4());
	    param.put("��", entity.getNrecti1());
	    param.put("��", entity.getNrecti2());
	    param.put("��", entity.getNrecti3());
	    param.put("��", entity.getNrecti4());
	    param.put("��", entity.getNrecti5());
	    param.put("��", entity.getNrecti6());
	    param.put("��", entity.getNrecti7());
	    param.put("��", entity.getNrecti8());
	    param.put("��", entity.getRectisuggest());
	    param.put("��", entity.getRectistatus());
	    param.put("��", entity.getBecheckedbuleadername()); 
	    param.put("��", entity.getRecticheckteamleadername());
	    param.put("��", entity.getRectimakesurename());
	    param.put("��", entity.getTownofficerokmessage());
	    param.put("��", entity.getTownofficer());
	    param.put("��", entity.getTownofficerokdate());
	    param.put("��", entity.getGeofficerokmessage());
	    param.put("��", entity.getGeofficername());
	    param.put("��", entity.getGeofficerokdate());
	    param.put("��", entity.getCityofficerokmessage());
	    param.put("��", entity.getCityofficername());
	    param.put("��", entity.getCityofficerokdate());
	    param.put("��", entity.getSafetymanagerokmessage());
	    param.put("��", entity.getSafetymanagername());
	    param.put("��", entity.getSafetymanagerokdate());
	    param.put("��", entity.getLocation1() + " " + entity.getLocation2() + " "+ entity.getLocation3());
	
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
	    request.setAttribute("strFileNameForView", "��ȫ����¼�� ");
	    
	    
	    logger.info("["+this.getClass().getName()+"][printword][end]");
	    logger.info("["+this.getClass().getName()+"][printword][goto][printword.jsp]");
	    return "printword";   
	}
}
