
package com.lin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.entity.NewsEntity;
import com.lin.service.NewsService;
import com.lin.service.UserService;
@Controller
@RequestMapping("news")
public class NewsController
{
	private static Logger logger = Logger.getLogger(NewsController.class);
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;//-流程管理，部署发布
	
	@Resource(name="taskService")
	private TaskService taskService;//任务管理
	
	@Resource(name="userService")
	private UserService userService;//任务管理
	
	@Resource(name="executionService")
	private ExecutionService executionService;//-流程实例管理
	
	@Resource(name="newsService")
	private NewsService newsService;//业务逻辑
	
	@Resource(name="historyService")
	private HistoryService historyService;//业务逻辑

	
	@RequestMapping("listNews")
	public @ResponseBody List<NewsEntity> listNews(HttpServletRequest request){
		List<NewsEntity>  list = newsService.getAll();
		for (int i = 0 ; i < list.size()-1;i++) {
			logger.info("["+this.getClass().getName()+"][listNews][id]"+list.get(i).getId());
			logger.info("["+this.getClass().getName()+"][listNews][title]"+list.get(i).getId());
			logger.info("["+this.getClass().getName()+"][listNews][xinxilaiyuan]"+list.get(i).getXinxilaiyuan());
		}
		return list;
	}
	
	

}
