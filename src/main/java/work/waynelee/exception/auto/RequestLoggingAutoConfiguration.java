/**
 * 
 */
package work.waynelee.exception.auto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import work.waynelee.exception.ControllerAspect;
import work.waynelee.exception.properties.RequestLoggingProperties;

/**
 * 记录请求日志自动配置
 * @author 李文庆
 * 2019年4月29日 上午10:42:08
 */
@Configuration
@EnableConfigurationProperties(value={RequestLoggingProperties.class})
@ConditionalOnClass(value=ControllerAspect.class)
@ConditionalOnProperty(prefix="spring.request.logging",value="enable",matchIfMissing=true)
public class RequestLoggingAutoConfiguration {

	@Bean                                                                                                                                   
    @ConditionalOnMissingBean(ControllerAspect.class)                                                                                           
    public ControllerAspect controllerAspect() {                                                                                           
		ControllerAspect controllerAspect = new ControllerAspect();
		return controllerAspect;
    }
	
}
