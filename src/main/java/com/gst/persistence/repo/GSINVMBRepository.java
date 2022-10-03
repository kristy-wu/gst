package com.gst.persistence.repo;

import com.gst.persistence.entity.GSINVMB;
import com.gst.persistence.key.GSINVMB_ID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GSINVMBRepository extends JpaRepository<GSINVMB, GSINVMB_ID> {
}
