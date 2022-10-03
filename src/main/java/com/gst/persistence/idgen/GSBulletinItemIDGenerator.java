package com.gst.persistence.idgen;

import com.dcsplab.common.DLDateUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class GSBulletinItemIDGenerator extends SequenceStyleGenerator {
  
  // private static final String prefix = "GBI";
  
  public GSBulletinItemIDGenerator() {
  }
  
  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
    throws MappingException {
    super.configure(LongType.INSTANCE, params, serviceRegistry);
  }
  
  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
    throws HibernateException {
    
    StringBuilder styled_sequence = new StringBuilder();
    
    Long orig_sequence = (Long) super.generate(session, object);
    String id_seq = String.format("%04d", orig_sequence);
    
    Date today = Calendar.getInstance().getTime();
    String date_seq = DLDateUtils.format(today, DLDateUtils.Resolution.SimpleYM);
    
    styled_sequence.append(date_seq).append(id_seq);

    /*
    Long orig_sequence = (Long) super.generate(session, object);

    String id_seq = String.format("%04d", orig_sequence);

    long currentTimestamp = System.currentTimeMillis();
    String hexTime = Long.toHexString(currentTimestamp).toUpperCase();
    styled_sequence.append(id_seq).append(hexTime);*/
    
    return styled_sequence.toString();
  }
}
