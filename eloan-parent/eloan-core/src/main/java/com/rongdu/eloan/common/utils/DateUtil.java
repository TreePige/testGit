package com.rongdu.eloan.common.utils;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Description: 日期处理工具类
 * </p>.
 *
 * @author：wujing
 * @version 1.0
 * @since 2014-3-26
 */
public class DateUtil {

	/** 时间格式：yyyy-MM-dd HH:mm:ss. */
	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/** 时间格式：yyyy-MM-dd HH:mm. */
	public static final String DATETIME_PATTERN_NO_SECOND = "yyyy-MM-dd HH:mm";
	
	/** 时间格式：yyyy-MM-dd. */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	
	/** 时间格式：yyyyMMdd. */
	public static final String DATE_PATTERN_2 = "yyyyMMdd";
	
	/** 时间格式：ddMMyyyy. */
	public static final String DATE_PATTERN_3 = "ddMMyyyy";
	
	/** 时间格式：yyyyMM. */
	public static final String DATE_PATTERN_4 = "yyyyMM";
	
	/** 时间格式：yyyy-MM. */
	public static final String MONTH_PATTERN = "yyyy-MM";
	
	/** The Constant TIME_DAY_MILLISECOND. */
	public final static int TIME_DAY_MILLISECOND = 86400000;

	/** 日志. */
	private static final Logger logger = Logger.getLogger(DateUtil.class);

	/**
	 * 计算多少天以后的日期.
	 *
	 * @param d the d
	 * @param day the day
	 * @return the date
	 */
	public static Date rollDay(Date d, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}
	
	/**
	 * 根据某个日期获取多少天以前的日期.
	 *
	 * @param d the d
	 * @param day the day
	 * @return the string
	 * @version 1.0
	 * @author 吴国成
	 * @created 2015年3月20日
	 */
	public static String yesteday(Date d, int day) {
        //当前时间
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, -day);
        SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN); 
        String str = sf.format(cal.getTime());           
        return str;
   }

	/**
	 * 计算多少月以后的日期.
	 *
	 * @param d the d
	 * @param mon the mon
	 * @return the date
	 */
	public static Date rollMon(Date d, int mon) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MONTH, mon);
		return cal.getTime();
	}

	/**
	 * 取得今天开始时间.
	 *
	 * @return the start time
	 */
	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	/**
	 * 取得今天结束时间.
	 *
	 * @return the end time
	 */
	public static Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

	/**
	 * 获取指定时间天的结束时间.
	 *
	 * @param day 指定时间
	 * @return 结束时间
	 */
	public static Date getDayEndTime(Date day) {
		Calendar cal = Calendar.getInstance();
		if (day != null) {
			cal.setTimeInMillis(day.getTime());
		}
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
		return cal.getTime();
	}

	/**
	 * 获取指定时间天的开始时间.
	 *
	 * @param day 指定时间
	 * @return 开始时间
	 */
	public static Date getDayStartTime(Date day) {
		Calendar cal = Calendar.getInstance();
		if (day != null) {
			cal.setTimeInMillis(day.getTime());
		}
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 将字符串按照格式转换成时间.
	 *
	 * @param str 传入时间参数
	 * @param f 格式
	 * @return 时间
	 */
	public static Date getDate(String str, String f) {
		SimpleDateFormat format = new SimpleDateFormat(f);
		if (str == null || str.length() <= 0) {
			return null;
		}
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			logger.error("参数:" + str + "格式不正确！");
			return null;
		}
		return date;
	}

	/**
	 * Convert String to Date.
	 *
	 * @param value string
	 * @return date
	 */
	public static Date doConvertToDate(Object value) {
		Date result = null;
		
		if (value == null) {
			return null;
		}

		if (value instanceof String) {
			if (StringUtils.isNotEmpty((String) value)) {
				try {
					result = DateUtils.parseDate((String) value, new String[] { DATE_PATTERN, DATETIME_PATTERN,
							DATETIME_PATTERN_NO_SECOND, MONTH_PATTERN });
				} catch (ParseException e) {
					e.printStackTrace();
				}

				// all patterns failed, try a milliseconds constructor
				if (result == null) {

					try {
						result = new Date(new Long((String) value).longValue());
					} catch (Exception e) {
						logger.error("Converting from milliseconds to Date fails!");
						e.printStackTrace();
					}

				}
			}
		} else if (value instanceof Object[]) {
			// let's try to convert the first element only
			Object[] array = (Object[]) value;

			if ((array != null) && (array.length >= 1)) {
				result = doConvertToDate(array[0]);
			}

		} else if (Date.class.isAssignableFrom(value.getClass())) {
			result = (Date) value;
		}
		return result;
	}

	/**
	 * 将时间按照格式转换成字符串.
	 *
	 * @param date 时间
	 * @param f 格式
	 * @return 字符串
	 */
	public static String dateStr(Date date, String f) {
		SimpleDateFormat format = new SimpleDateFormat(f);
		String str = format.format(date);
		return str;
	}

	/**
	 * Now.
	 *
	 * @return the date
	 */
	public static Date now() {
		return new Date();
	}
	
	
	/**
	 * @description 获得下个月的今天  
	 * @return
	 * @author lukf
	 * @return String
	 * @since  1.0.0
	*/
	public static String thisDayNextMonth(){
		//获得下个月的今天  
		Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		            Calendar c = Calendar.getInstance();  
		            c.setTime(date);//d);  
		            c.add(c.MONTH, 1);  
		            Date temp_date = c.getTime();   
		            System.out.println(format.format(temp_date));//结果2012-11-30  
		            return format.format(temp_date);  
	}
	
	/**
	 * @description 获得上个月的特定日期  
	 * @return
	 * @author fzc
	 * @return String
	 * @since  1.0.0
	*/
	public static Date thisDayLastMonth(Date date){ 
		//获得上个月的日期  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);//d);  
        c.add(c.MONTH, -1);
        Date temp_date = c.getTime();   
        return temp_date;  
	}
	
	/**
	 * 根据格式得到格式化后的日期.
	 *
	 * @param currDate 要格式化的日期
	 * @return String 返回格式化后的日期，格式由参数<code>format</code>
	 * 定义，如yyyy-MM-dd，如2006-02-15
	 * @see java.text.SimpleDateFormat#format(java.util.Date)
	 */
    public static String getFormatDate(Date currDate) {
        if (currDate == null) {
            return "";
        }
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(DATE_PATTERN);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_PATTERN);
            try {
                return dtFormatdB.format(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }
    
    /**
     * 根据格式得到格式化后的时间.
     *
     * @param currDate 要格式化的时间
     * @return Date 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd
     * HH:mm:ss
     * @see java.text.SimpleDateFormat#parse(java.lang.String)
     */
    public static Date getFormatDateTime(String currDate) {
        if (currDate == null) {
            return null;
        }
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(DATE_PATTERN);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_PATTERN);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }
    
    /**
     * 获得两个Date型日期之间相差的天数（第2个减第1个）.
     *
     * @param first the first
     * @param second the second
     * @return int 相差的天数
     */
    public static int getDaysBetweenDates(Date first, Date second) {
        Date d1 = getFormatDateTime(getFormatDate(first));
        Date d2 = getFormatDateTime(getFormatDate(second));

        Long mils = (d2.getTime() - d1.getTime()) / (TIME_DAY_MILLISECOND);

        return mils.intValue();
    }
    
    
    /**
     * 获取一个日期的下周一的日期.
     *
     * @param date the date
     * @return the next week monday
     */
    public static Date getNextWeekMonday(Date date){
    	Calendar cal = Calendar.getInstance();
		
		cal.setTime(date);
		
        int week = cal.get(Calendar.DAY_OF_WEEK);
        
        if(week>2){
            cal.add(Calendar.DAY_OF_MONTH,-(week-2)+7);
        }else{
            cal.add(Calendar.DAY_OF_MONTH,2-week+7);
        }
		return cal.getTime();
    };
    /**
     * 
     * @description 根据制定格式获得日期
     * @param dateFormat
     * @return
     * @author 孙凯伦
     * @return String
     * @since  1.0.0
     */
    public static String getNowTime(String dateFormat){
    	Date date = new Date();
    	SimpleDateFormat format	= new SimpleDateFormat(dateFormat);
    	String formatDate = format.format(date);
    	return formatDate;
    }
    /**
     * 
     * @description 	数据库Object的Date格式化为String
     * @param str		格式化类型为2016-01-06 17:04:47.0
     * @return			格式完毕后的时间
     * @author 孙凯伦
     * @return String
     * @since  1.0.0
     */
    public static String ObjectFormatDate(Object object){
    	try {
    		//转换类型
    		String str = ObjectTool.String(object);
    		//定义格式化的格式
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			//转后为String的格式
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	    	//转换后
			String string = sdf.format(date);
			return string;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return "";
    }
    
    /** 
     * 取得当月天数 
     * */  
    public static int getCurrentMonthLastDay()  
    {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }
}
