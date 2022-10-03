package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSDB;
import com.gst.persistence.key.GSCMSDB_ID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GSCMSDBRepository extends JpaRepository<GSCMSDB, GSCMSDB_ID> {
}
