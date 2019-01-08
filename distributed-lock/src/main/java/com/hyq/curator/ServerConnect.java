package com.hyq.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import static com.hyq.curator.Configuration.*;

/**
 * Created by heyuqiu on 2019/1/8.
 */
public class ServerConnect {
    public CuratorFramework connect() {
        //声明一个重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(SERVER_IPS, SESSION_TIMEOUT_MS, CONNECT_TIMEOUT_MS, retryPolicy);
        client.start();
        return client;
    }
}
