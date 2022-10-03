package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSDA;
import com.gst.persistence.key.GSCMSDA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GSCMSDARepository extends JpaRepository<GSCMSDA, GSCMSDA_ID> {
  
  List<GSCMSDA> findByCompanyOrderByDA001(String company);
  
  @Query(value = "SELECT * FROM CMSDA WHERE DA001 = 'Company' and COMPANY = ?1", nativeQuery = true)
  GSCMSDA findDefaultBoard(String compnay);
  
  GSCMSDA findByDA001(String DA001);
  
  GSCMSDA findByDA002(String DA002);

  /*
   don't know why deleteById(GSCMSDA_ID) not working... !!

   -->  the attributes in composite-id idClass should match the pk of entity
   -->  should not apply attribute inheritance on idClass

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM CMSDA WHERE DA001 = ?1", nativeQuery = true)
  void deleteByClassId(String id);*/
}
