package com.nzb.threadstate;

import java.util.concurrent.TimeUnit;

/**
 * @author M
 * @create 2018/2/11
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
