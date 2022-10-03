package com.dcsplab.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class BSTRedisMessagePublisher implements BSTMessagePublisher {
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
  @Autowired
  private ChannelTopic topic;
  
  public BSTRedisMessagePublisher() {
  }
  
  public BSTRedisMessagePublisher(
    final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
    this.redisTemplate = redisTemplate;
    this.topic = topic;
  }
  
  public void publish(final String message) {
    redisTemplate.convertAndSend(topic.getTopic(), message);
  }
}
