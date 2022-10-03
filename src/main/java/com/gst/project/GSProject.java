package com.gst.project;

import com.dcsplab.common.DLDateUtils;
import com.gst.xmlbeans.msproject.Project;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GSProject extends GSProjectWBS {
  
  // 專案ID
  private String id;
  
  // 專案名稱
  private String name;
  
  private Project msProject;
  
  public GSProject(String id) {
    this.id = id;
  }
  
  public GSProject(Project msProject) {
    importMSProject(msProject);
  }
  
  public static GSProject newProject(String name) {
    String newId = "";
    
    return new GSProject(newId);
  }
  
  private void importMSProject(Project msProject) {
    this.name = msProject.getTitle();
    this.msProject = msProject;
  }
  
  @Override
  public long getChildCount() {
    int childCount = 0;
    
    Project.Tasks tasks = this.msProject.getTasks();
    List<Project.Tasks.Task> taskList = tasks.getTask();
    for (Project.Tasks.Task task : taskList) {
      int level = task.getOutlineLevel().intValue();
      if (level == 1) {
        childCount++;
      }
    }
    return childCount;
  }
  
  @Override
  public boolean hasChildren() {
    Project.Tasks tasks = this.msProject.getTasks();
    List<Project.Tasks.Task> taskList = tasks.getTask();
    for (Project.Tasks.Task task : taskList) {
      int level = task.getOutlineLevel().intValue();
      if (level == 1) {
        return true;
      }
    }
    
    return false;
  }
  
  @Override
  public List<GSProjectWBS> getChildren() {
    ArrayList<GSProjectWBS> children = new ArrayList<>();
    
    Project.Tasks tasks = this.msProject.getTasks();
    List<Project.Tasks.Task> taskList = tasks.getTask();
    for (Project.Tasks.Task task : taskList) {
      int level = task.getOutlineLevel().intValue();
      if (level == 1) {
        GSTask gsTask = new GSTask(task, this.msProject);
        children.add(gsTask);
      }
    }
    
    return children;
  }
  
  @Override
  public String getSequence() {
    return "";
  }
  
  @Override
  public String getStartDisplay() {
    XMLGregorianCalendar clndr = this.msProject.getStartDate();
    Date date = clndr.toGregorianCalendar().getTime();
    
    return DLDateUtils.format(date, DLDateUtils.Resolution.NormalDate);
  }
  
  @Override
  public String getFinishDisplay() {
    XMLGregorianCalendar clndr = this.msProject.getFinishDate();
    Date date = clndr.toGregorianCalendar().getTime();
    return DLDateUtils.format(date, DLDateUtils.Resolution.NormalDate);
  }
  
  @Override
  public String getActualStartDisplay() {
    XMLGregorianCalendar clndr = this.msProject.getStartDate();
    Date date = clndr.toGregorianCalendar().getTime();
    return DLDateUtils.format(date, DLDateUtils.Resolution.NormalDate);
  }
  
  @Override
  public String getActualFinishDisplay() {
    XMLGregorianCalendar clndr = this.msProject.getFinishDate();
    Date date = clndr.toGregorianCalendar().getTime();
    return DLDateUtils.format(date, DLDateUtils.Resolution.NormalDate);
  }
}
