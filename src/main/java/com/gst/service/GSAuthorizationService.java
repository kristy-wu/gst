package com.gst.service;

import com.gst.context.GSContext;
import com.gst.domain.*;
import com.gst.persistence.entity.*;
import com.gst.persistence.key.GSADMAA_ID;
import com.gst.persistence.key.GSADMCA_ID;
import com.gst.persistence.key.GSADMEA_ID;
import com.gst.persistence.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GSAuthorizationService {
  
  @Autowired
  private GSADMCARepository roleRepository;
  
  @Autowired
  private GSADMAARepository moduleRepository;
  
  @Autowired
  private GSADMDARepository roleAccessRepository;
  
  @Autowired
  private GSADMBARepository funcRepository;
  
  @Autowired
  private GSADMEARepository functionUserRepository;
  
  @Autowired
  private GSADMEBRepository functionAccessRepository;
  
  public GSAuthorizationService() {
  }
  
  public List<GSModule> getModuleList(String companyId) {
    List<GSModule> modules = new ArrayList<>();
    List<GSADMAA> gsadmaaList = moduleRepository.findByCompanyOrderByAA001(companyId);
    for (GSADMAA gsadmaa : gsadmaaList) {
      GSModule module = populateModule(gsadmaa);
      modules.add(module);
    }
    return modules;
  }
  
  public GSModule getModule(String companyId, String moduleId) {
    GSADMAA_ID id = new GSADMAA_ID();
    id.setCompany(companyId);
    id.setAA001(moduleId);
    GSADMAA gsadmaa = moduleRepository.findById(id).orElse(null);
    return populateModule(gsadmaa);
  }
  
  public void updateModule(GSMember member, GSModule module) {
    GSADMAA gsadmaa = persistModule(module);
    gsadmaa.setModifiedBy(member);
    moduleRepository.save(gsadmaa);
  }
  
  private GSModule populateModule(GSADMAA gsadmaa) {
    if (gsadmaa == null) {
      return null;
    }
    GSModule module = new GSModule(this);
    module.setBaseAttribute(gsadmaa);
    module.setId(gsadmaa.getAA001());
    module.setName(gsadmaa.getAA002());
    module.setRemark(gsadmaa.getAA003());
    
    return module;
  }
  
  private GSADMAA persistModule(GSModule module) {
    GSADMAA gsadmaa = new GSADMAA();
    gsadmaa.setBaseAttribute(module);
    gsadmaa.setAA001(module.getId());
    gsadmaa.setAA002(module.getName());
    gsadmaa.setAA003(module.getRemark());
    
    return gsadmaa;
  }
  
  public List<GSSystemFunction> getModuleFunctions(GSModule module) {
    ArrayList<GSSystemFunction> functions = new ArrayList<>();
    
    String moduleId = module.getId();
    List<GSADMBA> funcs = funcRepository.findByBA004(moduleId);
    for (GSADMBA obj : funcs) {
      GSSystemFunction func = populateSystemFunction(obj);
      functions.add(func);
    }
    
    return functions;
  }
  
  public List<GSSystemFunction> getSystemFunctionList(String companyId) {
    List<GSSystemFunction> functions = new ArrayList<>();
    List<GSADMBA> gsadmbaList = funcRepository.findByCompanyOrderByBA001(companyId);
    for (GSADMBA gsadmba : gsadmbaList) {
      GSSystemFunction function = populateSystemFunction(gsadmba);
      functions.add(function);
    }
    return functions;
  }
  
  public void updateSystemFunction(GSMember member, GSSystemFunction function) {
    GSADMBA gsadmba = persistSystemFunction(function);
    gsadmba.setModifiedBy(member);
    funcRepository.save(gsadmba);
  }
  
  public GSSystemFunction getSystemFunction(String company, String funcId) {
    GSADMBA gsadmba = funcRepository.findByCompanyAndBA001(company, funcId);
    return populateSystemFunction(gsadmba);
  }
  
  private GSSystemFunction populateSystemFunction(GSADMBA gsadmba) {
    GSSystemFunction function = new GSSystemFunction(this);
    function.setBaseAttribute(gsadmba);
    function.setId(gsadmba.getBA001());
    function.setName(gsadmba.getBA002());
    function.setType(gsadmba.getBA003());
    function.setModule(gsadmba.getBA004());
    function.setRemark(gsadmba.getBA005());
    return function;
  }
  
  private GSADMBA persistSystemFunction(GSSystemFunction function) {
    GSADMBA gsadmba = new GSADMBA();
    gsadmba.setBaseAttribute(function);
    gsadmba.setBA001(function.getId());
    gsadmba.setBA002(function.getName());
    gsadmba.setBA003(function.getType());
    gsadmba.setBA004(function.getModule());
    gsadmba.setBA005(function.getRemark());
    
    return gsadmba;
  }
  
  public List<GSRole> getRoleList(String companyId) {
    ArrayList<GSRole> roleList = new ArrayList<>();
    
    List<GSADMCA> list = roleRepository.findAllByCompanyOrderByCA001(companyId);
    for (GSADMCA obj : list) {
      GSRole role = populateRole(obj);
      roleList.add(role);
    }
    
    return roleList;
  }
  
  public GSRole createRole(GSMember member) {
    GSADMCA gsadmca = new GSADMCA(member);
    gsadmca.setCA001("");
    gsadmca.setCA002("");
    gsadmca.setCA003("");
    return populateRole(gsadmca);
  }
  
  public void updateRole(GSMember member, GSRole role) {
    GSADMCA gsadmca = persistRole(role);
    gsadmca.setModifiedBy(member);
    roleRepository.save(gsadmca);
  }
  
  public void deleteRole(GSRole role) {
    GSADMCA_ID id = new GSADMCA_ID();
    id.setCompany(role.getCompanyId());
    id.setCA001(role.getId());
    roleRepository.deleteById(id);
  }
  
  private GSRole populateRole(GSADMCA gsadmca) {
    GSRole role = new GSRole(this);
    role.setBaseAttribute(gsadmca);
    role.setId(gsadmca.getCA001());
    role.setName(gsadmca.getCA002());
    role.setRemark(gsadmca.getCA003());
    return role;
  }
  
  private GSADMCA persistRole(GSRole role) {
    GSADMCA gsadmca = new GSADMCA();
    gsadmca.setBaseAttribute(role);
    gsadmca.setCA001(role.getId());
    gsadmca.setCA002(role.getName());
    gsadmca.setCA003(role.getRemark());
    
    return gsadmca;
  }
  
  public List<GSRoleAccess> getRoleAccessList(GSRole role) {
    ArrayList<GSRoleAccess> accessList = new ArrayList<>();
    List<GSADMDA> daList =
      roleAccessRepository.findByCompanyAndDA001OrderByDA002(role.getCompanyId(), role.getId());
    for (GSADMDA obj : daList) {
      accessList.add(populateRoleAccess(obj));
    }
    return accessList;
  }
  
  public GSRoleAccess transitionFunctionAccess(GSRole role, GSSystemFunction function) {
    GSRoleAccess access = new GSRoleAccess(this);
    access.setBaseAttribute(persistRole(role));
    access.setRoleId(role.getId());
    access.setFunctionId(function.getId());
    access.setAccessString(function.buildAccString());
    access.setRemark("");
    
    return access;
  }
  
  public void updateRoleAccess(GSMember member, GSRoleAccess gsRoleAccess) {
    GSADMDA gsadmda = persistRoleAccess(gsRoleAccess);
    gsadmda.setModifiedBy(member);
    roleAccessRepository.save(gsadmda);
  }
  
  private GSRoleAccess populateRoleAccess(GSADMDA gsadmda) {
    GSRoleAccess access = new GSRoleAccess(this);
    access.setBaseAttribute(gsadmda);
    access.setRoleId(gsadmda.getDA001());
    access.setFunctionId(gsadmda.getDA002());
    access.setAccessString(gsadmda.getDA003());
    access.setRemark(gsadmda.getDA004());
    return access;
  }
  
  private GSADMDA persistRoleAccess(GSRoleAccess access) {
    GSADMDA gsadmda = new GSADMDA();
    gsadmda.setBaseAttribute(access);
    gsadmda.setDA001(access.getRoleId());
    gsadmda.setDA002(access.getFunctionId());
    gsadmda.setDA003(access.getAccessString());
    gsadmda.setDA004(access.getRemark());
    return gsadmda;
  }
  
  public GSMemberAccess createMemberAccess(GSMember member) {
    GSADMEB gsadmeb = new GSADMEB(member);
    return populateMemberAccess(gsadmeb);
  }
  
  public List<GSMemberAccess> getMemberAccessList(GSMember member) {
    ArrayList<GSMemberAccess> accessList = new ArrayList<>();
    
    List<GSADMEB> list =
      functionAccessRepository.findByCompanyAndEB001(member.getCompanyId(), member.getId());
    
    for (GSADMEB obj : list) {
      accessList.add(populateMemberAccess(obj));
    }
    return accessList;
  }
  
  public boolean hasAdministratorAccess(GSMember member) {
    if(member==null) {
      return false;
    }

    GSADMEA_ID id = new GSADMEA_ID();
    id.setCompany(member.getCompanyId());
    id.setEA001(member.getId());
    try {
      if (functionUserRepository.findById(id).isPresent()) {
        GSADMEA obj = functionUserRepository.findById(id).get();
        return "Y".equals(obj.getEA002().trim());
      } else {
        GSMember contextUser = GSContext.getCurrentMember();
        GSADMEA obj = new GSADMEA();
        obj.setBaseAttribute(member);
        obj.setCreator(contextUser.getId());
        obj.setEA001(member.getId());
        obj.setEA002("N");
        functionUserRepository.save(obj);
        
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  
  public void updateMemberAccess(GSMember contextUser, GSMember member) {
    GSADMEA gsadmea = new GSADMEA();
    gsadmea.setBaseAttribute(contextUser);
    gsadmea.setModifiedBy(contextUser);
    gsadmea.setEA001(member.getId());
    gsadmea.setEA002(member.hasManagerAccess() ? "Y" : "N");
    functionUserRepository.save(gsadmea);
    
    List<GSADMEB> itemsToDelete =
      functionAccessRepository.findByCompanyAndEB001(member.getCompanyId(), member.getId());
    functionAccessRepository.deleteAll(itemsToDelete);
    
    List<GSMemberAccess> list = member.getAccessList();
    ArrayList<GSADMEB> itemsToAdd = new ArrayList<>();
    for (GSMemberAccess access : list) {
      GSADMEB dbObj = persistMemberAccess(access);
      dbObj.setModifiedBy(contextUser);
      itemsToAdd.add(dbObj);
    }
    functionAccessRepository.saveAll(itemsToAdd);
  }
  
  public void updateAccessListOfRole(GSMember member, GSRole role) {
    ArrayList<GSADMDA> list = new ArrayList<>();
    List<GSRoleAccess> accessList = role.getAccessList();
    roleAccessRepository.deleteByCompanyAndDA001(role.getCompanyId(), role.getId());
    
    for (GSRoleAccess access : accessList) {
      GSADMDA obj = new GSADMDA(member);
      obj.setModifiedBy(member);
      obj.setDA001(role.getId());
      obj.setDA002(access.getFunctionId());
      obj.setDA003(access.getAccessString());
      obj.setDA004("");
      list.add(obj);
    }
    
    roleAccessRepository.saveAll(list);
  }
  
  private GSMemberAccess populateMemberAccess(GSADMEB gsadmeb) {
    GSMemberAccess memberAccess = new GSMemberAccess(this);
    memberAccess.setBaseAttribute(gsadmeb);
    memberAccess.setMemberId(gsadmeb.getEB001());
    memberAccess.setFunctionId(gsadmeb.getEB002());
    memberAccess.setAccessString(gsadmeb.getEB003());
    memberAccess.setRemark(gsadmeb.getEB004());
    
    memberAccess.populateAccess(gsadmeb.getEB003());
    
    return memberAccess;
  }
  
  private GSADMEB persistMemberAccess(GSMemberAccess access) {
    GSADMEB gsadmeb = new GSADMEB();
    gsadmeb.setBaseAttribute(access);
    gsadmeb.setEB001(access.getMemberId());
    gsadmeb.setEB002(access.getFunctionId());
    gsadmeb.setEB003(access.getAccessString());
    gsadmeb.setEB004("");
    
    return gsadmeb;
  }
}
