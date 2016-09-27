package com.lin.controller;

import com.lin.service.DanweiService;
import com.lin.service.MyJbpmService;
import com.lin.service.RectiSearchService;
import com.lin.service.UserService;
import com.lin.util.ExcelToHtml;
import org.apache.log4j.Logger;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("zuzhituCTL")
public class ZuzhituController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ZuzhituController.class);
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;//-流程管理，部署发布
	
	@Resource(name="taskService")
	private TaskService taskService;//任务管理
	
	@Resource(name="userService")
	private UserService userService;//任务管理
	
	
	@Resource(name="danweiService")
	private DanweiService danweiService;
	
	@Resource(name="rectiSearchService")
	private RectiSearchService rectiSearchService;
	
	@Resource(name="myJbpmService")
	private MyJbpmService myJbpmService;//任务管理
	
	@Resource(name="executionService")
	private ExecutionService executionService;//-流程实例管理
	
	@Resource(name="historyService")
	private HistoryService historyService;//业务逻辑
	
	public ZuzhituController()
	{
	}

	
	
	//获取Msg
	@RequestMapping("to_Zuzhitu")
	public String to_Zuzhitu(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][getZuzhitu][start]");
		String fromDanwei = (String)request.getParameter("from");
		logger.info("["+this.getClass().getName()+"][getZuzhitu][fromDanwei]"+fromDanwei);
		
	    String strModelFileOfRealServerPath = 
	    		request.getRealPath("/") + "modelfile\\01rencheng.xlsx";
    
	    logger.info("["+this.getClass().getName()+"][getZuzhitu][strModelFileOfRealServerPath]"+strModelFileOfRealServerPath);
	    String strOut="";		
	    strOut = ExcelToHtml.readExcelToHtml(strModelFileOfRealServerPath, true);


		
		
		request.setAttribute("strOut",strOut);
		logger.info("["+this.getClass().getName()+"][getZuzhitu][end]");
		return "zuzhituS";
	}
	
	
	

}
