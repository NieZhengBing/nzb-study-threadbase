package com.nzb;
/**
 * @author M
 * @create 2018/2/11
 */
public class HowStartThread {
    private static class TestThread extends Thread {
        @Override
        public void run() {
            System.out.println("TestThread is running");
        }
    }

    private static class TestRunable implements Runnable{

        @Override
        public void run() {
            System.out.println("TestRunnable is running");
        }
    }
    public static void main(String[] args) {
        Thread t1 = new TestThread();
        Thread t2 = new Thread(new TestRunable());
        t1.start();
        t2.start();
    }
}
