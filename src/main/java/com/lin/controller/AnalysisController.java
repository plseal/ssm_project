package com.lin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lin.entity.AnalysisEntity;
import com.lin.entity.RectiSearchEntity;
import com.lin.service.AnalysisService;
import com.lin.util.common;
@Controller
@RequestMapping("analysis")
public class AnalysisController
{
	private static Logger logger = Logger.getLogger(AnalysisController.class);

	
	@Resource(name="analysisService")
	private AnalysisService analysisService;
	

	@RequestMapping("getdata")
	public String pieChart(HttpServletRequest request){
		logger.info("["+this.getClass().getName()+"][getdata][start]");
		AnalysisEntity entity = new AnalysisEntity();
		entity = analysisService.setEntityFromRequest(request, entity);
		
		List<AnalysisEntity> list_rectiNewCheck = analysisService.getAllAnalysis_rectiNew(entity);
		if ("�ϼ�����鰲ȫ���������Ϣ".equals(entity.getAnalysisfrom())) {
			list_rectiNewCheck = analysisService.getAllAnalysis_rectiCheck(entity);
		}
		
		String str_ana_data = "";
		String str_ana_labels = "";
		String str_douhao = ",";
		String str_rectiLevel = ",";
		//��֯���ַ�����ҳ�洫��
		for (int i=0;i<list_rectiNewCheck.size();i++) {
			AnalysisEntity entitydao =(AnalysisEntity)list_rectiNewCheck.get(i);
			if (i == list_rectiNewCheck.size()-1){
				str_douhao = "";
			}
			if ("����λ�����ţ�ͳ��".equals(entity.getAnalysistype())) {
				str_ana_data = str_ana_data + entitydao.getDanwei_cnt() + str_douhao;
				str_ana_labels = str_ana_labels + entitydao.getDanwei() + entitydao.getDanwei_cnt() + "��" + str_douhao;
			}else if ("���ص�ͳ��".equals(entity.getAnalysistype())) {
				str_ana_data = str_ana_data + entitydao.getLocation_cnt_sum() + str_douhao;
				str_ana_labels = str_ana_labels + entitydao.getLocation() + entitydao.getLocation_cnt_sum() + "��" + str_douhao;
			} else if ("����������ͳ��".equals(entity.getAnalysistype())) {
				str_ana_data = str_ana_data + entitydao.getRectitype_cnt() + str_douhao;
				str_ana_labels = str_ana_labels + entitydao.getRectitype() + entitydao.getRectitype_cnt() + "��" + str_douhao;
			} else if ("�������ȼ�ͳ��".equals(entity.getAnalysistype())) {
				str_ana_data = str_ana_data + entitydao.getRectilevel_cnt() + str_douhao;
				if (common.isEmpty(entitydao.getRectilevel())) {
					str_rectiLevel = "δȷ�������ȼ�";
				} else {
					str_rectiLevel = entitydao.getRectilevel();
				}
				str_ana_labels = str_ana_labels + str_rectiLevel + entitydao.getRectilevel_cnt() + "��" + str_douhao;
			} else {
				//Ĭ�ϰ���λ�����ţ�ͳ��
				str_ana_data = str_ana_data + entitydao.getDanwei_cnt() + str_douhao;
				str_ana_labels = str_ana_labels + entitydao.getDanwei() + entitydao.getDanwei_cnt() + "��" + str_douhao;
			}
		}
		logger.info("["+this.getClass().getName()+"][str_ana_data]"+str_ana_data);
		logger.info("["+this.getClass().getName()+"][str_ana_labels]"+str_ana_labels);
		/*
		
		List<AnalysisEntity> list_rectiCheck = analysisService.getAllAnalysis_rectiCheck();
		Integer rectitype_option_car_cnt = 0;
		Integer rectitype_option_special_equipment_cnt = 0;
		Integer rectitype_option_mark_cnt = 0;
		Integer rectitype_option_electric_cnt = 0;
		Integer rectitype_option_fire_control_cnt = 0;
	
		for (int i=0;i<list_rectiCheck.size();i++) {
			AnalysisEntity entity =(AnalysisEntity)list_rectiCheck.get(i);
			if ("��������".equals(entity.getRectitype())){
				rectitype_option_car_cnt = rectitype_option_car_cnt + Integer.parseInt(entity.getRectitype_cnt());
			}
			if ("�����豸".equals(entity.getRectitype())){
				rectitype_option_special_equipment_cnt = rectitype_option_special_equipment_cnt + Integer.parseInt(entity.getRectitype_cnt());
			}
			if ("��ʶ����".equals(entity.getRectitype())){
				rectitype_option_mark_cnt = rectitype_option_mark_cnt + Integer.parseInt(entity.getRectitype_cnt());
			}
			if ("��������".equals(entity.getRectitype())){
				rectitype_option_electric_cnt = rectitype_option_electric_cnt + Integer.parseInt(entity.getRectitype_cnt());
			}
			if ("��������".equals(entity.getRectitype())){
				rectitype_option_fire_control_cnt = rectitype_option_fire_control_cnt + Integer.parseInt(entity.getRectitype_cnt());
			}
		}
		*/
		
		request.setAttribute("ana_data", str_ana_data);
		request.setAttribute("ana_labels", str_ana_labels);
		
		request = analysisService.setRequestFromEntity(request, entity);
		if ("����ͼ".equals(entity.getAnalysischart())) {
			logger.info("["+this.getClass().getName()+"][getdata][goto][analysisBarChart.jsp]");
			logger.info("["+this.getClass().getName()+"][getdata][end]");
			return "analysisBarChart";
		} else {
			logger.info("["+this.getClass().getName()+"][getdata][goto][analysisPieChart.jsp]");
			logger.info("["+this.getClass().getName()+"][getdata][end]");
			return "analysisPieChart";
		}

	}
	
	

}
