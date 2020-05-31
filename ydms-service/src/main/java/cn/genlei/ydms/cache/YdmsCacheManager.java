package cn.genlei.ydms.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Junfeng
 */
@Configuration
@Slf4j
public class YdmsCacheManager {
    @Value("${spring.redis.host:}")
    String redisHost;
    @Autowired RedisConnectionFactory redisConnectionFactory;

    @Bean
    public CacheManager getCacheManager() {
        if(StringUtils.isEmpty(redisHost)){
            log.info("use ehCacheManager");
            return ehCacheManager();
        }else{
            log.info("use redisCacheManager");
            return redisCacheManager();
        }
    }

    private CacheManager ehCacheManager(){
        EhCacheCacheManager manager = new EhCacheCacheManager();
        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.create();
        manager.setCacheManager(cacheManager);
        return manager;
    }

    private CacheManager redisCacheManager(){
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        // 默认配置，过期时间指定是30分钟
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(10));

        Map<String, RedisCacheConfiguration> map = new HashMap<>();
        map.put("ydms",defaultCacheConfig);

        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter,defaultCacheConfig,map);
        return cacheManager;
    }

}
