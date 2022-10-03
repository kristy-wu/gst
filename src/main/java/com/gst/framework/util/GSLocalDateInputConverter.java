package com.gst.framework.util;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class GSLocalDateInputConverter implements Converter<LocalDate, Date> {
  
  private static final long serialVersionUID = 8152391528645164706L;
  
  @Override
  public Result<Date> convertToModel(LocalDate fieldValue, ValueContext context) {
    // Produces a converted value or an error
    try {
      Date date = Date.from(fieldValue.atStartOfDay(ZoneId.systemDefault()).toInstant());
      // ok is a static helper method that
      // creates a Result
      return Result.ok(date);
    } catch (Exception e) {
      // error is a static helper method
      // that creates a Result
      return Result.error("Select a date");
    }
  }
  
  @Override
  public LocalDate convertToPresentation(Date objValue, ValueContext context) {
    // Converting to the field type should
    // always succeed, so there is no support for
    // returning an error Result.
    
    LocalDate localDate = null;
    if (objValue != null) {
      if (objValue instanceof java.sql.Date) {
        localDate = ((java.sql.Date) objValue).toLocalDate();
      } else {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = objValue.toInstant();
        localDate = instant.atZone(defaultZoneId).toLocalDate();
      }
    }
    
    return localDate;
  }
}
