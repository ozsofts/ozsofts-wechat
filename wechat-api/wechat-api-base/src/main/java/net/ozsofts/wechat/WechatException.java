package net.ozsofts.wechat;

public class WechatException extends Exception {
	private static final long serialVersionUID = 5984894397110939208L;

	private String errorCode;

	public WechatException(String errorCode) {
		super(Error.getErrorMessage(errorCode));

		this.errorCode = errorCode;
	}

	public WechatException(String message, Throwable t) {
		super(message, t);

		this.errorCode = "-99";
	}

	public String getErrorCode() {
		return errorCode;
	}
}
