package cn.genlei.ydms.cache;

import cn.genlei.ydms.global.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: Junfeng
 */
@Slf4j
public class YdmsCacheManagerTest {
    @Autowired
    CacheManager cacheManager;

    public void getCache() {
        cacheManager.getCache(Constant.CACHE_YDMS).put("hello","world");
        while (true){
            String str = cacheManager.getCache(Constant.CACHE_YDMS).get("hello",String.class);
            log.info(str);
            if(str==null){
                break;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}