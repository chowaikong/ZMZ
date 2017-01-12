package me.knox.zmz.ui.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KNOX.
 */

public class TimeUtil {

  public static final String HH_MM               = "HH:mm";
  public static final String YYYY_MM_DD_HH_MM    = "yyyy-MM-dd HH:mm";
  public static final String YYYY_MM_DD          = "yyyy-MM-dd";
  public static final String MM_DD               = "MM-dd";
  public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

  /**
   * @return the time between current and specified time
   *
   * @param current current(millisecond)
   * @param time specified time（millisecond）
   * @param isShowDay show day or not
   */
  public static String getTime(long current, long time, boolean isShowDay) {
    if (!isShowDay) {
      return getTimeToMinute(time);
    }

    long a = ((current - time) / 60) / 1000;
    if (a <= 3) {
      return "刚刚";
    } else if (a < 60) {
      return a + "分钟前";
    } else if (a < 60 * 24) {
      return (int) a / 60 + "小时前";
    } else if (a < 60 * 24 * 7) {
      return (int) (a / (60 * 24)) + "天前";
    } else {
      return getTimeToDay(time);
    }
  }

  /**
   * @return Hour and minute
   *
   * @param mill time in millisecond
   */
  public static String convertHoursAndMinutes(long mill) {
    return convert(mill, HH_MM);
  }

  private static String convert(long mill, String format) {
    Date date = new Date(mill);
    String strs = "";
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      strs = sdf.format(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return strs;
  }

  /**
   * @return specified the last day of month by year and month
   *
   * @param year specified year
   * @param month specified month
   */
  public static String getLastDayOfMonth(int year, int month) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
    return new SimpleDateFormat(YYYY_MM_DD).format(cal.getTime());
  }

  public static String getTimeToDayWithSecond(long time) {
    if (time < 0) {
      return "";
    }
    SimpleDateFormat sdf = new SimpleDateFormat();
    String pattern = YYYY_MM_DD;
    sdf.applyPattern(pattern);
    return sdf.format(time * 1000);
  }

  public static String getTimeToDay(long time) {
    if (time < 0) {
      return "";
    }
    SimpleDateFormat sdf = new SimpleDateFormat();
    String pattern = YYYY_MM_DD;
    sdf.applyPattern(pattern);
    return sdf.format(time);
  }

  public static String getTimeToMinute(long time) {
    if (time < 0) {
      return "";
    }
    SimpleDateFormat sdf = new SimpleDateFormat();
    String pattern = YYYY_MM_DD_HH_MM;
    sdf.applyPattern(pattern);
    return sdf.format(time);
  }

  /**
   * @return specified the first day of month by year and month
   *
   * @param year specified year
   * @param month specified month
   */
  public static String getFirstDayOfMonth(int year, int month) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month);
    cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
    return new SimpleDateFormat(YYYY_MM_DD).format(cal.getTime());
  }

  /**
   * @return time period
   *
   * @param beginTime begin
   * @param endTime end
   */
  public static String getPeriod(long beginTime, long endTime, String patternBegin,
      String patternEnd) {
    SimpleDateFormat sdf = new SimpleDateFormat();
    sdf.applyPattern(patternBegin);
    Date date = new Date(beginTime);
    String scheduleTime = sdf.format(date);
    sdf.applyPattern(patternEnd);
    date.setTime(endTime);
    scheduleTime += "-" + sdf.format(date);
    return scheduleTime;
  }

  /**
   * @return 20xx-xx-xx
   */
  public static String convert2Date(long mill) {
    return convert(mill, YYYY_MM_DD);
  }

  /**
   * @return 20xx-xx-xx xx:xx
   */
  public static String convert2Minute(long mill) {
    return convert(mill, YYYY_MM_DD_HH_MM);
  }

  /**
   * @return 20xx-xx-xx XX:xx:xx
   */
  public static String convert2Second(long mill) {
    return convert(mill, YYYY_MM_DD_HH_MM_SS);
  }

  /**
   * @return xx-xx xx:xx
   */
  public static String convert2DateAndMinutes(long mill) {
    return convert(mill, MM_DD+ " " + HH_MM);
  }

  /**
   * @return weeks
   */
  public static String getWeeks() {
    Calendar cal = Calendar.getInstance();
    int today = cal.get(Calendar.DAY_OF_WEEK);
    String week = null;
    switch (today) {
      case 1:
        week = "星期日";
        break;
      case 2:
        week = "星期一";
        break;
      case 3:
        week = "星期二";
        break;
      case 4:
        week = "星期三";
        break;
      case 5:
        week = "星期四";
        break;
      case 6:
        week = "星期五";
        break;
      case 7:
        week = "星期六";
        break;
    }

    return week;
  }
}
