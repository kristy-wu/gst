package com.gst.persistence.repo;

import com.gst.persistence.entity.GSADMEA;
import com.gst.persistence.key.GSADMEA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GSADMEARepository extends JpaRepository<GSADMEA, GSADMEA_ID> {
}
