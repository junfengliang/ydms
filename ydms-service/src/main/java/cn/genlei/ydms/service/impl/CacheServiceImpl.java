package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: Junfeng
 */
@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void set(String key, Object value,long l, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key,value,l, timeUnit);
    }

    @Override
    public String get(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        if(o!=null){
            return o.toString();
        }
        return null;
    }

    @Override
    public String genCodeKey(String username) {
        return String.format("code_%s",username);
    }
}
