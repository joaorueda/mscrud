package com.inpacktu.crud.exception;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 2657863375305751777L;

	private Date timestamp;
	private String message;
	private String detail;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public ExceptionResponse() {
		super();
	}
	public ExceptionResponse(Date timestamp, String message, String detail) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.detail = detail;
	}
	@Override
	public int hashCode() {
		return Objects.hash(detail, message, timestamp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionResponse other = (ExceptionResponse) obj;
		return Objects.equals(detail, other.detail) && Objects.equals(message, other.message)
				&& Objects.equals(timestamp, other.timestamp);
	}
	
	
}
