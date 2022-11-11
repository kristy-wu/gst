package com.gst.persistence.repo;


import com.gst.persistence.entity.GSMESAA;
import com.gst.persistence.key.GSMESAA_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GSMESAARepository extends JpaRepository<GSMESAA, GSMESAA_ID> {

}