package com.nzb.interrupt;
/**
 * @author M
 * @create 2018/2/11
 */
public class FlagCancel {
    private static class TestRunnable implements Runnable {

        private volatile boolean on = false;
        private long i = 0;
        @Override
        public void run() {
            while (on) {
                System.out.println(i++);
                /*try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                */}
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("TestRunnable is running : " + i);
        }
        public void cancel() {
            on = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestRunnable testRunnable = new TestRunnable();
        Thread t = new Thread(testRunnable);
        t.start();
        Thread.sleep(10);
        testRunnable.cancel();
    }
 }
