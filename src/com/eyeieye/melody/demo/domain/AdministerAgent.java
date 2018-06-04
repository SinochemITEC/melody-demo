package com.eyeieye.melody.demo.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.eyeieye.melody.demo.enums.FunctionsEnum;

/**
 * 后台用户的cookie持久化对象
 * 
 * @author fish
 * 
 */
public class AdministerAgent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9163698778206514702L;

	public static final String AdministerTag = "administerAgent";

	private String loginId;

	private BigInteger functions;// 以2进制位纪录管理员用户的权限

	public AdministerAgent() {
		super();
	}

	public AdministerAgent(Administer admin) {
		super();
		this.loginId = admin.getLoginId();
	}

	/**
	 * 在指定的2进制位上是否有权限
	 * 
	 * @param index
	 * @return
	 */
	public boolean haveFunction(int index) {
		return this.functions.testBit(index);
	}

	/**
	 * 是否拥有权限
	 * 
	 * @param fe
	 * @return
	 */
	public boolean haveFunction(FunctionsEnum fe) {
		return haveFunction(fe.ordinal());
	}

	/**
	 * 设置用户的权限,实际应用可以使用自增的权限表id等唯一并且连续的正整数替换这里的enum.ordinal
	 * 
	 * @param funs
	 */
	public void setFunctions(List<FunctionsEnum> funs) {
		this.functions = new BigInteger("0");
		for (FunctionsEnum en : funs) {
			this.functions = this.functions.setBit(en.ordinal());
		}
	}

	public void setFunctions(int pos) {
		if (this.functions == null) {
			this.functions = new BigInteger("0");
		}
		this.functions = this.functions.setBit(pos);
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public BigInteger getFunctions() {
		return functions;
	}

	public void setFunctions(BigInteger functions) {
		this.functions = functions;
	}
}
