package com.lin.util;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.lin.service.DanweiService;

public class common { 
	@Resource(name="danweiService")
	private static DanweiService danweiService;
	
	private static Logger logger = Logger.getLogger(common.class);
	
	public static boolean isEmpty(String value) {

	    if ( value == null || value.length() == 0 )
	        return true;
	    else if ("null".equals(value)) {
	    	return true;
	    } else {
	        return false;
	    }
	}
	public static String getSQL(String sql,MapSqlParameterSource namedParameters){
		//logger.info("["+this.getClass().getName()+"][getSQL][start]"+sql);
		String strResultSQL = sql;
		Map  map = namedParameters.getValues();
		Iterator entries = map.entrySet().iterator();  
		while (entries.hasNext()) {  
		    Map.Entry entry = (Map.Entry) entries.next();  
		    String key = (String)entry.getKey();  
		    String value = (String)entry.getValue();  
		    //System.out.println("Key = " + key + ", Value = " + value);
		    //logger.info("["+this.getClass().getName()+"][getSQL][map]  "+"Key = " + key + ", Value = " + value);
		    strResultSQL = strResultSQL.replace(":"+key,"'"+value+"'" );
		}  
		//logger.info("["+this.getClass().getName()+"][getSQL][end]  "+strResultSQL);
		return strResultSQL;
	}
	public static String toBlank(String value) {

	    if ( isEmpty(value)  )
	        return "";
	    else
	        return value;
	}
	

	        
	
	public static void clear_session(HttpServletRequest request) {

		request.getSession().setAttribute("s_danwei",     "");
		request.getSession().setAttribute("s_location",   "");
		request.getSession().setAttribute("s_rectitype",  "");
		request.getSession().setAttribute("s_rectilevel", "");
		request.getSession().setAttribute("s_checkdate",  "");
		request.getSession().setAttribute("s_searchForAllFlg",  "");
	    
	}	
	
	public static Date getLastMonthDate(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH, -1);
	        return cal.getTime();
	}
    public static String encodeStr(String str) {  
        try {  
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
}

