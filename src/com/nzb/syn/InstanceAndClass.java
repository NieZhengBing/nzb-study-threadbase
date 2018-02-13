package com.nzb.syn;

import com.nzb.threadstate.SleepUtils;

/**
 * @author M
 * @create 2018/2/11
 */
public class InstanceAndClass {
    private static class TestClassSyn extends Thread {
        @Override
        public void run() {
            System.out.println("TestClass is going...");
            synClass();
        }
    }

    private static class TestInstanceSyn extends Thread {
        private InstanceAndClass instanceAndClass;

        public TestInstanceSyn(InstanceAndClass instanceAndClass) {
            this.instanceAndClass = instanceAndClass;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is going..." + instanceAndClass);
            instanceAndClass.synInstance();
        }
    }

    private static class TestInstaance2Syn implements Runnable {

        private InstanceAndClass instanceAndClass;

        public TestInstaance2Syn(InstanceAndClass instanceAndClass) {
            this.instanceAndClass = instanceAndClass;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2 is going..." + instanceAndClass);
            instanceAndClass.synInstance2();
        }
    }

    private static synchronized void synClass() {
        SleepUtils.second(1);
        System.out.println("synClass going...");
        SleepUtils.second(1);
    }

    private synchronized void synInstance() {
        SleepUtils.second(3);
        System.out.println("synInstance is going...");
        SleepUtils.second(3);
        System.out.println("synInstance ended");
    }

    private synchronized void synInstance2() {
        SleepUtils.second(3);
        System.out.println("synInstance2 going...");
        SleepUtils.second(3);
        System.out.println("synInstance2 ended");
    }

    public static void main(String[] args) {
        InstanceAndClass instanceAndClass = new InstanceAndClass();
        TestClassSyn t1 = new TestClassSyn();
        Thread t2 = new Thread(new TestInstanceSyn(instanceAndClass));
        Thread t3 = new Thread(new TestInstaance2Syn(instanceAndClass));
        t2.start();
        t3.start();
        SleepUtils.second(1);
        t1.start();

    }
}
