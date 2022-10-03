package com.gst.framework.util;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GSDateStringConverter implements Converter<String, String> {
  
  private static final long serialVersionUID = -4050128496846455055L;
  
  static SimpleDateFormat source = new SimpleDateFormat("yyyyMMdd");
  
  static SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd");
  
  @Override
  public Result<String> convertToModel(String value, ValueContext context) {
    
    try {
      Date displayDate = target.parse(value);
      String model = source.format(displayDate);
      return Result.ok(model);
    } catch (ParseException e) {
      return Result.error("");
    }
  }
  
  @Override
  public String convertToPresentation(String value, ValueContext context) {
    
    try {
      Date modelDate = source.parse(value);
      return target.format(modelDate);
    } catch (ParseException e) {
      return "";
    }
  }
}
