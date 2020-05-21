package com.djzhgd.framework.web.domain;

import com.djzhgd.common.constant.HttpStatus;

import java.io.Serializable;
/**
 * ZhgdPartyTheory控制器<br>
 *
 * @author wwk
 * @version 1.0, 2019-01-16
 * @see
 * @since 1.0
 */
public class ResultBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = HttpStatus.SUCCESS;

	public static final int FAIL = HttpStatus.ERROR;

	public static final String SUCCESSMSG = "处理成功";

	public static final String  FAILMSG = "处理失败";

	private int code = SUCCESS;

	private String msg = "处理成功";

	private T data;

	public ResultBean() {
		super();
	}

	public ResultBean(T data) {
		super();
		this.data = data;
	}

	public ResultBean(Throwable e) {
		super();
		this.msg = FAILMSG;
		this.code = FAIL;
	}

	public ResultBean success(T data) {
		ResultBean resultBean=new ResultBean();
		resultBean.setCode(SUCCESS);
		resultBean.setMsg(SUCCESSMSG);
		resultBean.setData(data);
		return resultBean;
	}

	public ResultBean fail(Throwable e) {
		ResultBean resultBean=new ResultBean();
		resultBean.setCode(FAIL);
		resultBean.setMsg(FAILMSG);
		resultBean.setData(data);
		return resultBean;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
