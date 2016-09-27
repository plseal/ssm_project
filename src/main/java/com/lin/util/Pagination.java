package com.lin.util;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class Pagination {  
      
    private int totalPages; // 总页数
    private int totalRecordCnt; // 总条数  

	private int page;   // 当前页码  
    private List resultList;    // 结果集存放List  
  
    private static Logger logger = Logger.getLogger(Pagination.class);
    
    public Pagination(String sql, int currentPage, int numPerPage,NamedParameterJdbcTemplate namedParameterJdbcTemplate) {  
        if (namedParameterJdbcTemplate == null) {  
            throw new IllegalArgumentException(  
                    "com.starhub.sms.util.Pagination.jTemplate is null,please initial it first. ");  
        } else if (sql == null || sql.equals("")) {  
            throw new IllegalArgumentException(  
                    "com.starhub.sms.util.Pagination.sql is empty,please initial it first. ");  
        }  
          
        String countSQL = getSQLCount(sql);  
        setPage(currentPage); 
        this.totalRecordCnt = namedParameterJdbcTemplate.getJdbcOperations().queryForInt(countSQL);
        logger.info("["+this.getClass().getName()+"][setTotalPages][totalRecordCnt]"+this.totalRecordCnt);
        setTotalPages(numPerPage,totalRecordCnt);  
        int startIndex = (currentPage - 1) * numPerPage;    //数据读取起始index  
          
        StringBuffer paginationSQL = new StringBuffer(" ");  
        paginationSQL.append(sql);  
        paginationSQL.append(" limit "+ startIndex+","+numPerPage);  
        setResultList(namedParameterJdbcTemplate.getJdbcOperations().queryForList(paginationSQL.toString()));  
    }  
      
    public String getSQLCount(String sql){  
        
        
        String sqlCount = "select count(*) from (" + sql + ") t_X ";  
        logger.info("["+this.getClass().getName()+"][getSQLCount][sql]"+sqlCount);
        return sqlCount;  
    }  
    public int getTotalRecordCnt() {
		return totalRecordCnt;
	}

	public void setTotalRecordCnt(int totalRecordCnt) {
		this.totalRecordCnt = totalRecordCnt;
	}
    public int getTotalPages() {  
        return totalPages;  
    }  
    public void setTotalPages(int totalPages) {  
        this.totalPages = totalPages;  
    }  
    public int getPage() {  
        return page;  
    }  
    public void setPage(int page) {  
        this.page = page;  
    }  
    public List getResultList() {  
        return resultList;  
    }  
    public void setResultList(List resultList) {  
        this.resultList = resultList;  
    }  
    // 计算总页数  
    public void setTotalPages(int numPerPage,int totalRows) {  
        if (totalRows % numPerPage == 0) {  
            this.totalPages = totalRows / numPerPage;  
            
        } else {  
            this.totalPages = (totalRows / numPerPage) + 1;  
        }
        
        logger.info("["+this.getClass().getName()+"][setTotalPages][totalPages]"+this.totalPages);
    }  
  
}  

