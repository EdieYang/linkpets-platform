package com.linkpets.result;


import com.linkpets.enums.ResultCode;

public class PlatformResult implements Result {

/*	public PlatformResult(Integer code,String msg,Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}*/

	private static final long serialVersionUID = 874200365941306385L;

	private Boolean success;

	private Integer code;

	private String msg;

	private Object data;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
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

	public static PlatformResult success() {
		PlatformResult result = new PlatformResult();
		result.setSuccess(true);
		result.setResultCode(ResultCode.SUCCESS);
		return result;
	}

	public static PlatformResult success(Object data) {
		PlatformResult result = new PlatformResult();
		result.setSuccess(true);
		result.setResultCode(ResultCode.SUCCESS);
		result.setData(data);
		return result;
	}

	public static PlatformResult failure(ResultCode resultCode) {
		PlatformResult result = new PlatformResult();
		result.setSuccess(false);
		result.setResultCode(resultCode);
		return result;
	}

	public static PlatformResult failure(ResultCode resultCode, Object data) {
		PlatformResult result = new PlatformResult();
		result.setSuccess(false);
		result.setResultCode(resultCode);
		result.setData(data);
		return result;
	}

	public static PlatformResult failure(String message) {
		PlatformResult result = new PlatformResult();
		result.setSuccess(false);
		result.setCode(ResultCode.PARAM_IS_INVALID.code());
		result.setMsg(message);
		return result;
	}

	private void setResultCode(ResultCode code) {
		this.code = code.code();
		this.msg = code.message();
	}
}
