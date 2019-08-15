package com.hcjava.uil;

import java.io.Serializable;

/**
 * 用来封装后台处理的结果集
 * @author lenovo
 *
 */
public class NoteResult implements Serializable{
	private Integer status;//0表示成功,其他表示失败
	private String msg;//消息
	private Object data;//封装返回的数据
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
