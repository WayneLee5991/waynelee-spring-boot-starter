/**
 * 
 */
package work.waynelee.exception;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import work.waynelee.exception.auto.RequestLoggingAutoConfiguration;

/**
 * 启用请求日志打印
 * @author lwq
 * 2019年3月29日 下午4:24:23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({RequestLoggingAutoConfiguration.class})
public @interface EnableRequestLogging {

}
