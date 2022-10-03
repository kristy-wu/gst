package com.gst.persistence.repo;

import com.gst.persistence.entity.GSMESTA;
import com.gst.persistence.key.GSMESTA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GSMESTARepository extends JpaRepository<GSMESTA, GSMESTA_ID> {
  
  @Query(
    value = "SELECT * FROM MESTA WHERE TA001 = ?1 AND TA503 <> '9' ORDER BY TA006",
    nativeQuery = true)
  List<GSMESTA> findByTA001OrderByTA006Asc(String TA001);
  
  @Query(
    value =
      "SELECT * FROM MESTA WHERE TA001 = ?1 "
        + "AND TA009 = ?2 "
        + "AND TA503 <> '9' ORDER BY TA006",
    nativeQuery = true)
  List<GSMESTA> findByTA001AndTA009OrderByTA006Asc(String TA001, String TA009);
  
  // List<GSMESTA> findByTA001AndTA004Contains(String TA001, String TA004);
  
  @Query(
    value =
      "SELECT * FROM MESTA WHERE TA001 = ?1 "
        + "AND TA004 LIKE %?2% AND TA503 <> '9' ORDER BY TA006",
    nativeQuery = true)
  List<GSMESTA> findReportByLineID(String TA001, String TA004);
  
  @Query(
    value =
      "SELECT ISNULL(SUM(TA008),0) AS AMT FROM MESTA "
        + " WHERE TA003 = ?1 AND TA004 = ?2 AND TA009 = 'N'"
        + " AND TA503 <> '9' ",
    nativeQuery = true)
  Double getUnReviewedAmount(String TA003, String TA004);
}
