package com.lin.controller;

import com.lin.entity.*;
import com.lin.service.DanweiService;
import com.lin.service.MyJbpmService;
import com.lin.service.RectiSearchService;
import com.lin.service.UserService;
import org.apache.log4j.Logger;
import org.jbpm.api.*;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.zip.ZipInputStream;
@Controller
@RequestMapping("admin")
public class AdminController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AdminController.class);
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;//-���̹������𷢲�
	
	@Resource(name="taskService")
	private TaskService taskService;//�������
	
	@Resource(name="userService")
	private UserService userService;//�������
	
	
	@Resource(name="danweiService")
	private DanweiService danweiService;
	
	@Resource(name="rectiSearchService")
	private RectiSearchService rectiSearchService;
	
	@Resource(name="myJbpmService")
	private MyJbpmService myJbpmService;//�������
	
	@Resource(name="executionService")
	private ExecutionService executionService;//-����ʵ������
	
	@Resource(name="historyService")
	private HistoryService historyService;//ҵ���߼�
	
	public AdminController()
	{
	}

	@RequestMapping("deployZIP")
	public String deployZIP(MultipartFile file){
		logger.info("["+this.getClass().getName()+"][deployZIP][start]");
		
		ZipInputStream zip = null;
		try{
			zip = new ZipInputStream(file.getInputStream());
		}catch (IOException e){
			e.printStackTrace();
		}
		String id = repositoryService.createDeployment().addResourcesFromZipInputStream(zip).deploy();
		logger.info("["+this.getClass().getName()+"][deployZIP][end]--goto[listdep.jsp]"+id);
		return "listdep";
	}
	//��ȡ���̶���
	@RequestMapping("listProcessDefinition")
	public @ResponseBody List<DeployEntity> listProcessDefinition(){
		List<ProcessDefinition> depList = repositoryService.createProcessDefinitionQuery().list();
		List<DeployEntity> list = new ArrayList<DeployEntity>();
		for(int i=0;i<depList.size();i++){
			DeployEntity de = new DeployEntity();
			de.setId(depList.get(i).getId());
			de.setKey(depList.get(i).getKey());
			de.setName(depList.get(i).getName());
			list.add(de);
		}
		return list;
	}
	@RequestMapping("loginnew")
	public String loginnew(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][loginnew][start]");
		

		String strToJsp = "";
		String id = request.getParameter("id");
		String pas = request.getParameter("pas");
		String preview = request.getParameter("preview");
		
		logger.info("["+this.getClass().getName()+"][loginnew][loginid]"+id);
		//���ID�Ƿ����
		//ID������
		if (userService.checkIdExist(id) < 1) {
			strToJsp = "error-login";
		//ID����pas����
		} else if (userService.checkPas(id,pas) < 1){
			request.setAttribute("errorMSG", "�������");
			strToJsp = "login";
		//ID����
		} else {
			//try {
			UserEntity ue = userService.getById(id);
			String name = ue.getName();
			String role = ue.getRole();
			//String leader = ue.getLeader();
			//UserEntity ue2 = userService.getById(leader);
			//String leadername = ue2.getName();
			String user_danwei = ue.getUser_danwei();
			logger.info("["+this.getClass().getName()+"][loginnew][loginname]"+name);
			logger.info("["+this.getClass().getName()+"][loginnew][loginrole]"+role);
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("role", role);
			//request.getSession().setAttribute("leader", leader);
			//request.getSession().setAttribute("leadername", leadername);
			request.getSession().setAttribute("user_danwei", user_danwei);
			
			
			strToJsp = "indexNew";
		}
		//��ʾ����
		if ("true".equals(preview)) {
			strToJsp = "indexNew_preview";
		
		}
		logger.info("["+this.getClass().getName()+"][loginnew][end]---goto["+strToJsp+"]");
		return strToJsp;
	}

	
	
	@RequestMapping("userAdminGet")
	public @ResponseBody List<UserEntity> userAdminGet(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][userAdminGet][start]");
		String s_danwei     = (String)request.getSession().getAttribute("s_danwei");
		String s_zhiwei     = (String)request.getSession().getAttribute("s_zhiwei");
		logger.info("["+this.getClass().getName()+"][userAdminGet][s_danwei]"+s_danwei);
		logger.info("["+this.getClass().getName()+"][userAdminGet][s_zhiwei]"+s_zhiwei);
		RectiSearchEntity entity = new RectiSearchEntity();
		entity.setS_danwei(s_danwei);
		entity.setS_zhiwei(s_zhiwei);
		List<UserEntity>  list = userService.getUserListByDanwei(entity);
		logger.info("["+this.getClass().getName()+"][userAdminGet][end]");
		return list;
	}
	
	@RequestMapping("to_userAdminJSP")
	public String to_userAdminJSP(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][to_userAdminJSP][start]");
		//��ù�˾һ��(������)
		List<DanweiEntity> danwei_For_Search_List = danweiService.getAll();
		request.setAttribute("danwei_For_Search_List",danwei_For_Search_List);
		
		RectiSearchEntity entity = new RectiSearchEntity();
		entity = rectiSearchService.setEntityFromRequest(request, entity);
		logger.info("["+this.getClass().getName()+"][to_userAdminJSP][s_danwei]"+entity.getS_danwei());
		request.getSession().setAttribute("s_danwei",     entity.getS_danwei());
		request.getSession().setAttribute("s_zhiwei",     entity.getS_zhiwei());
		
		logger.info("["+this.getClass().getName()+"][to_userAdminJSP][end]");
		return "userAdmin";
	}
	
	@RequestMapping("userAdminUpdate")
	public String userAdminUpdate(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][userAdminUpdate][start]");
		String id   = (String)request.getParameter("id");
		
		UserEntity entity = userService.getById(id);

		request.setAttribute("entity", entity);
		logger.info("["+this.getClass().getName()+"][userAdminUpdate][end]");
		return "userAdminUpdate";
	}
	
	@RequestMapping("userAdminUpdateSave")
	public String userAdminUpdateSave(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][userAdminUpdateSave][start]");

		UserEntity entity = new UserEntity();
		entity = userService.setEntityFromRequest(request, entity);
		
		userService.update(entity);


		logger.info("["+this.getClass().getName()+"][userAdminUpdateSave][end]");
		return "userAdmin";
	}
	
	
	@RequestMapping("userAdminDelete")
	public String userAdminDelete(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][userAdminDelete][start]");
		String id   = (String)request.getParameter("id");
		
		UserEntity entity = userService.getById(id);
		userService.delete(entity);

		logger.info("["+this.getClass().getName()+"][userAdminDelete][end]");
		return "userAdmin";
	}
	
	@RequestMapping("gotoJSPuserAdminInsert")
	public String userAdminInsert(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][gotoJSPuserAdminInsert][start]");
		UserEntity entity = new UserEntity();
		
		request.setAttribute("entity", entity);
		logger.info("["+this.getClass().getName()+"][gotoJSPuserAdminInsert][end]");
		return "userAdminInsert";
	}
	
	@RequestMapping("userAdminInsert")
	public String userAdminInertSave(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][userAdminInsert][start]");
		UserEntity entity = new UserEntity();
		entity = userService.setEntityFromRequest(request, entity);
		RectiSearchEntity s_entity = new RectiSearchEntity();
		s_entity.setS_danwei("�оֹ�˾");
		List <UserEntity> list = userService.getUserListByDanwei(s_entity);
		
		String idCheck = "true";
		//�鿴�Ƿ����ظ����û�
		for (int i=0;i<list.size();i++) {
			if (entity.getId().equals(list.get(i).getId())){
				idCheck = "false";
			}
		}
		logger.info("["+this.getClass().getName()+"][userAdminInsert][end]");
		if (idCheck.equals("false")) {
			return "userAdminInsertError";
		} else {
			userService.insert(entity);
			return "userAdmin";
		}
		
	}
	//��ȡ��������
	@RequestMapping("listTask")
	public @ResponseBody List<TaskEntity> listTask(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][listTask][start]");
		String id   = (String)request.getSession().getAttribute("id");
				
		List<Map<String, Object>> list = myJbpmService.getPersonalTasks(id);
		logger.info("["+this.getClass().getName()+"][listTask][list.size]:"+list.size());
		List<TaskEntity> listTask = new ArrayList<TaskEntity>();
		request.getSession().setAttribute("listOfTaskID","XXXXXX");
		for(int i=0;i<list.size();i++){
			TaskEntity taskEntity = new TaskEntity();
			taskEntity.setTaskid(list.get(i).get("taskid").toString());
			taskEntity.setTaskname(list.get(i).get("taskname").toString());
			taskEntity.setAssignee(list.get(i).get("assignee").toString());
			taskEntity.setProcessid(list.get(i).get("processid").toString());
			taskEntity.setStarter(list.get(i).get("starter").toString());
			taskEntity.setJavaid(list.get(i).get("javaid").toString());
			
			listTask.add(taskEntity);
		}
		logger.info("["+this.getClass().getName()+"][listTask][end]");
		return listTask;
	}
	
	//��ȡMsg
	@RequestMapping("getMsg")
	public @ResponseBody Map<String, Object> getMsg(HttpServletRequest request){
		//��ѯ����log
		Map<String, Object> result = new HashMap<String, Object>();
		
		String id   = (String)request.getSession().getAttribute("id");
		String sessListOfTaskId = "";	
		List<Map<String, Object>> list = myJbpmService.getPersonalMsg(id);
		
		List<String> listOfTaskID = new ArrayList<String>();
		//���ݿ��taskһ��
		for(int i=0;i<list.size();i++){
			listOfTaskID.add(list.get(i).get("taskid").toString());
		}
		//���ݿ��taskһ����session�����taskһ���Ƚϣ�session�����taskһ��û�еĻ�����������
		if ( request.getSession().getAttribute("listOfTaskID") != null ) {
			sessListOfTaskId = request.getSession().getAttribute("listOfTaskID").toString();
		}
		for(int i=0;i<listOfTaskID.size();i++){
			if(sessListOfTaskId.contains(listOfTaskID.get(i))){
				result.put("msgsize", 0);
			} else {
				result.put("msgsize", 1);
				//����ѭ��
				i=listOfTaskID.size();
			}
			
		}

		request.getSession().setAttribute("listOfTaskID",listOfTaskID);
		return result;
	}
	
	
	//��ȡ������Ա�б�
	@RequestMapping("listNGnames")
	public @ResponseBody List<UserEntity> listNGnames(){
		logger.info("["+this.getClass().getName()+"][listNGnames][start]");
						
		List<UserEntity>  list = userService.getUserListByNGname();
		
		logger.info("["+this.getClass().getName()+"][listNGnames][end]");
		return list;
		
	}




	/**
	 * ��ʾ����ͼƬ
	 * @param request
	 * @param response
	 * @param processInstanceId
	 */
	@RequestMapping("pic_")
	public void pic_(HttpServletRequest request, HttpServletResponse response,String processInstanceId) {
		//��ȡ�����̵�ͼƬ��
		InputStream inputStream = this.findProcessInstancePic(processInstanceId);
		PrintWriter pw = null;
		if (inputStream == null) {
			try {
				pw = response.getWriter();
				pw.write("error");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				pw.close();
			}
		} else {
			byte[] b = new byte[1024];
			int len = -1;
			ServletOutputStream sos = null;
			try {
				sos = response.getOutputStream();
				while ((len = inputStream.read(b, 0, 1024)) != -1) {
					sos.write(b, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (sos != null) {
					try {
						sos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

	}
	
	public InputStream findProcessInstancePic(String processInstanceId) {
		//��ȡ����ʵ��
		ProcessInstance processInstance = executionService.findProcessInstanceById(processInstanceId);
		//��ȡ���̶���id
		String processDefinitionId = processInstance.getProcessDefinitionId();
		//��ȡ��·�̶���
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).uniqueResult();
		//��ȡ���̶����е�ͼƬ��
		return repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),
				processDefinition.getImageResourceName());
	}

	@RequestMapping("viewForAdmin_")
	public String viewForAdmin_(HttpServletRequest request, String id, String fromPage) {
		//System.out.println("view----id:"+id);
		//System.out.println("view----taskId:"+taskId);
		logger.info("["+this.getClass().getName()+"][viewForAdmin_][start]");
 		

	
		request.setAttribute("id", id);
		
		request.setAttribute("prepagejsp", fromPage);
		logger.info("["+this.getClass().getName()+"][viewForAdmin_][goto][picForAdmin.jsp]");
		logger.info("["+this.getClass().getName()+"][viewForAdmin_][end]");
		
		return "picForAdmin";
	}
	/**
	 * ��ʾ��������

	 */
	@RequestMapping("view_")
	public String view_(HttpServletRequest request, String id, String taskid,String fromPage
			,String listRectiAc) {
		//System.out.println("view----id:"+id);
		//System.out.println("view----taskId:"+taskId);
		logger.info("["+this.getClass().getName()+"][view_][start]");
 		List<ActivityCoordinates> ac = null;
		//û����ɵ�����
		if (taskid != null) {
			// ����ͼ��ǰ��ڵ�
			logger.info("["+this.getClass().getName()+"][view_][taskId] is not null");
			ActivityCoordinates activityCoordinates = this.findActivityCoordinates(id);

			//��ȡ����ʷ�ڵ�
			if(activityCoordinates != null){
				 ac = this.findHistoryActivityInfo(id);
				//ȥ����ǰ�ڵ�
				ac.remove(activityCoordinates);

			}
			request.setAttribute("activityCoordinates", activityCoordinates);
		//�Ѿ���ɵ�����
		} else {
			logger.info("["+this.getClass().getName()+"][view_][taskId] is  null");
			ac = this.findHistoryActivityInfo(id);
			//ȥ����ǰ�ڵ�
			//ActivityCoordinates activityCoordinates = this.findActivityCoordinates(id);
			//ac.remove(activityCoordinates);
		}
	
		request.setAttribute("ac", ac);
		request.setAttribute("id", id);
		
		request.setAttribute("listRectiAc", listRectiAc);
		logger.info("["+this.getClass().getName()+"][get_][listRectiAc]:"+listRectiAc);
		
		request.setAttribute("prepagejsp", fromPage);
		logger.info("["+this.getClass().getName()+"][view_][goto][pic.jsp]");
		logger.info("["+this.getClass().getName()+"][view_][end]");
		
		return "pic";
	}
	//��ȡ����ǰ�ڵ�
	public ActivityCoordinates findActivityCoordinates(String id) {

		ProcessInstance processInstance = executionService.findProcessInstanceById(id);
		if (null==processInstance||processInstance.isSuspended()) {
			return null;
		}
		Set<String> activityNames = processInstance.findActiveActivityNames();//���ķ���

		return repositoryService.getActivityCoordinates(processInstance
				.getProcessDefinitionId(), activityNames.iterator().next());

	}
	//��ȡ����ʷ�ڵ�
	public List<ActivityCoordinates> findHistoryActivityInfo(String processId) {
		List<ActivityCoordinates> activityCoordinates = new ArrayList<ActivityCoordinates>();
		//���ķ���
		List<HistoryActivityInstance> hisIns = historyService.createHistoryActivityInstanceQuery().processInstanceId(processId).list();
		
		ProcessInstance processInstance = executionService.findProcessInstanceById(processId);
		if (null==processInstance||processInstance.isSuspended()) {
			return null;
		}
		for(Iterator<HistoryActivityInstance> iter = hisIns.iterator();iter.hasNext() ; ){
			activityCoordinates.add(repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(), iter.next().getActivityName()));
		}
		return activityCoordinates;
	}

}
