package com.gst.persistence.repo;

import com.gst.persistence.entity.GSADMDA;
import com.gst.persistence.key.GSADMDA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GSADMDARepository extends JpaRepository<GSADMDA, GSADMDA_ID> {
  
  List<GSADMDA> findByCompanyAndDA001OrderByDA002(String company, String da001);
  
  @Modifying
  @Transactional
  void deleteByCompanyAndDA001(String companyId, String roleId);
  
  @Modifying
  @Transactional
  void deleteByCompanyAndDA001AndDA002(String companyId, String roleId, String functionId);
}
