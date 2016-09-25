
package com.lin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lin.dao.LocationDao;
import com.lin.entity.LocationEntity;
@Service
public class LocationService
{
	@Resource(name="locationDao")
	private LocationDao locationDao;

	public LocationService()
	{
	}

	
	public List<LocationEntity> getAll()
	{
		return locationDao.getAll();
	}

	public List<LocationEntity> getYinhuanType()
	{
		return locationDao.getYinhuanType();
	}
	
	public List<LocationEntity> getBeCheckedBu()
	{
		return locationDao.getBeCheckedBu();
	}
	
}
