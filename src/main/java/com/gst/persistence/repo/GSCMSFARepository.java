package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSFA;
import com.gst.persistence.key.GSCMSFA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GSCMSFARepository extends JpaRepository<GSCMSFA, GSCMSFA_ID> {
  
  List<GSCMSFA> findByCompany(String companyId);
}
