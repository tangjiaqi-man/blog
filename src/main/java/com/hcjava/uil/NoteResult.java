package com.hcjava.uil;

import java.io.Serializable;

/**
 * ������װ��̨����Ľ����
 * @author lenovo
 *
 */
public class NoteResult implements Serializable{
	private Integer status;//0��ʾ�ɹ�,������ʾʧ��
	private String msg;//��Ϣ
	private Object data;//��װ���ص�����
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
