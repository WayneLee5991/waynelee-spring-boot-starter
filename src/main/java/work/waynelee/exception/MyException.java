/**
 * 
 */
package work.waynelee.exception;

/**
 * 自定义异常
 * @author lwq
 * 2019年1月15日 下午12:59:28
 */
public class MyException extends RuntimeException {

	private static final long serialVersionUID = 5579492723835964916L;
	private Integer code;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public MyException( Integer code,String message) {
		super(message);
		this.code = code;
	}

	public MyException( Integer code,String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}
	
	/**2019年4月28日 下午6:21:38 添加*/
	public MyException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMsg());
		Integer code = exceptionEnum.getCode();
		this.code = code;
	}
	
	/**2019年4月28日 下午6:21:38 添加*/
	public MyException(ExceptionEnum exceptionEnum,Throwable cause) {
		super(exceptionEnum.getMsg(),cause);
		Integer code = exceptionEnum.getCode();
		this.code = code;
	}
	
}
