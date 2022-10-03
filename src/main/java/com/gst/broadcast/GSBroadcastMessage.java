package com.gst.broadcast;

import java.io.Serializable;

public class GSBroadcastMessage implements Serializable {
  
  /*public static final String PMCType_Scheduler = "GSTScheduler";
  public static final String PMCType_Production = "Production";
  public static final String PMCType_PMCReview = "PMCReview";*/
  private static final long serialVersionUID = -2868904990123633435L;
  private String message;
  
  private Object[] content;
  
  public GSBroadcastMessage() {
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public Object[] getContent() {
    return content;
  }
  
  public void setContent(Object... content) {
    this.content = content;
  }
}
