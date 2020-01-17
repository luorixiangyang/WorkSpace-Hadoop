package com.yongliang.elk.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis分布式锁
 *
 * @author zhangyongliang
 * @create 2019-11-13 10:12
 **/
@Component
public class DistributedLock {
    @Autowired
    private RedisTemplate redisTemplate;

    //获取锁
    public boolean getLock(String lockId, long millisecond) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockId, "lock", millisecond, TimeUnit.MILLISECONDS);
        return success != null && success;
    }

    //释放锁
    public boolean releaseLock(String lockId) {
       return redisTemplate.delete(lockId);
    }
}
