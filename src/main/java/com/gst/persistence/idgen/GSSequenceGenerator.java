package com.gst.persistence.idgen;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Properties;

public class GSSequenceGenerator extends SequenceStyleGenerator {
  
  public static final String PARAM_PREFIX = "prefix";
  
  private String prefix;
  
  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
    throws MappingException {
    
    super.configure(LongType.INSTANCE, params, serviceRegistry);
    
    setPrefix(params.getProperty(PARAM_PREFIX));
  }
  
  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
    throws HibernateException {
    
    StringBuilder styled_sequence = new StringBuilder(getPrefix());
    
    Calendar c = Calendar.getInstance();
    long t = c.getTimeInMillis();
    String t_head = String.format("%07X", t / 10000L);
    styled_sequence.append(t_head);
    
    Long orig_sequence = (Long) super.generate(session, object);
    String hexseq = String.format("%04X", orig_sequence % 10000L);
    styled_sequence.append(hexseq);
    
    String t_tail = String.format("%04X", t % 10000);
    styled_sequence.append(t_tail);
    
    return styled_sequence.toString();
  }
  
  private String getPrefix() {
    return this.prefix;
  }
  
  private void setPrefix(String p) {
    this.prefix = p;
  }
}
