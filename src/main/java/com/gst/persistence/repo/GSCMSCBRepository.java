package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSCB;
import com.gst.persistence.key.GSCMSCB_ID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GSCMSCBRepository extends JpaRepository<GSCMSCB, GSCMSCB_ID> {
  
  List<GSCMSCB> findByCompanyAndCB001(String company, String CB001);
}
