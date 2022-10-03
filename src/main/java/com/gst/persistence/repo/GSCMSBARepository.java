package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSBA;
import com.gst.persistence.key.GSCMSBA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GSCMSBARepository extends JpaRepository<GSCMSBA, GSCMSBA_ID> {
  
  @Query(
    value =
      "SELECT * FROM CMSBA WHERE BA001 NOT IN (" + "SELECT CB002 FROM CMSCB WHERE CB001 = ?1)",
    nativeQuery = true)
  List<GSCMSBA> findUnAssignedDepartmentByPersonId(String id);
  
  @Query(
    value =
      "SELECT * FROM CMSBA BA WHERE BA.COMPANY=:COMPANY AND BA.BA001 IN (SELECT CB002 FROM CMSCB WHERE CB001 = :MEMID)",
    nativeQuery = true)
  List<GSCMSBA> findAssignedDepartmentByMemberId(
    @Param("COMPANY") String companyId, @Param("MEMID") String memId);
  
  GSCMSBA findByBA001(String id);
  
  GSCMSBA findByCompanyAndBA001(String company, String id);
  
  List<GSCMSBA> findByCompany(String company);
}
