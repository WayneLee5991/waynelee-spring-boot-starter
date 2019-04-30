/**
 * 
 */
package work.waynelee.exception;

import work.waynelee.exception.enums.ExceptionEnumExample;

/**
 * 异常信息枚举需要实现此接口，枚举示例详见：{@link ExceptionEnumExample}
 * @author lwq
 * 2019年3月13日 下午5:10:54
 */
public interface ExceptionEnum {
	
	void setCode(Integer code);
	
	void setMsg(String msg);
	
	Integer getCode();
	
	String getMsg();
}
