package com.athena.plano_de_aula.api.exceptionhandler;

import java.util.Date;

public class MessageExceptionHandler {
	private Date timestamp;
	private Integer status;
	private String mesage;
	
	public MessageExceptionHandler(Date timestamp, Integer status, String mesage) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.mesage = mesage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
	
	
}
