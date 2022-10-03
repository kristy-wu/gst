package com.gst.persistence.repo;

import com.gst.persistence.entity.GSADMSY;
import com.gst.persistence.key.GSADMSY_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GSADMSYRepository extends JpaRepository<GSADMSY, GSADMSY_ID> {

  /*
  error happend !?
  No Dialect mapping for JDBC type: -15
   */
  @Query(value = "SELECT SY002 FROM ADMSY WHERE SY001 = ?1", nativeQuery = true)
  String findSetting(String sy001);

  GSADMSY findBySY001(String SY001);
}
