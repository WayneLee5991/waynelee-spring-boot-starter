/**
 * 
 */
package work.waynelee.exception.enums;

import work.waynelee.exception.ExceptionEnum;

/**
 * 异常枚举信息示例
 * @author lwq
 * 2019年3月13日 下午5:14:06
 */
public enum ExceptionEnumExample implements  ExceptionEnum {
	
	/**
	 * code : 101 <br>
	 * msg  : 成功
	 */
	SUCCESS(101,"成功"),
	;

	private Integer code;
	
	private String msg;

	private ExceptionEnumExample(Integer code, String msg) {
		this.setCode(code);
		this.msg = msg;
	}

	@Override
	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}


	@Override
	public Integer getCode() {
		return this.code;
	}


	@Override
	public String getMsg() {
		return this.msg;
	}
	

}
