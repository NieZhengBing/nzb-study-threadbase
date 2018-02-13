package com.nzb.interrupt;

/**
 * @author M
 * @create 2018/2/11
 */
public class BlockInterrupt {
    private static Object o = new Object();

    private static class WhileTryWhenBlock extends Thread {
        private volatile boolean on = true;
        private long i = 0;

        @Override
        public void run() {
            System.out.println("current executing thread id: " + Thread.currentThread().getId());
            while (on && !Thread.currentThread().isInterrupted()) {
                System.out.println("i = " + i++);
                try {
                    synchronized (o) {
                        o.wait();
                    }
                } catch (InterruptedException e) {
                    System.out.println("currrent excuting thread interrupt flag is: " + Thread.currentThread().getId() + ":" + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("interrupted thread: " + Thread.currentThread().getId() + ":" + isInterrupted());
                }
            }
        }

        public void cancel() {
            interrupt();
            System.out.println("current method thread instance: " + getId());
            System.out.println("execute current method thread: " + Thread.currentThread().getId());
        }
    }

    private static class TryWhileWhenBlock extends Thread {
        private volatile boolean on = true;
        private long i = 0;

        @Override
        public void run() {
            try {
                while (on) {
                    System.out.println(i++);
                    synchronized (o) {
                        o.wait();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("current execute thread interrupted flag: " + Thread.currentThread().getId() + ":" + Thread.currentThread().isInterrupted());
            } finally {

            }
        }

        public void cancel() {
            on = false;
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WhileTryWhenBlock whileTryWhenBlock = new WhileTryWhenBlock();
        whileTryWhenBlock.start();
        Thread.sleep(100);
        whileTryWhenBlock.cancel();
        /*TryWhileWhenBlock tryWhileWhenBlock = new TryWhileWhenBlock();
        tryWhileWhenBlock.start();
        Thread.sleep(100);
        tryWhileWhenBlock.cancel();*/
    }

/*    private static volatile boolean on = true;

    private static class WhenBlock implements Runnable {

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void cancel() {
            on = false;
            Thread.currentThread().interrupt();
        }
    }*/
}
