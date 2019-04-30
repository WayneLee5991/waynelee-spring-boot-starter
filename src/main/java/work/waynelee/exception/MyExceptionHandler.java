/**
 * 
 */
package work.waynelee.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import work.waynelee.exception.properties.ErrorProperties;

/**
 * 异常拦截器
 * @author lwq
 * 2019年1月15日 下午1:14:21
 */
@ControllerAdvice
@Order(2)
public class MyExceptionHandler {
	
	@Autowired
	private ErrorProperties errorProperties;
	
	/**
	 * 全局异常处理，根据请求头数据的不同，判断是否为ajax请求，做出相应的处理
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Object exceptionHandler(Exception e,HttpServletRequest request){
		
		Integer type = errorProperties.getRequestType().getType();
		
		//如果是前后端不分离模式
		if (Objects.equals(type,1)) {
			
			return resoleDefaultException(e, request);
		
		//如果是前后端分离的模式
		}else if (Objects.equals(type,2)) {
			
			return resoleAjaxException(e);
		
		//两种模式都有
		}else{
			
			return resoleAllException(e, request);
		}
		
        
	}
	
	/**
	 * 前后端不分离模式时处理异常
	 * @param e
	 * @param request
	 * @return
	 */
	public ModelAndView resoleDefaultException(Exception e,HttpServletRequest request){
		
		boolean enablePrintStackTrace = errorProperties.isEnablePrintStackTrace();
		String url = request.getRequestURL().toString();
	    ModelAndView mav = new ModelAndView();
	    
	    mav.setViewName(errorProperties.getErrorViewName());
	    
	    mav.addObject("url", url);
	    
	    if(e instanceof MyException){
            MyException em = (MyException) e;
            mav.addObject("code",em.getCode());
            mav.addObject("msg", em.getMessage());
        }else if(e instanceof NullPointerException){
        	if (enablePrintStackTrace) {
        		e.printStackTrace();
			}
        	mav.addObject("msg", "空指针异常");
        }else{
        	if (enablePrintStackTrace) {
        		e.printStackTrace();
			}
        	mav.addObject("msg", e.getMessage());
        }
	    
	    mav.addObject("stackTrace",e.getStackTrace()[0]);
	    
	    return mav;
	}
	
	/**
	 * 前后端分离模式下处理异常信息
	 * @param e
	 * @return
	 */
	public ResultBO<Object> resoleAjaxException(Exception e){
		
		boolean enablePrintStackTrace = errorProperties.isEnablePrintStackTrace();
		
		//判定是否是自己定义的异常
        if(e instanceof MyException){
            MyException em = (MyException) e;
            return ResultTool.error(em.getCode(),em.getMessage());
        //判断是否为空指针异常
        }else if(e instanceof NullPointerException){
        	if (enablePrintStackTrace) {
        		e.printStackTrace();
			}
        	return ResultTool.error(2,"空指针异常");
        }
        if (enablePrintStackTrace) {
    		e.printStackTrace();
		}
        StringWriter sw = new StringWriter();  
        PrintWriter pw = new PrintWriter(sw, true);  
        //e.printStackTrace(pw);  
        pw.flush();   
        sw.flush(); 
        String result = sw.toString(); 
        
        String regEx = "Caused by:(.*)";  
	    Pattern pat = Pattern.compile(regEx);  
	    Matcher mat = pat.matcher(result);  
	    boolean rs = mat.find();  
	    if (rs) {
	    	return ResultTool.error(2,e.getClass().getName()+" : "+e.getMessage(),mat.group(1));
		}else{
			//返回系统异常
	        return ResultTool.error(2,e.getClass().getName()+" : "+e.getMessage(),e.getStackTrace()[0]);
		}
	}
	
	/**
	 * 两种模式都有的情况下处理异常
	 * @param e
	 * @param request
	 * @return
	 */
	public Object resoleAllException(Exception e,HttpServletRequest request){
		
		String url = request.getRequestURL().toString();
		//判断是否为ajax请求
	    String acceptHeader = request.getHeader("Accept");
		String xRequestedWith = request.getHeader("x-requested-with");
		String referer = request.getHeader("Referer");
		String postmanToken = request.getHeader("postman-token");
		//如果是ajax请求
	    if ((acceptHeader != null && acceptHeader.contains("application/json"))
	    		|| xRequestedWith != null && "XMLHttpRequest".equals(xRequestedWith)
	            || referer != null && !url.equalsIgnoreCase(referer)
	            || postmanToken != null) {
	    	
	    	return resoleAjaxException(e);
		    
	    }else{
	    
		    return resoleDefaultException(e, request);
	    }
	}
	
}
