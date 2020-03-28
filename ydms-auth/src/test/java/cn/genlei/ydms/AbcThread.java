package cn.genlei.ydms;

import java.util.concurrent.Semaphore;

public class AbcThread {
        private int times;
        private Semaphore semaphoreA =new Semaphore(1);
        private Semaphore semaphoreB =new Semaphore(0);
        private Semaphore semaphoreC =new Semaphore(0);

        public AbcThread(int times) {
            this.times=times;
        }


        public static void main(String[] args) {
            AbcThread abc =new AbcThread(10);

            new Thread(abc::printB).start();
            new Thread(abc::printC).start();
            new Thread(abc::printA).start();

        }
        public void printA() {
            print("A",semaphoreA,semaphoreB);
        }
        public void printB() {
            print("B",semaphoreB,semaphoreC);
        }
        public void printC() {
            print("C",semaphoreC,semaphoreA);
        }


        public void print(String name,Semaphore current,Semaphore next) {
            for(int i=0;i<times;i++) {
                try {
                    current.acquire();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(i + " - " + Thread.currentThread().getId() + " -> " + name);
                next.release();
            }

        }

}
