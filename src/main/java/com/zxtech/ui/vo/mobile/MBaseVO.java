package com.zxtech.ui.vo.mobile;

import java.io.Serializable;

public final class MBaseVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final int STATE_FAIL = 0;
	public static final int STATE_SUCC = 1;

	private int status;
	private String reason;
	private String msg;
	private Object data;
	
	public MBaseVO(){
		status = STATE_FAIL;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
