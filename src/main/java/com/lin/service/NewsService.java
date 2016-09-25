
package com.lin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lin.dao.NewsDao;
import com.lin.entity.NewsEntity;
@Service
public class NewsService
{
	@Resource(name="newsDao")
	private NewsDao newsDao;



	//public String insert(UserEntity entity){
	//	return userDao.insert(entity);
	//}

	//public String update(UserEntity entity){
	//	return userDao.update(entity);
	//}

	//public String delete(UserEntity entity){
	//	return userDao.delete(entity);
	//}



	public List<NewsEntity> getAll()
	{
		return newsDao.getAll();
	}
}
