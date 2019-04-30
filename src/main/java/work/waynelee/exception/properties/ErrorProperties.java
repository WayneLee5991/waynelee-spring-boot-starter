/**
 * 
 */
package work.waynelee.exception.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 全局异常处理配置
 * @author lwq
 * 2019年4月28日 下午6:37:29
 */
@ConfigurationProperties(prefix="spring.exception")
public class ErrorProperties {

	/**
	 * 启用错误信息打印
	 */
	private boolean enablePrintStackTrace = true;
	
	/**
	 * 异常处理时，请求的类型
	 */
	private RequestType requestType = RequestType.DEFAULT; 
	
	/**
	 * 当请求不为ajax时，错误页面的路径
	 */
	private String errorViewName = "error/err";
	
	public boolean isEnablePrintStackTrace() {
		return enablePrintStackTrace;
	}

	public void setEnablePrintStackTrace(boolean enablePrintStackTrace) {
		this.enablePrintStackTrace = enablePrintStackTrace;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public String getErrorViewName() {
		return errorViewName;
	}

	public void setErrorViewName(String errorViewName) {
		this.errorViewName = errorViewName;
	}

	public enum RequestType{
		
		/**
		 * 默认前后端不分离 模式
		 */
		DEFAULT(1,"default"),
		
		
		/**
		 * 前后端分离模式 
		 */
		AJAX(2,"ajax"),
		
		
		/**
		 * 两种模式 
		 */
		ALL(3,"all");
		
		private Integer type;
		
		private String msg;
		
		public Integer getType() {
			return type;
		}

		public String getMsg() {
			return msg;
		}

		private RequestType(Integer type, String msg) {
			this.type = type;
			this.msg = msg;
		}
		
	}
}
