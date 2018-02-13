package com.nzb.interrupt;
/**
 * @author M
 * @create 2018/2/11
 */
public class SafeInterrupt implements Runnable{
    private volatile boolean on = true;
    private long i = 0;

    @Override
    public void run() {
        while (on && Thread.currentThread().isInterrupted()) {
            i++;
        }
        System.out.println("TestRunnable is running : " + i);
    }

    public void cancel() {
        on = false;
        Thread.currentThread().interrupt();
    }
}
