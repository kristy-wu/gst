package com.gst.persistence.repo;

import com.gst.persistence.entity.GSADMAA;
import com.gst.persistence.key.GSADMAA_ID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GSADMAARepository extends JpaRepository<GSADMAA, GSADMAA_ID> {
  List<GSADMAA> findByCompanyOrderByAA001(String companyId);
}
