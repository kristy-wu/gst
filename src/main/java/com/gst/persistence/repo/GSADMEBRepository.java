package com.gst.persistence.repo;

import com.gst.persistence.entity.GSADMEB;
import com.gst.persistence.key.GSADMEB_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GSADMEBRepository extends JpaRepository<GSADMEB, GSADMEB_ID> {
  
  List<GSADMEB> findByCompanyAndEB001(String company, String EB001);
}
