package com.gst.persistence.repo;

import com.gst.persistence.entity.GSCMSGA;
import com.gst.persistence.key.GSCMSGA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GSCMSGARepository extends JpaRepository<GSCMSGA, GSCMSGA_ID> {

    List<GSCMSGA> findByCompany(String companyId);

    GSCMSGA findByCompanyAndGA001(String companyId, String GA001);
}
