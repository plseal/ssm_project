// 
// 
//  
// 

package com.lin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lin.entity.RectiNewEntity;
import com.lin.entity.StandardDatabaseEntity;
import com.lin.entity.UserEntity;
import com.lin.service.RectiNewService;
import com.lin.service.StandardDatabaseService;
import com.lin.service.UserService;
@Controller
@RequestMapping("standardDatabase")
public class StandardDatabaseController
{

	@Resource(name="repositoryService")
	private RepositoryService repositoryService;//-流程管理，部署发布
	
	@Resource(name="taskService")
	private TaskService taskService;//任务管理
	
	@Resource(name="userService")
	private UserService userService;//任务管理
	
	@Resource(name="executionService")
	private ExecutionService executionService;//-流程实例管理
	
	@Resource(name="standardDatabaseService")
	private StandardDatabaseService standardDatabaseService;//业务逻辑
	
	@Resource(name="historyService")
	private HistoryService historyService;//业务逻辑

	
	@RequestMapping("listStandardDatabase")
	public @ResponseBody List<StandardDatabaseEntity> listStandardDatabase(HttpServletRequest request){
		List<StandardDatabaseEntity>  list = standardDatabaseService.getAll();
		return list;
	}
	
	

}
