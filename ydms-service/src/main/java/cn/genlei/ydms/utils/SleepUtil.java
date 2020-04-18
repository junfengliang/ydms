package cn.genlei.ydms.utils;

/**
 * @author: Junfeng
 */
public class SleepUtil {
    static boolean ENABLE_SLEEP = true;
    final static long SLEEP = 1_000L;

    public static void sleep(){
        if(!ENABLE_SLEEP){
            return;
        }
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
