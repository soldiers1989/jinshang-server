package project.jinshang.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期工具类。封装了一些操作日期的方法。该类全部是静态方法。
 * @author lizhm
 *
 */
public class DateUtils {
    public static final String xinQiNames[] =  {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
    /**
     * 格式化输出日期
     *
     * @param date
     *            日期
     * @param format
     *            格式
     * @return 返回字符型日期
     */
    public static String format(java.util.Date date, String stly) {
        Locale locale =null;
        if(stly.indexOf("MMM")!=-1){
            locale= new Locale("en");
        }
        String result = "";
        String str="yyyy-MM-dd HH:mm:ss";
        if(stly!=null&&!stly.equals(""))str=stly;
        try {
            if (date != null) {
                if(locale==null){
                    java.text.DateFormat df = new java.text.SimpleDateFormat(str);
                    result = df.format(date);
                }else{
                    java.text.DateFormat df = new java.text.SimpleDateFormat(str,locale);
                    result = df.format(date);
                }

            }
        } catch (Exception e) {
        }
        return result;
    }


    private static final int[] DAYS_OF_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /**
     * 获取指定月共有多少天
     * @param yy 年份
     * @param mmBased0 月份（1月份为0）
     * @return 该月的天数，如果月份不合法，返回0
     */
    public static int getDaysOfMonth(final int yy, final int mmBased0) {
        if (mmBased0 == 1) {
            return ((yy % 4 == 0 && yy % 100 != 0) || yy % 400 == 0) ? 29 : 28;
        } else if (mmBased0 >= 0 && mmBased0 < 12) {
            return DateUtils.DAYS_OF_MONTH[mmBased0];
        } else {
            return 0;
        }
    }

    /**
     * 根据毫秒数值创建Date对象
     *
     * @param timeMillis 毫秒数
     * @return java.util.Date 对象
     */
    public static Date getDate(final long timeMillis) {
        Date date = new Date();
        date.setTime(timeMillis);
        return date;
    }

    /**
     * 根据给定的年月日时分秒创建Date对象
     *
     * @param yyyy 年
     * @param mmBased0 月份（1月份为0）
     * @param dd 日
     * @param hh24 小时（24时制）
     * @param mi 分
     * @param ss 秒
     * @return java.util.Date 对象
     */
    public static Date getDate(final int yyyy, final int mmBased0, final int dd, final int hh24, final int mi,
                               final int ss) {
        return new GregorianCalendar(yyyy, mmBased0, dd, hh24, mi, ss).getTime();
    }

    /**
     * 创建日期对象
     *
     * @param yyyy 年
     * @param mmBased0 月份（1月份为0）
     * @param dd 日
     * @return java.util.Date 对象
     */
    public static Date getDate(final int yyyy, final int mmBased0, final int dd) {
        return new GregorianCalendar(yyyy, mmBased0, dd).getTime();
    }

    /**
     * 创建日期对象
     *
     * @param yyyy 年
     * @param mmBased0 月份（1月份为0）
     * @return java.util.Date 对象
     */
    public static Date getDate(final int yyyy, final int mmBased0) {
        return new GregorianCalendar(yyyy, mmBased0, 1, 0, 0, 0).getTime();
    }

    /**
     * 复制一个Date对象
     *
     * @param date
     * @return
     */
    public static Date cloneDate(final Date date) {
        Date nd = new Date();
        nd.setTime(date.getTime());
        return nd;
    }

    /**
     * 在一个给定日期上增加若干分钟
     *
     * @param date
     * @param m
     */
    public static Date addMinutes(final Date date, final int m) {
        long t = date.getTime();
        t += m * 60 * 1000L;
        date.setTime(t);
        return date;
    }

    /**
     * 在一个指定日期上增加若干天
     *
     * @param date
     * @param d
     */
    public static Date addDays(final Date date, final int d) {
        return DateUtils.addMinutes(date, d * 1440);
    }

    /**
     * 给定的字符串（yyyy-MM-dd）加指定天数
     * @param begintime
     * @param days
     * @return
     */
    public static String addDays(String begintime,int days) {
        SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
        Date   tmpDate   =   null;
        try   {
            tmpDate   =   df.parse(begintime);
        }
        catch(Exception   e){
            System.out.println("日期型字符串格式错误");
        }
        long   nDay=(tmpDate.getTime()/(24*60*60*1000)+1+days)*(24*60*60*1000);
        tmpDate.setTime(nDay);

        return   df.format(tmpDate);

    }

    /**
     * 计算 从 ds 到 dt 经过的秒数。
     *
     * @param ds
     * @param dt
     * @return
     */
    public static long diffSeconds(final Date ds, final Date dt) {
        long s = ds.getTime();
        long t = dt.getTime();
        return (t - s) / 1000;
    }

    /**
     * 计算从ds到dt经过的天数
     * @param ds
     * @param dt
     * @return
     */
    public static int diffDays(final Date ds, final Date dt) {
//        long s = ds.getTime();
//        long t = dt.getTime();
//        return (int) (t - s) / 1000 / 86400;

        //Date d1 = df.parse("2004-03-26 13:31:40");
        //Date d2 = df.parse("2004-01-02 11:30:24");
        long diff = ds.getTime() - dt.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return (int)days;
    }

    /**
     * 2个日期之间相差的月数
     *
     * @return
     */
    public static int diffMonths(final Date sd, final Date ed) {
        int s = sd.getYear() * 12 + sd.getMonth();
        int e = ed.getYear() * 12 + ed.getMonth();
        return e - s;
    }

    /**
     * 获取一个Date对象表示的毫秒数
     *
     * @param d
     * @return
     */
    public static long getTimeMillis(final Date d) {
        return null == d ? 0 : d.getTime();
    }

    public static String getXingQi(Date date)
    {
        //String s = "2012-01-12 16:30";
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        //Date date = new Date();
//        try {
//			date = sdfInput.parse(d);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(dayOfWeek<0){
            dayOfWeek=0;
        }
        //System.out.println(xinQiNames[dayOfWeek]);
        return xinQiNames[dayOfWeek];

    }
    /**
     * 是否是节假日
     * @param date
     * @return true是
     */
    public static boolean isHoliday(Date date){
        if(DateUtils.getXingQi(date).equals("星期日") || DateUtils.getXingQi(date).equals("星期六") ){
            return true;
        }

        return false;
    }
    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {
        //1800-01-01
        if(str==null || str.trim().equals("")) return null;
        String format="";
        if(str.length()<=10) {
            format="yyyy-MM-dd";
        }else {
            format="yyyy-MM-dd HH:mm:ss";
        }
        int first = StringUtils.intValue(str.substring(0,2));
        if(first<18){
            return null;
        }
        SimpleDateFormat sd = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sd.parse(str);
//    	   System.out.println(date.getTime());
//    	   System.out.println(System.currentTimeMillis());
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return date;
    }

    public static Date StrToDate(String str ,String style) {
        if(style==null || style.trim().equals("")){
            style = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(style);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return date;
    }




}