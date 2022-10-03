package com.dcsplab.common;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DLDateUtils {

  static final SimpleDateFormat SIMPLE_YM_SDF = new SimpleDateFormat("yyyyMM");

  static SimpleDateFormat SIMPLE_DATE_SDF = new SimpleDateFormat("yyyyMMdd");

  static SimpleDateFormat NORMAL_DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");

  static SimpleDateFormat NORMAL_DATE_MINUTE_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  static SimpleDateFormat NORMAL_DATE_TIME_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  static SimpleDateFormat SIMPLE_TIME_SDF = new SimpleDateFormat("HH:mm:ss");

  public static String format(Resolution res) {
    return format(null, res);
  }

  public static String format(Date date, Resolution res) {
    if (date == null) {
      Calendar clndr = Calendar.getInstance();
      date = clndr.getTime();
    }

    if (res == Resolution.SimpleDate) {
      return SIMPLE_DATE_SDF.format(date);
    } else if (res == Resolution.NormalDate) {
      return NORMAL_DATE_SDF.format(date);
    } else if (res == Resolution.Time) {
      return SIMPLE_TIME_SDF.format(date);
    } else if (res == Resolution.Day) {
      return NORMAL_DATE_SDF.format(date);
    } else if (res == Resolution.Minute) {
      return NORMAL_DATE_MINUTE_SDF.format(date);
    } else if (res == Resolution.Full) {
      return NORMAL_DATE_TIME_SDF.format(date);
    } else if (res == Resolution.SimpleYM) {
      return SIMPLE_YM_SDF.format(date);
    } else {
      return NORMAL_DATE_TIME_SDF.format(date);
    }
  }

  public static Date parse(String value) throws Exception {
    return parse(value, Resolution.Full);
  }

  public static Date parse(String value, Resolution res) throws Exception {
    if (res == Resolution.SimpleDate) {
      return SIMPLE_DATE_SDF.parse(value);
    } else if (res == Resolution.NormalDate) {
      return NORMAL_DATE_SDF.parse(value);
    } else if (res == Resolution.Time) {
      throw new Exception("No such resolution for date parse: Time");
    } else if (res == Resolution.Day) {
      return NORMAL_DATE_SDF.parse(value);
    } else if (res == Resolution.Minute) {
      return NORMAL_DATE_MINUTE_SDF.parse(value);
    } else if (res == Resolution.Full) {
      return NORMAL_DATE_TIME_SDF.parse(value);
    } else if (res == Resolution.SimpleYM) {
      return SIMPLE_YM_SDF.parse(value);
    } else {
      throw new Exception("No such resolution for date parse: null");
    }
  }

  public enum Resolution {
    SimpleDate,
    NormalDate,
    Time,
    Day,
    Minute,
    Full,
    SimpleYM
  }

  public static class DateConverter implements Converter<String, Date> {

    private static final long serialVersionUID = 5124471304573004189L;

    @Override
    public Result<Date> convertToModel(String date, ValueContext valueContext) {

      try {
        return Result.ok(parse(date, Resolution.Full));
      } catch (Exception e) {
        return Result.error("date format eror:" + date);
      }
    }

    @Override
    public String convertToPresentation(Date date, ValueContext valueContext) {
      return format(date, DLDateUtils.Resolution.Full);
    }
  }
}
