package com.gst.project;

import com.dcsplab.common.DLDateUtils;
import com.gst.xmlbeans.msproject.Project;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GSTask extends GSProjectWBS {
  
  // 工作名稱
  private final String name;
  
  private final Project.Tasks.Task msTask;
  
  private final Project msProject;
  
  private final boolean hasChildren = false;
  
  private final ArrayList<GSProjectWBS> children;
  
  // 工作ID
  private String id;
  
  // 專案ID
  private String projectId;
  
  private int childCount = 0;
  
  public GSTask(Project.Tasks.Task msTask, Project msProject) {
    this.name = msTask.getName();
    this.msTask = msTask;
    this.msProject = msProject;
    
    children = new ArrayList<>();
    
    int targetLevel = this.msTask.getOutlineLevel().intValue() + 1;
    String WBS = this.msTask.getWBS();
    
    Project.Tasks tasks = this.msProject.getTasks();
    List<Project.Tasks.Task> taskList = tasks.getTask();
    for (Project.Tasks.Task task : taskList) {
      int taskLevel = task.getOutlineLevel().intValue();
      String taskWBS = task.getWBS();
      if (targetLevel == taskLevel && taskWBS.startsWith(WBS)) {
        GSTask gsTask = new GSTask(task, msProject);
        children.add(gsTask);
        childCount++;
      }
    }
  }
  
  @Override
  public long getChildCount() {
    return this.childCount;
  }
  
  @Override
  public boolean hasChildren() {
    return this.hasChildren;
  }
  
  @Override
  public List<GSProjectWBS> getChildren() {
    return this.children;
  }
  
  @Override
  public String getSequence() {
    return this.msTask.getWBS();
  }
  
  @Override
  public String getStartDisplay() {
    XMLGregorianCalendar clndr = this.msTask.getStart();
    Date date = clndr.toGregorianCalendar().getTime();
    return DLDateUtils.format(date, DLDateUtils.Resolution.NormalDate);
  }
  
  @Override
  public String getFinishDisplay() {
    XMLGregorianCalendar clndr = this.msTask.getFinish();
    Date date = clndr.toGregorianCalendar().getTime();
    return DLDateUtils.format(date, DLDateUtils.Resolution.NormalDate);
  }
  
  @Override
  public String getActualStartDisplay() {
    XMLGregorianCalendar clndr = this.msTask.getStart();
    Date date = clndr.toGregorianCalendar().getTime();
    return DLDateUtils.format(date, DLDateUtils.Resolution.NormalDate);
  }
  
  @Override
  public String getActualFinishDisplay() {
    XMLGregorianCalendar clndr = this.msTask.getFinish();
    Date date = clndr.toGregorianCalendar().getTime();
    return DLDateUtils.format(date, DLDateUtils.Resolution.NormalDate);
  }
}
