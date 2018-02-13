package com.nzb.bq;

/**
 * @author M
 * @create 2018/2/11
 */
public class BqTest {

    private static class ThreadPush extends Thread {
        BlockingQueueWN<Integer> bq;

        public ThreadPush(BlockingQueueWN<Integer> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            int i = 20;
            while (i > 0) {
                try {
                    Thread.sleep(1000);
                    System.out.println("i =" + i + " will push");
                    bq.enqueue(i--);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static class ThreadPop extends Thread {
        BlockingQueueWN<Integer> bq;

        public ThreadPop(BlockingQueueWN<Integer> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " will pop...");
                    Integer i = bq.dequeue();
                    System.out.println(" i=" + i.intValue() + " already pop");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueueWN bq = new BlockingQueueWN<>(10);
        ThreadPush threadA = new ThreadPush(bq);
        threadA.setName("Push");
        ThreadPop threadB = new ThreadPop(bq);
        threadB.setName("Pop");
        threadB.start();
        threadA.start();
    }
}
