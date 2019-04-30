/**
 * 
 */
package work.waynelee.exception;

/**
 *
 * @author 李文庆
 * 2019年1月15日 下午12:51:50
 */
public class ResultTool {
	
    private ResultTool(){}
    
    /**
     * 成功时调用，调用者不需要数据
     * <br> 2018年4月18日上午11:24:15
     */
    public static ResultBO<Object> success(){
        return success(null);
    }
    
    /**
     * 成功时调用，并返回 data数据
     * <br>2018年4月18日上午11:22:40
     */
    public static  ResultBO<Object> success(Object data){
        return success("成功",data);
    }


    /**
     * 成功时调用，向调用者返回提示信息，并返回指定格式的数据
     */
    public static  ResultBO<Object> success(String msg,Object data){
        return new ResultBO<Object>(0, msg, data);
    }
    
    /**
     * 失败时调用<br>
     * （传入参数为null时，会自动设置 code为-1，msg为"未定义异常"）<br>
     * 2018年4月18日上午11:27:45
     */
    public static ResultBO<Object> error(ExceptionEnum en){
        if(en ==null){
            return new ResultBO<>(-1, "未定义异常", null);
        }
        return new ResultBO<>(en.getCode(), en.getMsg(), null);
    }
    
    public static ResultBO<Object> error(Integer code,String msg){
        if(code == null){
            code = -1;
        }
        if(msg == null){
            msg = "未定义异常";
        }
        msg = msg.trim();
        return new ResultBO<Object>(code, msg, null);
    }
    
    public static ResultBO<Object> error(Integer code,String msg,Object cause){
        if(code == null){
            code = -1;
        }
        if(msg == null){
            msg = "未定义异常";
        }
        msg = msg.trim();
        return new ResultBO<Object>(code, msg,cause);
    }
	
}
