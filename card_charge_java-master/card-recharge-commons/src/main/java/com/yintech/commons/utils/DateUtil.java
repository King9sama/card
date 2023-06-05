package com.yintech.commons.utils;

import com.yintech.commons.constant.DateEnum;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description
 * @Company: yintech
 * @date 2021/6/21 4:34 下午
 */
public class DateUtil {
    /**
     * 日期默认格式
     */
    public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
    /**
     * 时间默认格式
     */
    public static final String DATETIME_FORMAT_DEFAULT = "yyyy-MM-dd hh:mm:ss";

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalDateTime nowDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 初始化时间
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @param hourOfDay
     * @param minuteOfHour
     * @param secondOfMinute
     * @return
     */
    public static LocalDateTime newDateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute) {
        return LocalDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute);
    }

    /**
     * 按照指定格式格式化时间
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (Objects.isNull(dateTime) || StringUtil.isEmpty(pattern)) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 按照指定格式解析时间
     *
     * @param text
     * @param pattern
     * @return
     */
    public static LocalDateTime parseDateTime(String text, String pattern) {
        if (StringUtil.isEmpty(text) || StringUtil.isEmpty(pattern)) {
            return null;
        }
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 当前日期
     *
     * @return
     */
    public static LocalDate nowDate() {
        return LocalDate.now();
    }

    /**
     * 初始化日期
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static LocalDate newDate(int year, int monthOfYear, int dayOfMonth) {
        return LocalDate.of(year, monthOfYear, dayOfMonth);
    }

    /**
     * 按照指定格式格式化时间
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String formatDate(LocalDate localDate, String pattern) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 增加或者减少时间，只支持年，月，日
     *
     * @param localDate
     * @param dateType
     * @param plus
     * @return
     */
    public static LocalDate plus(LocalDate localDate, DateEnum dateType, int plus) {
        if (Objects.isNull(localDate)) {
            throw new IllegalArgumentException("日期不能为空");
        }
        if (Objects.isNull(dateType)) {
            throw new IllegalArgumentException("日期类型不能为空");
        }
        if (dateType.equals(DateEnum.YEAR)) {
            return localDate.plusYears(plus);
        } else if (dateType.equals(DateEnum.MONTH)) {
            return localDate.plusMonths(plus);
        } else if (dateType.equals(DateEnum.DAY)) {
            return localDate.plusDays(plus);
        } else if (dateType.equals(DateEnum.WEEK)) {
            return localDate.plusWeeks(plus);
        }
        throw new IllegalArgumentException("不支持的日期类型[" + dateType.getType() + "]");
    }

    /**
     * 向前或者向后滚动时间
     *
     * @param localDateTime
     * @param dateType
     * @param plus
     * @return
     */
    public static LocalDateTime plus(LocalDateTime localDateTime, DateEnum dateType, int plus) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException("时间不能为空");
        }
        if (Objects.isNull(dateType)) {
            throw new IllegalArgumentException("时间类型不能为空");
        }
        if (dateType.equals(DateEnum.YEAR)) {
            return localDateTime.plusYears(plus);
        } else if (dateType.equals(DateEnum.MONTH)) {
            return localDateTime.plusMonths(plus);
        } else if (dateType.equals(DateEnum.DAY)) {
            return localDateTime.plusDays(plus);
        } else if (dateType.equals(DateEnum.WEEK)) {
            return localDateTime.plusWeeks(plus);
        } else if (dateType.equals(DateEnum.HOUR)) {
            return localDateTime.plusHours(plus);
        } else if (dateType.equals(DateEnum.MINUTE)) {
            return localDateTime.plusMinutes(plus);
        } else {
            return localDateTime.plusSeconds(plus);
        }
    }

    /**
     * 日期类型转换，LocalDate转Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 日期类型转换，Date转LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 日期类型转换，LocalDateTime转Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 日期类型转换，Date转LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static String getFirstDayOfMonth() {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime with = date.with(TemporalAdjusters.firstDayOfMonth());
        return formatDateTime(with, DATE_FORMAT_DEFAULT);
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static String getLastDayOfMonth() {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime with = date.with(TemporalAdjusters.lastDayOfMonth());
        return formatDateTime(with, DATE_FORMAT_DEFAULT);
    }

    /**
     * 获取本周第一天
     *
     * @return
     */
    public static String getFirstDayOfWeek() {
        LocalDate localDate = LocalDate.now();
        LocalDate with = localDate.with(DayOfWeek.MONDAY);
        return formatDate(with, DATE_FORMAT_DEFAULT);
    }

    /**
     * 本周最后一天
     *
     * @return
     */
    public static String getLastDayOfWeek() {
        LocalDate localDate = LocalDate.now();
        LocalDate with = localDate.with(DayOfWeek.SUNDAY);
        return formatDate(with, DATE_FORMAT_DEFAULT);
    }
}
