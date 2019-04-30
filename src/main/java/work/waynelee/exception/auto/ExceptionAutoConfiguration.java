/**
 * 
 */
package work.waynelee.exception.auto;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import work.waynelee.exception.MyExceptionHandler;
import work.waynelee.exception.properties.ErrorProperties;

/**
 *
 * @author 李文庆
 * 2019年3月14日 下午2:37:41
 */
@Configuration
@EnableConfigurationProperties(value={ErrorProperties.class})
public class ExceptionAutoConfiguration {
	
	@Bean
	public MyExceptionHandler myExceptionHandler(){
		MyExceptionHandler myExceptionHandler = new MyExceptionHandler();
		return myExceptionHandler;
	}
}
