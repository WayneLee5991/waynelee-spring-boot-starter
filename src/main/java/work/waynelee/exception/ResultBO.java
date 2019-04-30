/**
 * 
 */
package work.waynelee.exception;

import java.io.Serializable;

/**
 * 自定义返回数据
 * @author 李文庆
 * @date 2018年4月8日下午1:08:13
 */

public class ResultBO<T> implements Serializable {

	private static final long serialVersionUID = -4006037402528281189L;

	/** 错误码 */
    private Integer code;
    
    /**  提示信息 */
    private String msg;
    /**
     * 返回的具体内容<br>
     * 有默认的初始值——空字符串"",
     * 因为阿里巴巴的fastjson处理数据时，不允许存在内容为null的字段
     */
    private T data;
    
    public ResultBO(Integer code,String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "ResultBO [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }
}
