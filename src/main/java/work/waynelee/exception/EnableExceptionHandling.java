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

import work.waynelee.exception.auto.ExceptionAutoConfiguration;

/**
 * 启用全局异常处理
 * @author lwq
 * 2019年3月29日 下午4:24:23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ExceptionAutoConfiguration.class})
public @interface EnableExceptionHandling {

}
