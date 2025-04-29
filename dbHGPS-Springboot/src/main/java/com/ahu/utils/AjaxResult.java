package com.ahu.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一Ajax返回结果
 */
@Data
public class AjaxResult implements Serializable {

	private int code;
	private String msg;
	private Object data;

	public static AjaxResult succ(Object data) {
		return succ(200, "操作成功", data);
	}

	public static AjaxResult succ(int code, String msg, Object data) {
		AjaxResult r = new AjaxResult();
		r.setCode(code);
		r.setMsg(msg);
		r.setData(data);
		return r;
	}

	public static AjaxResult fail(String msg) {
		return fail(400, msg, null);
	}

	public static AjaxResult fail(int code, String msg, Object data) {
		AjaxResult r = new AjaxResult();
		r.setCode(code);
		r.setMsg(msg);
		r.setData(data);
		return r;
	}

}
