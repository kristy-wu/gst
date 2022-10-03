package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSEA;
import com.gst.persistence.key.GSCMSEA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GSCMSEARepository extends JpaRepository<GSCMSEA, GSCMSEA_ID> {
  
  List<GSCMSEA> findByCompanyAndEA001OrderByEA002Desc(String company, String EA001);
  
  @Query(
    value =
      "SELECT TOP(:TopN) * FROM CMSEA WHERE COMPANY = :Company "
        + "AND EA001 = 'Company' AND EA003 <= :Today AND EA004 >= :Today "
        + "ORDER BY EA002 DESC ",
    nativeQuery = true)
  List<GSCMSEA> findLatest(
    @Param("Company") String company, @Param("TopN") int count, @Param("Today") Date today);
}
