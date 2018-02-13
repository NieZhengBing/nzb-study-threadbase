package com.nzb.waitnotify;

/**
 * @author M
 * @create 2018/2/11
 */
public class User {
    public static final String CITY = "NewYork";
    private int age;
    private String city;

    public User(int age, String city) {
        this.age = age;
        this.city = city;
    }

    public User() {
    }

    public synchronized void changeCity() {
        this.city = "London";
        notifyAll();
    }

    public synchronized void changeAge() {
        this.age = 31;
        notifyAll();
    }

    public synchronized void waitAge() {
        while (this.age <= 30) {
            try {
                wait();
                System.out.println("wait age [" + Thread.currentThread().getId()
                        + "] is notified!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the age is " + this.age);
    }

    public synchronized void waitCity() {
        while (this.city.equals(CITY)) {
            try {
                wait();
                System.out.println("wait city [" + Thread.currentThread().getId()
                        + "] is notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the city is " + this.city);
    }
}
