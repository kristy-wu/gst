package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSMR;
import com.gst.persistence.key.GSCMSMR_ID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GSCMSMRRepository extends JpaRepository<GSCMSMR, GSCMSMR_ID> {
}
