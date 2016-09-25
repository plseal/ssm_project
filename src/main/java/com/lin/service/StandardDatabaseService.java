
package com.lin.service;

import com.lin.dao.StandardDatabaseDao;
import com.lin.dao.UserDao;
import com.lin.entity.StandardDatabaseEntity;
import com.lin.entity.UserEntity;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
@Service
public class StandardDatabaseService
{
	@Resource(name="standardDatabaseDao")
	private StandardDatabaseDao standardDatabaseDao;



	//public String insert(UserEntity entity){
	//	return userDao.insert(entity);
	//}

	//public String update(UserEntity entity){
	//	return userDao.update(entity);
	//}

	//public String delete(UserEntity entity){
	//	return userDao.delete(entity);
	//}



	public List<StandardDatabaseEntity> getAll()
	{
		return standardDatabaseDao.getAll();
	}
}
