package com.gst.persistence.repo;

import com.gst.persistence.entity.GSADMBA;
import com.gst.persistence.key.GSADMBA_ID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GSADMBARepository extends JpaRepository<GSADMBA, GSADMBA_ID> {
  
  GSADMBA findByCompanyAndBA001(String company, String BA001);
  
  List<GSADMBA> findByCompanyOrderByBA001(String BA001);
  
  List<GSADMBA> findByBA004(String BA004);
}
