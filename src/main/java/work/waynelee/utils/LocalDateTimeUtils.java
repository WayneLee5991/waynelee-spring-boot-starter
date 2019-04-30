/**
 * 
 */
package work.waynelee.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author 李文庆
 * 2018年5月17日 下午2:34:05
 */
public class LocalDateTimeUtils {

	private LocalDateTimeUtils() {
		super();
	}
	
	/**日期格式字符串转为LocalDateTime 年月日时分秒
	 * @param time 日期格式字符串
	 * @return
	 */
	public static LocalDateTime stringToLocalDateTime(String time){
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(time,df);
		
	}
	
	/**日期格式字符串转为LocalDate 年月日
	 * @param time 日期格式字符串
	 * @return
	 */
	public static LocalDate stringToLocalDate(String time){
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(time,df);
		
	}

	/**LocalDateTime转为日期格式字符串
	 * @param time
	 * @param pattern 要转的格式
	 * @return
	 */
	public static String localDateTimeToString (LocalDateTime time,String pattern){
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
		return df.format(time);
		
	}
	
	/**LocalDate转为日期格式字符串
	 * @param time
	 * @param pattern 要转的格式
	 * @return
	 */
	public static String localDateToString (LocalDate time,String pattern){
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
		return df.format(time);
		
	}
	
	/**java.util.Date转为LocalDateTime
	 * @param date
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date date){
		
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	/**LocalDateTime转为java.util.Date
	 * @param time
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime time){
		
		return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
		
	}
	
	/**
	 * 指定时间格式化
	 * @param time LocalDateTime
	 * @param pattern
	 * @return
	 */
	public static String localDateTimeFormat(LocalDateTime time,String pattern) {
		
	    return time.format(DateTimeFormatter.ofPattern(pattern));
	    
	}
	
	/**
	 * 指定时间格式化
	 * @param time LocalDate
	 * @param pattern
	 * @return
	 */
	public static String localDateFormat(LocalDate time,String pattern) {
		
	    return time.format(DateTimeFormatter.ofPattern(pattern));
	    
	}
	
	/**当前时间格式化
	 * @param pattern
	 * @return
	 */
	public static String formatNow(String pattern) {
		
	    return  localDateTimeFormat(LocalDateTime.now(), pattern);
	    
	}
	
	/**获取两个时间的时间差
	 * @param startTime
	 * @param endTime
	 * @param field 单位（年月日时分秒）ChronoUnit里的常量
	 * @return
	 */
	public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
		
		
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) return period.getYears();
        if (field == ChronoUnit.MONTHS) return period.getYears() * 12 + period.getMonths();
        return field.between(startTime, endTime);
        
    }
}
