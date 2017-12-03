package com.haiyoung.hyweb.util;

public class Message<T> {

	public static enum MessageType {
		success, no_authority, error, silent_error, checked_error
	}

	private MessageType type;

	private T message;

	private String errorMsg;

	public static Message<String> SuccessMessage = new Message<String>(
			MessageType.success);

	public static Message<String> NoAuthorityMessage = new Message<String>(
			MessageType.no_authority);

	public static Message<String> SilentErrorMessage = new Message<String>(
			MessageType.silent_error);
	
	public static Message<String> CheckedErrorMessage = new Message<String>(
			MessageType.checked_error);
	

	public Message() {
		type = MessageType.success;
	}

	public Message(MessageType type) {
		this.type = type;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public static <T> Message<T> createErrorMessage(String errorMsg) {
		Message<T> message = new Message<T>(MessageType.error);
		message.setErrorMsg(errorMsg);
		return message;
	}

	public static <T> Message<T> createSuccessMessage(T msg) {
		Message<T> message = new Message<T>(MessageType.success);
		message.setMessage(msg);
		return message;
	}
	
	public static <T> Message<T> createCheckedErrorMessage(T msg) {
		Message<T> message = new Message<T>(MessageType.checked_error);
		message.setMessage(msg);
		return message;
	}

}