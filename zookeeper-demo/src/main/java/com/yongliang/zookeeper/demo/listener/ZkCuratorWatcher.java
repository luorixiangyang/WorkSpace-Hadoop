package com.yongliang.zookeeper.demo.listener;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

/**
 * ZK监听实现
 *
 * @author zhangyongliang
 * @create 2019-01-12 15:15
 **/
public class ZkCuratorWatcher  implements CuratorWatcher {
    @Override
    // Watcher事件通知方法
    public void process(WatchedEvent event) throws Exception {
        System.out.println("触发watcher，节点路径为：" + event.getPath());
    }
}
