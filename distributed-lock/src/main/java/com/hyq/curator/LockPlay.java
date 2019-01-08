package com.hyq.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.data.Stat;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by heyuqiu on 2019/1/9.
 */
public class LockPlay {
    public void getLockAndDoing(String path,DoSomething doSomething) throws Exception {
        CuratorFramework connect = new ServerConnect().connect();
        System.out.println(Thread.currentThread().getName() + "已连接，状态：" + connect.getState());
        connect.checkExists().creatingParentsIfNeeded().forPath(path);
        InterProcessMutex interProcessMutex = new InterProcessMutex(connect, path);
        interProcessMutex.acquire();
        System.out.println(Thread.currentThread().getName() + "获得锁");
        doSomething.doing();
        interProcessMutex.release();
        System.out.println(Thread.currentThread().getName() + "释放锁");
    }
}
