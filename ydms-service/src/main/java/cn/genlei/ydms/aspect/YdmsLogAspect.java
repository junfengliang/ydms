package cn.genlei.ydms.aspect;

import cn.genlei.ydms.annotation.YdmsLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author: Junfeng
 */

@Aspect
@Component
@Slf4j
public class YdmsLogAspect {

    @Around("@annotation(ydmsLog)")
    public Object around(ProceedingJoinPoint point, YdmsLog ydmsLog) throws Throwable {
        log.info("before run -----");
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        log.info("after run -----");

        return result;
    }
}
