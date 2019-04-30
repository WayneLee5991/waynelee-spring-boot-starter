/**
 * 
 */
package work.waynelee.utils;

/**
 * service层封装返回数据
 * @author WayneLee
 * 2019年2月19日 下午3:47:05
 */
public class ReturnDataDTO<T> {

	private Integer status;
	
	private T data;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public ReturnDataDTO() {
		super();
	}

	public ReturnDataDTO(Integer status, T data) {
		super();
		this.status = status;
		this.data = data;
	}

	
	@Override
	public String toString() {
		return "ReturnDataDTO [status=" + status + ", data=" + data + "]";
	}
	
	/**
	 * 请求成功
	 * @return 
	 * staus：1 <br>
	 * data：null
	 */
	public static <T> ReturnDataDTO<T> ok(){
		return new DefaultBuilder(1).build();
	}
	
	/**
	 * 请求成功
	 * @param data 数据
	 * @return
	 * staus：1 <br>
	 * data：T data
	 */
	public static <T> ReturnDataDTO<T> ok(T data){
		return  new DefaultBuilder(1).build(data);
	}
	
	/**
	 * 请求错误，返回错误代码
	 * @param status 状态码
	 * @return
	 * status：status<br>
	 * data：null
	 */
	public static <T> ReturnDataDTO<T> error(Integer status){
		return new DefaultBuilder(status).build();
	}
	
	/**
	 * 请求错误，返回错误代码
	 * @param status 状态码
	 * @return
	 * status：status<br>
	 * data：null
	 */
	public static <T> ReturnDataDTO<T> error(Integer status,T data){
		return new DefaultBuilder(status).build(data);
	}
	
	/**
	 * 请求错误，返回错误代码
	 * @param status 状态码
	 * @return
	 * status：status<br>
	 * msg：错误信息
	 */
	public static <T> ReturnDataDTO<T> error(Integer status,String msg){
		return new DefaultBuilder(status).build();
	}
	
	public interface BodyBuilder{
		
		<T> ReturnDataDTO<T> build();
		
		<T> ReturnDataDTO<T> build(T data);
		
	}
	
	private static class DefaultBuilder implements BodyBuilder{
		
		private final Integer status;

		public DefaultBuilder(Integer status) {
			super();
			this.status = status;
		}

		@Override
		public <T> ReturnDataDTO<T> build() {
			return new ReturnDataDTO<T>(this.status,null);
		}
		
		@Override
		public <T> ReturnDataDTO<T> build(T data) {
			return new ReturnDataDTO<T>(this.status, data);
		}
	}
}
