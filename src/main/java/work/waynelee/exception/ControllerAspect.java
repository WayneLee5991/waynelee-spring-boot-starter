/**
 * 
 */
package work.waynelee.exception;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import work.waynelee.exception.properties.RequestLoggingProperties;
import work.waynelee.utils.IpGetterUtils;


/**
 * 记录请求日志
 * @author lwq
 * 2019年2月19日 下午2:22:35
 */
@Aspect
public class ControllerAspect {
	
	private static final Logger log = LoggerFactory.getLogger(ControllerAspect.class);
	
	private final String cut = "@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.GetMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.DeleteMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.PatchMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.PutMapping)";
	
	public long startTime;
	
	public long endTime;
	
	@Autowired
	private RequestLoggingProperties properties;

	/**请求方法前打印请求内容
	 * @param joinPoint
	 */
	@Before(value=cut)
	public void methodBefor(JoinPoint joinPoint) {
		if (properties.isEnable()) {
			startTime = System.currentTimeMillis();
			ServletRequestAttributes  requestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = requestAttributes.getRequest();
			String url = request.getRequestURL().toString();
			if (!url.contains("swagger")) {
				log.info("==========请求内容==========");
				log.info("请求ip:{}",IpGetterUtils.getIPAddress(request));
				log.info("请求全路径:{}",request.getRequestURL().toString());
				log.info("请求方式:{}",request.getMethod());
				log.info("请求端口:{}",request.getLocalPort());
				//log.info("请求类方法:{},joinPoint.getSignature());
				log.info("请求参数:{}",Arrays.toString(joinPoint.getArgs()));
				log.info("==========请求结束==========");
			}
		}
		
	}
	
	/**请求结束后打印返回内容
	 * @param returnValue 返回值
	 */
	@AfterReturning(pointcut=cut,returning="returnValue")
	public void methodAfterReturing(Object returnValue){
		if (properties.isEnable()) {
			
			endTime = System.currentTimeMillis();
			long currentTime = endTime - startTime;
			
			ServletRequestAttributes  requestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = requestAttributes.getRequest();
			String url = request.getRequestURL().toString();
			
			if (!url.contains("swagger")) {
				log.info("==========响应内容==========");
				log.info("请求耗时:{}",currentTime+"ms");
				
				if (returnValue instanceof ResultBO) {
					@SuppressWarnings("unchecked")
					ResultBO<Object> res = (ResultBO<Object>) returnValue;
					Integer code = res.getCode();
					String msg = res.getMsg();
					log.info("code:{}",code);
					log.info("msg:{}",msg);
				}else if(returnValue instanceof ResponseEntity){
					@SuppressWarnings("unchecked")
					ResponseEntity<Object> res = (ResponseEntity<Object>) returnValue;
					int code = res.getStatusCodeValue();
					log.info("code:{}",code);
				}else{
					log.info("返回数据:{}",returnValue);
				}
				log.info("==========响应内容==========");
			}
			
		}
		
	}
}
