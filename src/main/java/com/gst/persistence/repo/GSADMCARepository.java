package com.gst.persistence.repo;

import com.gst.persistence.entity.GSADMCA;
import com.gst.persistence.key.GSADMCA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GSADMCARepository extends JpaRepository<GSADMCA, GSADMCA_ID> {
  
  List<GSADMCA> findAllByCompanyOrderByCA001(String companyId);
}
