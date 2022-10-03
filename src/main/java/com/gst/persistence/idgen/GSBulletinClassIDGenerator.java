package com.gst.persistence.idgen;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class GSBulletinClassIDGenerator extends SequenceStyleGenerator {
  
  private static final String prefix = "GBC";
  
  public GSBulletinClassIDGenerator() {
  }
  
  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
    throws MappingException {
    super.configure(LongType.INSTANCE, params, serviceRegistry);
  }
  
  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
    throws HibernateException {
    
    StringBuilder styled_sequence = new StringBuilder(prefix);
    
    Long orig_sequence = (Long) super.generate(session, object);
    
    String id_seq = String.format("%04d", orig_sequence);
    
    long currentTimestamp = System.currentTimeMillis();
    String hexTime = Long.toHexString(currentTimestamp).toUpperCase();
    styled_sequence.append(id_seq).append(hexTime);
    
    return styled_sequence.toString();
  }
}
