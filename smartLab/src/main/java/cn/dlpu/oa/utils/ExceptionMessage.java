package cn.dlpu.oa.utils;

public class ExceptionMessage {
	
	/** 出现异常的类名 */
	private String className;
	/** 异常信息 */
	private String error;
	/** 异常提示信息 */
	private String message;

	/* set and get method */
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
