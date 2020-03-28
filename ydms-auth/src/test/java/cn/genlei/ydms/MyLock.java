package cn.genlei.ydms;

import java.util.concurrent.TimeUnit;

public class MyLock {
    public static void main(String[] args) throws InterruptedException {
        MyLock lock = new MyLock();
        lock.test();
    }
    public void test() throws InterruptedException {
        Thread t = new Thread(runnable);
        t.start();
        System.out.println("before sleep ");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("after sleep ");
        //LockSupport.unpark(t);
        t.interrupt();
        t.join();
        System.out.println("end");
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("park . ");
          //  LockSupport.park();
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("被中断了");
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("被中断了");
            }
            System.out.println("unpark...");
        }
    };

}
