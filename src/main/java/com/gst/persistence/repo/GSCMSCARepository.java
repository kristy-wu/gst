package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSCA;
import com.gst.persistence.key.GSCMSCA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GSCMSCARepository extends JpaRepository<GSCMSCA, GSCMSCA_ID> {
  
  GSCMSCA findByCA001(String id);
  
  List<GSCMSCA> findByCompanyOrderByCA001(String companyId);
  
  GSCMSCA findByCompanyAndCA001(String companyId, String memId);
  
  List<GSCMSCA> findByCompanyAndCA003(String companyId, String deptId);
  
  int countByCompanyAndCA003(String companyId, String deptId);
}
