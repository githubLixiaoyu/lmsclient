package com.zxtech.support.controller;

import com.zxtech.support.exception.util.WebErrUtils;

public class Result {

	private String flag;
	private String  msg;
	private Object data;
	
	private long recordsTotal;//总条数
	private long recordsFiltered;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = WebErrUtils.getI18nSuccessMessage(msg,msg);
	}
	
	public void setMsg(String msg,Object[] args) {
		this.msg = WebErrUtils.getI18nSuccessMessage(msg,args,msg);
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
}
