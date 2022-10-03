package com.dcsplab.vdui;

public class UIConstants {
  
  public static final String DEFAULT_LINE_HEIGHT = "25px";
  
  public static final String DEFAULT_FONT_SIZE = "14px";
  
  public static final String SMALL_FONT_SIZE = "12px";
  
  public static final String DEFAULT_ICON_ENABLED_COLOR = "#00b4f0";
  
  public static final String DEFAULT_ICON_DISABLED_COLOR = "#e4e9e7";
  
  public static final String DEFAULT_GREEN_COLOR = "#00BB00";
  
  public static final String DEFAULT_RED_COLOR = "#CE0000";
  
  public static final String DEFAULT_BORDER_COLOR = "#00b4f0";
  
  public static final String PMC_LINESTATUS_UNKNOWN = "red"; // 未生產
  
  public static final String PMC_LINESTATUS_INPROCESS = "#0000E3"; // 生產中
  
  public static final String PMC_LINESTATUS_HOLD = "#FF5809"; // 暫停
  
  public static final String PMC_LINESTATUS_COMPLETE = "#00A600"; // 結案
  
  public static final String PMC_LINESTATUS_STANDBY = "#1ae917"; // 已發料
  
  public static final String PMC_LINESTATUS_INCOMING = "grey"; // 未生產
  
  public static final String PMC_URGENTLINE_FOREGROUND = "white";
  
  public static final String PMC_URGENTLINE_BACKGROUND = "red";
  
  public static final String PMC_URGENTLINE_BACKGROUND_HOLD = "#FF5809";
  
  public static final String BUTTON_HEIGHT = DEFAULT_LINE_HEIGHT;
  
  public static final String BUTTON_FONT_SIZE = DEFAULT_FONT_SIZE;
  
  public static final String TEXTFIELD_HEIGHT = DEFAULT_LINE_HEIGHT;
  
  public static final String TEXTFIELD_FONT_SIZE = "12px";
  
  // "新增", "查詢", "修改", "刪除", "確認", "取消確認", "作廢"
  public static final String ACCESS_CREATE = "新增";
  
  public static final String ACCESS_SEARCH = "查詢";
  
  public static final String ACCESS_MODIFY = "修改";
  
  public static final String ACCESS_DELETE = "刪除";
  
  public static final String ACCESS_CONFIRM = "確認";
  
  public static final String ACCESS_CANCEL = "取消確認";
  
  public static final String ACCESS_OBSOLETE = "作廢";
  
  public static final String[] ACCESS_LIST = {
    ACCESS_CREATE,
    ACCESS_SEARCH,
    ACCESS_MODIFY,
    ACCESS_DELETE,
    ACCESS_CONFIRM,
    ACCESS_CANCEL,
    ACCESS_OBSOLETE
  };
  
  public static final String PMCDataChangeNotification = "偵測到製令異動, 已更新表格！";
  
  public static final String PMCReviewNotification = "有新的報工資料";
}
