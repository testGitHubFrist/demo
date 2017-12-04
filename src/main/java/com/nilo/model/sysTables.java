package com.nilo.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/18.
 */
public class sysTables implements Serializable{

    private String tableName;
    private String tableComment;
    
    
	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
