
package com.lin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lin.dao.DanweiDao;
import com.lin.entity.DanweiEntity;
@Service
public class DanweiService
{
	@Resource(name="danweiDao")
	private DanweiDao danweiDao;

	public DanweiService()
	{
	}

	
	public List<DanweiEntity> getAll()
	{
		return danweiDao.getAll();
	}
	public DanweiEntity getShortName(String rid)
	{
		return danweiDao.getShortName(rid);
	}
	public DanweiEntity getFullName(String rid)
	{
		return danweiDao.getFullName(rid);
	}
	public DanweiEntity getFullNameByOrderId(String rid)
	{
		return danweiDao.getFullNameByOrderId(rid);
	}
	
	
}
