package cn.genlei.ydms.service;

import java.util.concurrent.TimeUnit;

/**
 * @author: Junfeng
 */
public interface CacheService {
    void set(String key, Object value,long l, TimeUnit timeUnit);
    String get(String key);

    String genCodeKey(String username);
}
