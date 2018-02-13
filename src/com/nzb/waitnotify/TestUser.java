package com.nzb.waitnotify;
/**
 * @author M
 * @create 2018/2/11
 */
public class TestUser {
    private static User user = new User(30, User.CITY);

    private static class CheckAge extends Thread {
        @Override
        public void run() {
            user.waitAge();
        }
    }

    private static class CheckCity extends Thread {
        @Override
        public void run() {
            user.waitCity();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new CheckAge().start();
        }
        for (int i = 0; i < 3; i++){
            new CheckCity().start();
        }
        Thread.sleep(1000);
        user.changeCity();
    }
}
