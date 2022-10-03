package com.gst.mes.pmc;

import com.dcsplab.common.DLDateUtils;
import com.dcsplab.common.DLDateUtils.Resolution;
import com.dcsplab.vdui.UIConstants;
import com.gst.domain.GSPMCManufactureOrder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

import java.util.Date;

public class GSPMCGridHelper {
  
  public static final String TA006DisplayColor = "#007979"; // 品號
  
  public static final String TA015DisplayColor = "#0000E3"; // 預計生產量
  
  public static final String TA017DisplayColor = "#00A600"; // 已生產數量
  
  private static GSGridCellDiv getCellComponent(GSPMCManufactureOrder data) {
    /*
    if (data.isUrgentReported()) {
      display.addClassName("gst-grid-cell-urgentreported");
    } else if (data.isUrgent()) {
      display.addClassName("gst-grid-cell-urgent");
    } else {
      display.addClassName("gst-grid-cell-normal");
    }*/
    
    return new GSGridCellDiv();
  }
  
  private static String getCommonDateString(String rawValue) {
    String dateString = "";
    if (rawValue != null && !rawValue.trim().isEmpty()) {
      try {
        dateString = rawValue.trim();
        Date date = DLDateUtils.parse(dateString, Resolution.SimpleDate);
        dateString = DLDateUtils.format(date, Resolution.NormalDate);
      } catch (Exception e) {
      }
    }
    
    return dateString;
  }
  
  /*
   * value: a String represents date or datetime
   */
  public static Component _getCommonDateDisplay(String value) {
    Label display = new Label("");
    display.setSizeUndefined();
    
    display.setText(getCommonDateString(value));
    return display;
  }
  
  /*
   * TA001 製令單別
   */
  public static Component getTA001Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    display.setText(data.getManufactureOrderTypeId());
    return display;
  }
  
  /*
   * TA002 製令單號
   */
  public static Component getTA002Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    display.setText(data.getManufactureOrderId());
    return display;
  }
  
  /*
   * TA021 生產單位
   */
  public static Component getTA021Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    display.setText(data.getProductLine());
    return display;
  }
  
  /*
   * TA006 產品品號
   */
  public static Component getTA006Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    display.setText(data.getPartNumber());
    
    if (!data.isUrgent()) {
      display.getStyle().set("color", TA006DisplayColor);
    }
    
    display.getStyle().set("font-weight", "bold");
    display.getStyle().set("font-size", "14px");
    
    return display;
  }
  
  /*
   * TA029 備註
   */
  public static Component getTA029Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    display.setText(data.getRemark());
    return display;
  }
  
  /*
   * TA034 品名
   */
  public static Component getTA034Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    display.setText(data.getProductName());
    return display;
  }
  
  /*
   * TA035 規格
   */
  public static Component getTA035Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    display.setText(data.getProductSpec());
    return display;
  }
  
  /*
   * 1.未生產,2.已發料,3.生產中
   */
  public static Component getTA011Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    
    String statusCode = data.getStatusCode().trim();
    String statusText = "未知狀態";
    String statusColor = UIConstants.PMC_LINESTATUS_UNKNOWN;
    
    if (data.isUrgent()) {
      switch (statusCode) {
        case "1":
          statusText = "未生產";
          break;
        case "2":
          statusText = "已發料";
          break;
        case "3":
          statusText = "生產中";
          break;
      }
    } else {
      switch (statusCode) {
        case "1":
          statusText = "未生產";
          statusColor = UIConstants.PMC_LINESTATUS_INCOMING;
          break;
        case "2":
          statusText = "已發料";
          statusColor = UIConstants.PMC_LINESTATUS_STANDBY;
          break;
        case "3":
          statusText = "生產中";
          statusColor = UIConstants.PMC_LINESTATUS_INPROCESS;
          break;
      }
      
      display.getStyle().set("color", statusColor);
    }
    
    display.setText(statusText);
    
    return display;
  }
  
  /*
  預計產量
  */
  public static Component getTA015Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    String materialUnit = data.getMaterialUnit();
    String estimateAmount = "" + data.getEstimateAmount();
    
    display.setText(estimateAmount + "(" + materialUnit + ")");
    
    if (!data.isUrgent()) {
      display.getStyle().set("color", TA015DisplayColor);
    }
    
    display.getStyle().set("font-weight", "bold");
    display.getStyle().set("font-size", "14px");
    display.getStyle().set("padding-right", "2px");
    display.getStyle().set("text-align", "right");
    
    return display;
  }
  
  /*
  已生產數
   */
  public static Component getTA017Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    
    String materialUnit = data.getMaterialUnit();
    String finishedAmount = "" + data.getFinishedAmount();
    display.setText(finishedAmount + "(" + materialUnit + ")");
    
    if (!data.isUrgent()) {
      display.getStyle().set("color", TA017DisplayColor);
    }
    
    display.getStyle().set("font-weight", "bold");
    display.getStyle().set("font-size", "13px");
    display.getStyle().set("text-align", "right");
    display.getStyle().set("padding-right", "2px");
    
    return display;
  }
  
  /*
   * 2020/12/19  新增「未審核數量」欄位
   */
  public static Component getMinusTA017Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);

    /*
    GSMESTARepository stockRepository = GSContext.getApplication().getStockRepository();
    Double ra =
        stockRepository.getUnReviewedAmount(
            data.getManufactureOrderTypeId().trim(), data.getManufactureOrderId().trim());*/
    
    double value = data.getUnreviewedAmount();
    
    String sTA007 = data.getMaterialUnit();
    display.setText("" + value + "(" + sTA007 + ")");
    
    if (!data.isUrgent()) {
      display.getStyle().set("color", UIConstants.PMC_LINESTATUS_HOLD);
    }
    
    display.getStyle().set("font-weight", "bold");
    display.getStyle().set("font-size", "13px");
    display.getStyle().set("text-align", "right");
    display.getStyle().set("padding-right", "2px");
    
    return display;
  }
  
  /*
  預計生產包裝量
   */
  public static Component getTA045Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    
    String estimatePackAmount = "" + data.getEstimatePackAmount();
    String packingUnit = data.getPackingUnit();
    
    display.setText(estimatePackAmount + "(" + packingUnit + ")");
    
    display.getStyle().set("text-align", "right");
    display.getStyle().set("padding-right", "2px");
    
    return display;
  }
  
  /*
  目前生產狀態
   */
  public static Component getTA092Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    
    // 前次加工時間
    Date lastStartTime = data.getLastStartTime();
    
    // 目前開工時間
    Date startTime = data.getStartTime();
    
    String statusText;
    String statusColor;
    if (data.isUrgent()) {
      if (startTime != null) {
        statusText = "生產中";
      } else {
        if (lastStartTime != null) {
          statusText = "暫停";
        } else {
          statusText = "未生產";
        }
      }
    } else {
      if (startTime != null) {
        statusText = "生產中";
        statusColor = UIConstants.PMC_LINESTATUS_INPROCESS;
      } else {
        if (lastStartTime != null) {
          statusText = "暫停";
          statusColor = UIConstants.PMC_LINESTATUS_HOLD;
        } else {
          statusText = "未生產";
          statusColor = UIConstants.PMC_LINESTATUS_INCOMING;
        }
      }
      
      display.getStyle().set("color", statusColor);
    }
    
    display.setText(statusText);
    
    return display;
  }
  
  /*
  預計開工日
   */
  public static Component getTA009Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    String value = getCommonDateString(data.getEstimateStartDate().trim());
    display.setText(value);
    
    return display;
  }
  
  /*
  預計完工日
   */
  public static Component getTA010Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    String value = getCommonDateString(data.getEstimateFinishDate().trim());
    display.setText(value);
    
    return display;
  }
  
  /*
  實際開工日
   */
  public static Component getTA012Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    String value = getCommonDateString(data.getActualStartDate().trim());
    display.setText(value);
    
    return display;
  }
  
  /*
  生產順序
   */
  public static Component getTA501Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    
    Double d = data.getWorkingSequence();
    if (d == null) {
      return display;
    }
    
    display.setText("" + d.intValue());
    if (!data.isUrgent()) {
      if (d.intValue() < 888) {
        display.getStyle().set("color", UIConstants.PMC_LINESTATUS_INPROCESS);
      } else if (d.intValue() == 999) {
        display.getStyle().set("color", UIConstants.PMC_LINESTATUS_HOLD);
      } else if (d.intValue() == 888) {
        display.getStyle().set("color", UIConstants.PMC_LINESTATUS_INCOMING);
      }
    }
    
    display.getStyle().set("text-align", "center");
    
    return display;
  }
  
  /*
  前次加工時間
   */
  public static Component getTA502Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    
    Date lastStartTime = data.getLastStartTime();
    if (lastStartTime != null) {
      String value = DLDateUtils.format(lastStartTime, Resolution.Minute);
      display.setText(value);
    }
    
    return display;
  }
  
  /*
  目前開工時間
   */
  public static Component getTA503Display(GSPMCManufactureOrder data) {
    Div display = getCellComponent(data);
    
    Date d = data.getStartTime();
    if (d != null) {
      String value = DLDateUtils.format(d, Resolution.Minute);
      display.setText(value);
    }
    
    return display;
  }
  
  public static class GSGridCellDiv extends Div {
    
    private static final long serialVersionUID = 11364293794008799L;
    
    public GSGridCellDiv() {
      setSizeUndefined();
      super.setText("-");
      // getStyle().set("height", "18px");
    }
    
    public void setText(String value) {
      if (value != null && !value.trim().isEmpty()) {
        super.setText(value);
      } else {
        super.setText("-");
      }
    }
  }
}
