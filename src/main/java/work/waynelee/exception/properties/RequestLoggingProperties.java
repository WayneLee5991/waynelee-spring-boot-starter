/**
 * 
 */
package work.waynelee.exception.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 请求日志打印配置
 * @author lwq
 * 2019年3月13日 下午5:23:17
 */
@ConfigurationProperties(prefix="spring.request.logging")
public class RequestLoggingProperties {

	private boolean enable;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
}
