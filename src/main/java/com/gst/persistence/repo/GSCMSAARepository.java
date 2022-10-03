package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSAA;
import com.gst.persistence.entity.GSCMSBA;
import com.gst.persistence.key.GSCMSAA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GSCMSAARepository extends JpaRepository<GSCMSAA, GSCMSAA_ID> {
  
  GSCMSAA findByCompany(String company);
  
  @Query(value = "SELECT * FROM CMSBA WHERE COMPANY = ?1 ORDER BY BA001", nativeQuery = true)
  List<GSCMSBA> getGSCMSBAList(String company);
  
  @Query(value = "SELECT count(1) FROM CMSBA WHERE COMPANY = ?1", nativeQuery = true)
  long getGSCMSBACount(String company);
}
