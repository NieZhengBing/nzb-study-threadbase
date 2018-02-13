package com.nzb;

/**
 * @author M
 * @create 2018/2/11
 */
public class SleepTest {
    private Object lock = new Object();

    private class ThreadSleep extends Thread {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take the lock");
            try {
                synchronized (lock) {
                    System.out.println(threadName + " taking the lock");
                    Thread.sleep(5000);
                    System.out.println("Finish the work: " + threadName);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ThreadNotSleep extends Thread {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take the lock time= " + System.currentTimeMillis());
            synchronized (lock) {
                System.out.println(threadName + " taking the lock time= " + System.currentTimeMillis());
                System.out.println("Finish the work: " + threadName);
            }
        }
    }

    public static void main(String[] args) {
        SleepTest sleepTest = new SleepTest();
        ThreadSleep threadA = sleepTest.new ThreadSleep();
        threadA.setName("ThreadSleep");
        ThreadNotSleep threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("ThreadNotSleep");
        threadA.start();
        try {
            Thread.sleep(1000);
            System.out.println(" RunTest slept!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }
}
