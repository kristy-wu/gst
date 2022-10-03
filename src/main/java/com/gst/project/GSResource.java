package com.gst.project;

import com.gst.xmlbeans.msproject.Project;

public class GSResource {
  
  private final String name;
  
  private String id;
  
  private String taskId;
  
  private String projectId;
  
  public GSResource(String name) {
    this.name = name;
  }
  
  public GSResource(Project.Resources.Resource msResource) {
    this.name = msResource.getName();
  }
}
