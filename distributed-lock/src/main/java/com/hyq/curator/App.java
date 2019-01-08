package com.hyq.curator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by heyuqiu on 2019/1/9.
 */
public class App {
    private static ExecutorService executors = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            executors.execute(() -> {
                try {
                    new LockPlay().getLockAndDoing("/lock/hyq", () -> {
                        System.out.println(Thread.currentThread().getName() + "睡2秒");
                        TimeUnit.SECONDS.sleep(2);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
