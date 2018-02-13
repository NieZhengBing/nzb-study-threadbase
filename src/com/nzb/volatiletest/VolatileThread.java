package com.nzb.volatiletest;
/**
 * @author M
 * @create 2018/2/11
 */
public class VolatileThread implements Runnable{
    private volatile int a = 5;
    @Override
    public void run() {
        synchronized (this) {
            a = a + 1;
            System.out.println(Thread.currentThread().getName() + "---------" + a);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = a + 1;
            System.out.println(Thread.currentThread().getName() + "------------" + a);
        }
    }
}
