
package com.lin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.lin.entity.RectiNewEntity;
import com.lin.service.RectiNewService;
@Controller
@RequestMapping("uploadFile")
public class uploadFileController
{
	private static Logger logger = Logger.getLogger(uploadFileController.class);
	@Resource(name="rectiNewService")
	private RectiNewService rectiNewService;//业务逻辑
	
	public uploadFileController()
	{
	}
	@RequestMapping("saveuploadFile")
	public String saveuploadFile(MultipartFile fileObj, HttpServletRequest request) throws Exception{
		logger.info("["+this.getClass().getName()+"][saveuploadFile][start]");
		if (fileObj != null){
			if (fileObj.getSize() == 0) {
				return "addFileFailed";
			}
			logger.info("["+this.getClass().getName()+"][saveuploadFile][SaveFile---strFile.Name]:"+fileObj.getName());
			logger.info("["+this.getClass().getName()+"][saveuploadFile][SaveFile---strFile.Size]:"+fileObj.getSize());
			
			String strTruePath = (new StringBuilder(String.valueOf(request.getRealPath("/")))).append("upload/").append(request.getSession().getAttribute("javaid")).append("/").toString();
			logger.info("["+this.getClass().getName()+"][saveuploadFile][SaveFile---strTruePath]:"+strTruePath);
			System.out.println((new StringBuilder("SaveFile---:")).append(strTruePath).toString());
			String filename = fileObj.getOriginalFilename();
			SaveFileFromInputStream(fileObj.getInputStream(), strTruePath, filename);
		}
		logger.info("["+this.getClass().getName()+"][saveuploadFile][goto][addFileSuccess.jsp]");
		logger.info("["+this.getClass().getName()+"][saveuploadFile][end]");
		return "addFileSuccess";
	}
	@RequestMapping("deleteUploadFile")
	public String deleteUploadFile(String strFile, HttpServletRequest request) throws Exception {
		logger.info("["+this.getClass().getName()+"][deleteUploadFile][start]");
		
		logger.info("["+this.getClass().getName()+"][deleteUploadFile][Delete---strFile]:"+strFile);
		String strTruePath = (new StringBuilder(String.valueOf(request.getRealPath("/")))).append("upload/").append(request.getSession().getAttribute("javaid")).append("/").toString();
		logger.info("["+this.getClass().getName()+"][deleteUploadFile][Delete---strTruePath]:"+strTruePath+strFile);
		
		File file = new File((new StringBuilder(String.valueOf(strTruePath))).append(strFile).toString());
		if (file.isFile() && file.exists()) {
			file.delete();
		}
		RectiNewEntity entity = new RectiNewEntity();
		
		//表单内容设置到entity
		entity = rectiNewService.setEntityFromRequest(request, entity);
		
		logger.info("["+this.getClass().getName()+"][deleteUploadFile][javaid]:"+entity.getJavaid());

		request.setAttribute("entity",entity);
		
		logger.info("["+this.getClass().getName()+"][deleteUploadFile][goto][addFileList.jsp]");
		logger.info("["+this.getClass().getName()+"][deleteUploadFile][end]");
		return "addFileList";
	}
	
	public void SaveFileFromInputStream(InputStream stream, String path, String savefile) throws IOException{
		logger.info("["+this.getClass().getName()+"][SaveFileFromInputStream][start]");
		FileOutputStream fs = new FileOutputStream((new StringBuilder(String.valueOf(path))).append(savefile).toString());
		
		logger.info("["+this.getClass().getName()+"][SaveFileFromInputStream][path][savefile]"+path+savefile);
		byte buffer[] = new byte[0x100000];
		
		for (int byteread = 0; (byteread = stream.read(buffer)) != -1;){
			fs.write(buffer, 0, byteread);
			fs.flush();
		}

		fs.close();
		stream.close();
		logger.info("["+this.getClass().getName()+"][SaveFileFromInputStream][end]");
	}
	@RequestMapping("addFileList")
	public String addFileList(HttpServletRequest request) throws Exception{
		logger.info("["+this.getClass().getName()+"][addFileList][start]");
		
		
		RectiNewEntity entity = new RectiNewEntity();
		
		//表单内容设置到entity
		entity = rectiNewService.setEntityFromRequest(request, entity);
		
		logger.info("["+this.getClass().getName()+"][addFileList][javaid]:"+entity.getJavaid());

		request.setAttribute("entity",entity);
		
		logger.info("["+this.getClass().getName()+"][addFileList][goto][addFileList.jsp]");
		logger.info("["+this.getClass().getName()+"][addFileList][end]");
		return "addFileList";
	}
	@RequestMapping("addFileListReadOnly")
	public String addFileListReadOnly(HttpServletRequest request,String javaid) throws Exception{
		logger.info("["+this.getClass().getName()+"][addFileListReadOnly][start]");

		logger.info("["+this.getClass().getName()+"][addFileListReadOnly][javaid]:"+javaid);

		request.setAttribute("javaid",javaid);
		
		logger.info("["+this.getClass().getName()+"][addFileListReadOnly][goto][addFileListReadOnly.jsp]");
		logger.info("["+this.getClass().getName()+"][addFileListReadOnly][end]");
		return "addFileListReadOnly";
	}
	
}
