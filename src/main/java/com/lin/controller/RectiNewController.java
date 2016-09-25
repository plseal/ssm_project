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
	private RepositoryService repositoryService;//-���̹������𷢲�
	
	@Resource(name="taskService")
	private TaskService taskService;//�������
	
	@Resource(name="userService")
	private UserService userService;//�������
	
	@Resource(name="executionService")
	private ExecutionService executionService;//-����ʵ������
	
	@Resource(name="rectiNewService")
	private RectiNewService rectiNewService;//ҵ���߼�
	
	@Resource(name="rectiSearchService")
	private RectiSearchService rectiSearchService;//ҵ���߼�
	
	@Resource(name="danweiService")
	private DanweiService danweiService;//ҵ���߼�
	
	@Resource(name="locationService")
	private LocationService locationService;//ҵ���߼�
	
	@Resource(name="myJbpmService")
	private MyJbpmService myJbmpService;//ҵ���߼�
	
	@Resource(name="historyService")
	private HistoryService historyService;//ҵ���߼�

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
		
		String taskname = "��ʼ�ڵ�";
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
		//���Ÿ�����listȡ��
		List<UserEntity>  leaderList =userService.getUserListByLeader(user_danwei);
		request.setAttribute("leaderList",leaderList);
		//�ֹ��쵼listȡ��
		List<UserEntity>  managerList =userService.getUserListByManager(user_danwei);
		request.setAttribute("managerList",managerList);
		//��λlistȡ��
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//���������ڵ�λȡ��
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//��������listȡ��
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		//���ص�listȡ��
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
		
		//���������õ�entity
		entity = rectiNewService.setEntityFromRequest(request, entity);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("javaid", entity.getJavaid());
		map.put("starter", entity.getStarter());
		
		//��������
		ProcessInstance ins = executionService.startProcessInstanceByKey("rectification", map);
		logger.info("["+this.getClass().getName()+"][start_][process start]");
		logger.info("["+this.getClass().getName()+"][start_][process id]:"+ins.getId());
		
		
		//�嵥��������Ϣ�־û�
		entity.setProcessid(ins.getId());
		entity.setTaskname("���Ÿ�����:"+entity.getBumenfuzeren()+"����");
		//javaid����
		if (rectiNewService.checkIdExist(entity)>0) {
			rectiNewService.update(entity);
		//javaid������
		} else {
			rectiNewService.insert(entity);
		} 
		
		//��ɵ�һ���ڵ�
		Map<String, String> map2 = new HashMap<String, String>();
		
		map2.put("leader", entity.getBumenfuzeren());
		
		String taskId =myJbmpService.getTaskIdByProcessId(ins.getId());
		logger.info("["+this.getClass().getName()+"][start_][task id]:"+taskId);
		taskService.setVariables(taskId, map2);
		
		taskService.completeTask(taskId, "��������");
		
		
		request.setAttribute("prepagejsp", "rectiNew.jsp");
		request.setAttribute("listRectiAc", "this_month");
		
		//��������listȡ��
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
		
		//���������õ�entity
		entity = rectiNewService.setEntityFromRequest(request, entity);
		
		
		//javaid����
		if (rectiNewService.checkIdExist(entity)>0) {
			rectiNewService.update(entity);
		//javaid������
		} else {
			rectiNewService.insert(entity);
		}
		
		//���ݿ������������ȡ��
		RectiNewEntity entitySaved = rectiNewService.getById(entity.getJavaid());
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//���Ÿ�����listȡ��
		List<UserEntity>  leaderList =userService.getUserListByLeader(user_danwei);
		request.setAttribute("leaderList",leaderList);
		//�ֹ��쵼listȡ��
		List<UserEntity>  managerList =userService.getUserListByManager(user_danwei);
		request.setAttribute("managerList",managerList);
		//��λlistȡ��
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//���������ڵ�λȡ��
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//��������listȡ��
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		
		//���ص�listȡ��
		List<LocationEntity>  locationList =locationService.getAll();
		request.setAttribute("locationList",locationList);
		
		request.setAttribute("entity",entity);
		
		if (pageid.equals("rectiNew.jsp")) {
			request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
			request.setAttribute("messeageForSave", "����ɹ��������ť�鿴��");
			
			
			request = rectiNewService.setRequestFromEntity(request, entitySaved);

			logger.info("["+this.getClass().getName()+"][save_][end]---goto[rectiNew.jsp]");
			return "rectiNew";
		} else {
			request.setAttribute("prepagejsp", "rectiNewEntrance.jsp");
			request.setAttribute("taskName", "����������");
			logger.info("["+this.getClass().getName()+"][save_][end]---goto[detail.jsp]");
			return "detail";
		}

	}



	//ɾ����������Ϣ����ҳ���ã�
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
		//ɾ��rectiNew������
		rectiNewService.delete(entity,request);
		//ɾ���Ѿ����������
		//�����������Ҫɾ������
		//processidΪ�յ�
		if ( common.isEmpty(processid)) {
			//��ɾ��
		} else {
			executionService.deleteProcessInstanceCascade(processid);
		}
		
		//��������listȡ��
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
		
		//��������listȡ��
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
		
		
		//���õ�ǰҳ
        int intPage = (page==null||page<=0)?1:page;
        //����ÿҳ��ʾ������
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
	//�������
	@RequestMapping("complete")
	public String complete(String taskid, HttpServletRequest request) throws UnsupportedEncodingException {
		logger.info("["+this.getClass().getName()+"][complete][start]");
		String strResult = request.getParameter("strResult");
		String strTrans = request.getParameter("strTrans");
		
		logger.info("["+this.getClass().getName()+"][complete]["+strResult+"]["+strTrans+"]");
		logger.info("["+this.getClass().getName()+"][complete][taskid]:"+taskid);
		
		
		RectiNewEntity entity = new RectiNewEntity();
		
		//���������õ�entity
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
		if (entity.getTaskname().contains("���Ÿ�����") ){
			if ( strTrans.equals("����") ){
				entity.setLeader(id);
				entity.setLeadername(name);
				entity.setLeaderokdate(df.format(date));
				entity.setTaskname("�ֹ��쵼:"+entity.getFenguanlingdao()+"����");
			} else {
				entity.setLeadername("");
				entity.setLeaderokdate("");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("manager", entity.getFenguanlingdao());
			taskService.setVariables(taskid, map);
			
		} else if (entity.getTaskname().contains("�ֹ��쵼") ){
			if ( strTrans.equals("����") ){
				entity.setManager(id);
				entity.setManagername(name);
				entity.setManagerokdate(df.format(date));
				entity.setTaskname("�칫��:"+geofficer+"����");
			} else {
				entity.setManager("");
				entity.setManagername("");
				
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("geofficer", geofficer);
			
			taskService.setVariables(taskid, map);
		} else if (entity.getTaskname().contains("�칫��") ){
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("townofficer", townofficer);
			map1.put("cityofficer", cityofficer);
			taskService.setVariables(taskid, map1);
			if ( strTrans.equals("����") ){
				if ("rectiLevelYes".equals(strResult)){
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("rectiLevel", Integer.valueOf(1));

					taskService.setVariables(taskid, map);
					entity.setRectilevel("�ش�����");
					entity.setTaskname("��Ҫ������:"+townofficer+"����");
				} else if ("rectiLevelNo".equals(strResult)){
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("rectiLevel", Integer.valueOf(0));
					taskService.setVariables(taskid, map);
					entity.setRectilevel("���ش�����");
					entity.setTaskname("��������������:"+entity.getStarter());
				}
				entity.setGeofficer(id);
				entity.setGeofficername(name);
				entity.setGeofficerokdate(df.format(date));
			} else if (strTrans.equals("������")) {
				entity.setGeofficer(id);
				entity.setGeofficername(name);
				entity.setGeofficerokdate(df.format(date));

				entity.setTaskname("��ȫ����Ա����"+safetymanager);
			} else {
				entity.setGeofficer("");
				entity.setGeofficername("");
			}
			
		} else if (entity.getTaskname().contains("��Ҫ������") ){
			if ( strTrans.equals("����") ){
				entity.setTownofficer(id);
				entity.setTownofficername(name);
				entity.setTownofficerokdate(df.format(date));
				entity.setTaskname("�о�:"+cityofficer+"����");
			} else {
				entity.setTownofficer("");
				entity.setTownofficername("");
			}
			
		} else if (entity.getTaskname().contains("�о�") ) {
			if  (strTrans.equals("����")) {
				entity.setCityofficer(id);
				entity.setCityofficername(name);
				entity.setCityofficerokdate(df.format(date));
				entity.setTaskname("�칫���ٴ�����:"+starterGeOfficer);
			} else {
				entity.setCityofficer("");
				entity.setCityofficername("");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("geofficer", starterGeOfficer);
			taskService.setVariables(taskid, map);
		} else if (entity.getTaskname().contains("��ȫ����Ա����") ) {
			if  (strTrans.equals("����")) {
				entity.setSafetymanager(id);
				entity.setSafetymanagername(name);
				entity.setSafetymanagerokdate(df.format(date));
				entity.setProcessid("");
				
				entity.setTaskname("��ȫ����Ա���ս������̡�"+safetymanager);
			} else {
				entity.setSafetymanager("");
				entity.setSafetymanagername("");
			}
		} else if (entity.getTaskname().contains("��������������") ) {
			if  (strTrans.equals("����")) {
				entity.setProcessid("");				
				entity.setTaskname("�������������ս������̡�"+entity.getStarter());
				
				entity.setStarterokdate(df.format(date));
			} else {
				
				
			}
		}
		//javaid����
		if (rectiNewService.checkIdExist(entity)>0) {
			rectiNewService.update(entity);
		//javaid������
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
		
		//��������listȡ��
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
		//javaid����
		if (rectiNewService.checkIdExist(entity)>0) {
			rectiNewService.update(entity);
		//javaid������
		} else {
			rectiNewService.insert(entity);
		}
		String user_danwei = (String)request.getSession().getAttribute("user_danwei");
		//���Ÿ�����listȡ��
		List<UserEntity>  leaderList =userService.getUserListByLeader(user_danwei);
		request.setAttribute("leaderList",leaderList);
		//�ֹ��쵼listȡ��
		List<UserEntity>  managerList =userService.getUserListByManager(user_danwei);
		request.setAttribute("managerList",managerList);
		//��λlistȡ��
		List<DanweiEntity>  danweiList =danweiService.getAll();
		request.setAttribute("danweiList",danweiList);
		//���������ڵ�λȡ��
		DanweiEntity  danwei_entity =danweiService.getShortName(user_danwei);
		request.setAttribute("user_danwei",danwei_entity.getId());
		//��������listȡ��
		List<LocationEntity>  yinhuanTypeList =locationService.getYinhuanType();
		request.setAttribute("yinhuanTypeList",yinhuanTypeList);
		//���ص�listȡ��
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
	    param.put("��", entity.getNo());    
	    param.put("��", entity.getName()); 
	    param.put("��", entity.getLocation());
	    param.put("��", entity.getCheckdate()); 
	    param.put("��", entity.getContent());
	    //param.put("��", entity.getOkstandard());
	    param.put("��", entity.getOkmeasure());
	    param.put("��", entity.getLeadername());
	    param.put("��", entity.getLeaderokdate()); 
	    param.put("��", entity.getLeaderokmessage()); 
	    param.put("��", entity.getManagername()); 
	    param.put("��", entity.getManagerokdate());
	    param.put("��", entity.getManagerokmessage()); 
	    param.put("��", entity.getGeofficername());
	    param.put("��", entity.getGeofficerokdate());
	    param.put("��", entity.getGeofficerokmessage());
	    param.put("��", entity.getTownofficername());
	    param.put("��", entity.getTownofficerokdate());
	    param.put("��", entity.getTownofficerokmessage());
	    param.put("��", entity.getCityofficername());
	    param.put("��", entity.getCityofficerokdate());
	    param.put("��", entity.getCityofficerokmessage()); 
	    //��ȫ����Ա������Ϣ
	    if (!"".equals(entity.getSafetymanagerokdate())) {
		    param.put("��", entity.getSafetymanagername());
		    param.put("��", entity.getSafetymanagerokdate());  
		    param.put("��", entity.getSafetymanagerokmessage());
		//����������������Ϣ
	    } else {
		    param.put("��", entity.getStarter());
		    param.put("��", entity.getStarterokdate());  
		    param.put("��", entity.getStarterokmessage());
	    	
	    }
	    param.put("��", entity.getDanwei());


	    	    
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
	    request.setAttribute("strFileNameForView", "��ȫ����������ʵ�嵥");
	    
	    logger.info("["+this.getClass().getName()+"][printword][end]");
	    logger.info("["+this.getClass().getName()+"][printword][goto][printword.jsp]");
	    
	    
	    return "printword";
	}
}
