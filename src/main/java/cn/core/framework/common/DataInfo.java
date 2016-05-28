package cn.core.framework.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class DataInfo {

	/**
	 * 数据项
	 */
	private Object data;
	
	/**
	 * 返回信息
	 */
	private String msg;
	
	/**
	 * 返回结果（0-失败，1-成功）
	 */
	private int status;
	
	@XmlElement
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@XmlElement
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@XmlElement
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
