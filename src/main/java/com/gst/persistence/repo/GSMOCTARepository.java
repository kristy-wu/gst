package com.gst.persistence.repo;

import com.gst.persistence.entity.GSMOCTA;
import com.gst.persistence.key.GSMOCTA_ID;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GSMOCTARepository extends JpaRepository<GSMOCTA, GSMOCTA_ID> {
  
  @Query(value = "SELECT MAX(TA501) val FROM MOCTA WHERE TA501 < ?1", nativeQuery = true)
  Double findLastSequenceLessThan(Double sequence);
  
  @Query(value = "SELECT MIN(TA501) val FROM MOCTA WHERE TA501 > ?1", nativeQuery = true)
  Double findFirstSequenceGreaterThan(Double sequence);
  
  GSMOCTA findByTA501(Double sequence);
  
  List<GSMOCTA> findByTA021(String tA021, Sort sort);
  
  /*
   * for GSPMCDashboard
   */
  @Query(
    value =
      "SELECT * FROM MOCTA WHERE COMPANY = :CMP AND UPPER(TA011) <> 'Y' "
        + "ORDER BY TA021,TA501,TA001,TA002",
    nativeQuery = true)
  List<GSMOCTA> findAllOnlineMOCTA(@Param("CMP") String company);
  
  /*
  2022-04-07  GSPMCDashboard 製令數量過多造成顯示效能問題
  取各線別前五筆製令
   */
  @Query(
    value =
      "SELECT * FROM\n"
        + "    (\n"
        + "      SELECT *, ROW_NUMBER()\n"
        + "  OVER (PARTITION BY TA021 ORDER BY TA021,TA501,TA001,TA002 DESC) AS rn\n"
        + "  FROM MOCTA WHERE COMPANY = :CMP AND UPPER(TA011) <> 'Y'\n"
        + "    ) MO_RANK\n"
        + "  WHERE rn <= :TOPN",
    nativeQuery = true)
  List<GSMOCTA> findAllOnlineMOCTATopN(@Param("CMP") String company, @Param("TOPN") String topN);
  
  /*
   * for GSPMCScheduler, GSPMCStock...
   */
  @Query(
    value =
      "SELECT * FROM MOCTA WHERE COMPANY = :CMP AND "
        + "TA021 = :DEPT AND UPPER(TA011) <> 'Y' "
        + "ORDER BY TA501,TA001,TA002",
    nativeQuery = true)
  List<GSMOCTA> findOnlineMOCTAByDept(@Param("CMP") String company, @Param("DEPT") String TA021);
}
