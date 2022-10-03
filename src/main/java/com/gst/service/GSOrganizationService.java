package com.gst.service;

import com.gst.context.GSContext;
import com.gst.domain.GSCompany;
import com.gst.domain.GSDepartment;
import com.gst.domain.GSMember;
import com.gst.persistence.entity.GSCMSAA;
import com.gst.persistence.entity.GSCMSBA;
import com.gst.persistence.entity.GSCMSCA;
import com.gst.persistence.entity.GSCMSCB;
import com.gst.persistence.key.GSCMSCA_ID;
import com.gst.persistence.repo.GSCMSAARepository;
import com.gst.persistence.repo.GSCMSBARepository;
import com.gst.persistence.repo.GSCMSCARepository;
import com.gst.persistence.repo.GSCMSCBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class GSOrganizationService {
  
  @Autowired
  private GSCMSAARepository companyRepository;
  
  @Autowired
  private GSCMSBARepository departmentRepository;
  
  @Autowired
  private GSCMSCARepository memberRepository;
  
  @Autowired
  private GSCMSCBRepository personDeptRepository;
  
  public GSOrganizationService() {
  }
  
  public GSCompany getCompany(String companyId) {
    GSCMSAA entity = companyRepository.findByCompany(companyId);
    return populateCompany(entity);
  }
  
  private GSCompany populateCompany(GSCMSAA gscmsaa) {
    GSCompany company = new GSCompany(this);
    company.setBaseAttribute(gscmsaa);
    company.setId(gscmsaa.getAA001());
    company.setShortName(gscmsaa.getAA002());
    company.setName(gscmsaa.getAA003());
    company.setDbCategory(gscmsaa.getAA004());
    return company;
  }
  
  public List<GSDepartment> getDepartmentList(GSCompany company) {
    List<GSCMSBA> list = departmentRepository.findByCompany(company.getCompanyId());
    ArrayList<GSDepartment> deptList = new ArrayList<>();
    for (GSCMSBA obj : list) {
      GSDepartment dept = populateDepartment(obj);
      deptList.add(dept);
    }
    return deptList;
  }
  
  public GSDepartment getDepartment(String companyId, String deptId) {
    GSCMSBA obj = departmentRepository.findByCompanyAndBA001(companyId, deptId);
    if (obj == null) {
      return null;
    }
    return populateDepartment(obj);
  }
  
  public GSDepartment createDepartment(GSMember member) {
    GSCMSBA gscmsba = new GSCMSBA(member);
    gscmsba.setBA001("");
    gscmsba.setBA002("");
    gscmsba.setBA905(null);
    
    return populateDepartment(gscmsba);
  }
  
  public void updateDepartment(GSMember member, GSDepartment dept) {
    GSCMSBA gscmsba = persistDepartment(dept);
    gscmsba.setModifiedBy(member);
    departmentRepository.save(gscmsba);
  }
  
  public void deleteDepartment(GSDepartment dept) {
    GSCMSBA gscmsba = persistDepartment(dept);
    departmentRepository.delete(gscmsba);
  }
  
  private GSDepartment populateDepartment(GSCMSBA gscmsba) {
    GSDepartment dept = new GSDepartment(this);
    dept.setBaseAttribute(gscmsba);
    dept.setId(gscmsba.getBA001());
    dept.setName(gscmsba.getBA002());
    dept.setLastFinishTime(gscmsba.getBA905());
    
    return dept;
  }
  
  private GSCMSBA persistDepartment(GSDepartment dept) {
    GSCMSBA gscmsba = new GSCMSBA();
    gscmsba.setBaseAttribute(dept);
    gscmsba.setBA001(dept.getId());
    gscmsba.setBA002(dept.getName());
    gscmsba.setBA905(dept.getLastFinishTime());
    
    return gscmsba;
  }
  
  public List<GSMember> getMemberList(GSDepartment department) {
    List<GSCMSCA> list =
      memberRepository.findByCompanyAndCA003(department.getCompanyId(), department.getId());
    ArrayList<GSMember> memberList = new ArrayList<>();
    for (GSCMSCA obj : list) {
      GSMember member = populateMember(obj);
      memberList.add(member);
    }
    return memberList;
  }
  
  public int getMemberCount(GSDepartment department) {
    return memberRepository.countByCompanyAndCA003(department.getCompanyId(), department.getId());
  }
  
  public List<GSMember> getCompanyMemberList(String companyId) {
    List<GSMember> members = new ArrayList<>();
    List<GSCMSCA> dataList = memberRepository.findByCompanyOrderByCA001(companyId);
    
    GSAuthorizationService authService = GSContext.getApplication().getAuthorizationService();
    for (GSCMSCA data : dataList) {
      GSMember member = populateMember(data);
      boolean hasMgrAccess = authService.hasAdministratorAccess(member);
      member.setManagerAccess(hasMgrAccess);
      
      List<GSCMSCB> existedDept =
        personDeptRepository.findByCompanyAndCB001(member.getCompanyId(), member.getId());
      List<GSDepartment> assignedDepts = new ArrayList<>();
      for (GSCMSCB gscmscb : existedDept) {
        GSDepartment department = getDepartment(gscmscb.getCompany(), gscmscb.getCB002());
        assignedDepts.add(department);
      }
      member.setRelatedDepartments(assignedDepts);
      
      members.add(member);
    }
    return members;
  }
  
  public GSMember getMember(String companyId, String memberId) {
    GSCMSCA obj = memberRepository.findByCompanyAndCA001(companyId, memberId);
    if (obj != null) {
      return populateMember(obj);
    }
    return null;
  }
  
  public GSMember createMember(GSMember member) {
    GSCMSCA cmsca = new GSCMSCA(member);
    cmsca.setCA001("");
    cmsca.setCA002("");
    cmsca.setCA003("");
    cmsca.setCA004("");
    cmsca.setCA005(Calendar.getInstance().getTime());
    cmsca.setCA006(null);
    return populateMember(cmsca);
  }
  
  public void updateMember(GSMember contextUser, GSMember member) {
    // save member info
    // clear existed related depts
    // save selected related deptys
    // update admin flag
    
    GSCMSCA gscmsca = persistMember(member);
    gscmsca.setModifiedBy(contextUser);
    memberRepository.save(gscmsca);
    
    List<GSCMSCB> existedDept =
      personDeptRepository.findByCompanyAndCB001(member.getCompanyId(), member.getId());
    personDeptRepository.deleteAll(existedDept);
    
    List<GSDepartment> relatedDepts = member.getRelatedDepartments();
    List<GSCMSCB> gscmscbList = new ArrayList<>();
    for (GSDepartment dept : relatedDepts) {
      GSCMSCB gscmscb = new GSCMSCB(contextUser);
      gscmscb.setCB001(member.getId());
      gscmscb.setCB002(dept.getId());
      gscmscb.setModifiedBy(contextUser);
      gscmscbList.add(gscmscb);
    }
    personDeptRepository.saveAll(gscmscbList);
  }
  
  public void deleteMember(GSMember member) {
    GSCMSCA_ID id = new GSCMSCA_ID();
    id.setCompany(member.getCompanyId());
    id.setCA001(member.getId());
    memberRepository.deleteById(id);
    
    List<GSCMSCB> gscmscbList =
      personDeptRepository.findByCompanyAndCB001(member.getCompanyId(), member.getId());
    personDeptRepository.deleteAll(gscmscbList);
  }
  
  public List<GSDepartment> getAssignedDepts(GSMember member) {
    ArrayList<GSDepartment> deptList = new ArrayList<>();
    
    List<GSCMSBA> list =
      departmentRepository.findAssignedDepartmentByMemberId(
        member.getCompanyId(), member.getId());
    
    for (GSCMSBA obj : list) {
      GSDepartment dept = populateDepartment(obj);
      deptList.add(dept);
    }
    
    return deptList;
  }
  
  private GSMember populateMember(GSCMSCA gscmsca) {
    GSMember member = new GSMember(this);
    member.setBaseAttribute(gscmsca);
    member.setId(gscmsca.getCA001());
    member.setName(gscmsca.getCA002());
    member.setDeptId(gscmsca.getCA003());
    member.setPassword(gscmsca.getCA004());
    member.setActiveDate(gscmsca.getCA005());
    member.setObsoleteDate(gscmsca.getCA006());
    
    member.setRelatedDepartments(this.getAssignedDepts(member));
    
    return member;
  }
  
  private GSCMSCA persistMember(GSMember member) {
    GSMember contextUser = GSContext.getCurrentMember();
    
    GSCMSCA gscmsca = new GSCMSCA();
    gscmsca.setBaseAttribute(member);
    gscmsca.setModifier(contextUser.getId());
    gscmsca.setFlag(member.getFlag() + 1);
    gscmsca.setCA001(member.getId());
    gscmsca.setCA002(member.getName());
    gscmsca.setCA003(member.getDeptId());
    gscmsca.setCA004(member.getPassword());
    gscmsca.setCA005(member.getActiveDate());
    gscmsca.setCA006(member.getObsoleteDate());
    
    return gscmsca;
  }
}
