package com.yongliang.zookeeper.demo.util;
import com.yongliang.zookeeper.demo.listener.ZkCuratorWatcher;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import java.util.List;
/**
 * Curator工具类
 *
 * @author zhangyongliang
 * @create 2019-01-12 14:07
 **/
public class CuratorUtils {
    public CuratorFramework client=null;
    private  static final String zkServerIps="192.168.92.20:2181,192.168.92.21:2181,192.168.92.22:2181";

    /**
     * 同步创建ZK实例，设置重连策略
     */
    public CuratorUtils() {
        //设置重连策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
        //实例化Curator客户端
        //使用工厂建立客户端对象
        client=  CuratorFrameworkFactory.builder()
        //放入ZK连接服务器IP
                .connectString(zkServerIps)
        //设定会话时间以及重连策略
                .sessionTimeoutMs(10000)
        //设定重连策略
                .retryPolicy(retryPolicy)
                .build();
        // 启动Curator客户端
        client.start();
    }
    //关闭客户端连接
    private  void closeZkClient(){
        if(client!=null){
            this.client.close();
        }
    }
    //获取当前客户端的状态
    private static void getZkStatus() {
        CuratorUtils curatorUtil=new CuratorUtils();
        boolean zkStatus=curatorUtil.client.isStarted();
        System.out.println("当前客户端的状态：" + (zkStatus ? "连接中..." : "已关闭..."));
    }
    //创建节点
    private  static  void createNonde()throws Exception{
        //节点路径
        String nodePath="/yongliang/testNode";
        //节点数据
        byte[] data="this is testZk Data".getBytes();
        CuratorUtils curatorUtil=new CuratorUtils();
        //创建父节点，递归创建
        String result=curatorUtil.client.create().creatingParentsIfNeeded()
       //创建持久节点
                      .withMode(CreateMode.PERSISTENT)
                      .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                      .forPath(nodePath,data);
        System.out.println(result+"节点，创建成功...");
        //关闭客户端
        curatorUtil.closeZkClient();
        //获取状态信息
        getZkStatus();
    }
    //更新节点
    private static  int updateNode()throws Exception{
        CuratorUtils curatorUtil=new CuratorUtils();
        //节点路径
        String nodePath="/yongliang/testNode";
        //更新数据
        byte[] data="this is new Data".getBytes();
        //指定数据版本
        Stat resultStatus=curatorUtil.client.setData().withVersion(0)
                          .forPath(nodePath,data);
        return resultStatus.getVersion();
    }
    //删除节点
    private static  void deleteNode()throws Exception{
        CuratorUtils curatorUtil=new CuratorUtils();
        //节点路径
        String nodePath="/yongliang/testNode";
        //删除节点, 如果删除失败会继续删除
        curatorUtil.client.delete().guaranteed()
        //子节点也递归删除
                   .deletingChildrenIfNeeded()
        //删除对应的版本
                   .withVersion(1)
                   .forPath(nodePath);
        curatorUtil.closeZkClient();
        getZkStatus();
    }
    //查询节点信息
    private static String getNodeInfo()throws Exception{
        CuratorUtils curatorUtil=new CuratorUtils();
        Stat statInfo=new Stat();
        //节点路径
        String nodePath="/yongliang/testNode";
        byte[] nodeData=curatorUtil.client.getData().storingStatIn(statInfo).forPath(nodePath);
        System.out.println("节点 " + nodePath + " 的数据为：" + new String(nodeData));
        System.out.println("该节点的数据版本号为：" + statInfo.getVersion());
        return  new String(nodeData);
    }
    //获取父节点的子节点列表信息
    private static List<String> getNodeListInfo()throws Exception{
        CuratorUtils curatorUtil=new CuratorUtils();
        List<String> nodeInfoList=null;
        //节点路径
        String nodePath="/yongliang/testNode";
        nodeInfoList=curatorUtil.client.getChildren().forPath(nodePath);
        System.out.println(nodePath + " 节点下的子节点列表：");
        for (String childNode : nodeInfoList) {
            System.out.println(childNode);
        }
        curatorUtil.closeZkClient();
        return  nodeInfoList;
    }
    //查询节点是否存在
    private static   boolean isNodeModify() throws Exception{
        CuratorUtils curatorUtil=new CuratorUtils();
        //节点路径
        String nodePath="/yongliang/testNode";
        // 查询某个节点是否存在，存在就会返回该节点的状态信息，如果不存在的话则返回空
        Stat  statExist = curatorUtil.client.checkExists().forPath(nodePath);
        if (statExist != null) {
            System.out.println(nodePath + " 节点存在");
            return  true;
        }
        curatorUtil.closeZkClient();
        return  false;
    }
    //Watcher 实现 修改节点数据就会触发监听 （有效性1次）
    private static void wathcerZK()throws Exception{
        CuratorUtils curatorUtil=new CuratorUtils();
        //节点路径
        String nodePath="/yongliang/testNode";
        curatorUtil.client.getData().usingWatcher(new ZkCuratorWatcher()).forPath(nodePath);
    }
    //一次注册，多次监听（只针对NodeChance事件）
    private  static void periestWathcerZk()throws Exception{
        CuratorUtils curatorUtil=new CuratorUtils();
        //节点路径
        String nodePath="/yongliang/testNode";
        // NodeCache: 缓存节点，并且可以监听数据节点的变更，会触发事件
        final NodeCache nodeCache = new NodeCache(curatorUtil.client, nodePath);
        // 参数 buildInitial : 初始化的时候获取node的值并且缓存
        nodeCache.start(true);
        // 获取缓存里的节点初始化数据
        if (nodeCache.getCurrentData() != null) {
            System.out.println("节点初始化数据为：" + new String(nodeCache.getCurrentData().getData()));
        } else {
            System.out.println("节点初始化数据为空...");
        }
        // 为缓存的节点添加watcher，或者说添加监听器
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            // 节点数据change事件的通知方法
            @Override
            public void nodeChanged() throws Exception {
                // 防止节点被删除时发生错误
                if (nodeCache.getCurrentData() == null) {
                    System.out.println("获取节点数据异常，无法获取当前缓存的节点数据，可能该节点已被删除");
                    return;
                }
                // 获取节点最新的数据
                String data = new String(nodeCache.getCurrentData().getData());
                System.out.println(nodeCache.getCurrentData().getPath() + " 节点的数据发生变化，最新的数据为：" + data);
            }
        });
    }
    //监听一个节点下的所有事件
    private static  void pathCildWatcher()throws Exception{
        CuratorUtils curatorUtil=new CuratorUtils();
        //节点路径
        String nodePath="/yongliang/testNode";
          final String PARENT_NODE_PATH = "/super";  // 父节点
        // PathChildrenCache: 监听数据节点的增删改，可以设置触发的事件
        final PathChildrenCache childrenCache = new PathChildrenCache(curatorUtil.client, PARENT_NODE_PATH, true);
        /**
         * StartMode: 初始化方式
         * POST_INITIALIZED_EVENT：异步初始化，初始化之后会触发事件
         * NORMAL：异步初始化
         * BUILD_INITIAL_CACHE：同步初始化
         */
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        // 列出子节点数据列表，需要使用BUILD_INITIAL_CACHE同步初始化模式才能获得，异步是获取不到的
        List<ChildData> childDataList = childrenCache.getCurrentData();
        System.out.println("当前节点的子节点详细数据列表：");
        for (ChildData childData : childDataList) {
            System.out.println("\t* 子节点路径：" + new String(childData.getPath()) + "，该节点的数据为：" + new String(childData.getData()));
        }
        // 添加事件监听器
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                // 通过判断event type的方式来实现不同事件的触发
                if (event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)) {  // 子节点初始化时触发
                    System.out.println("\n--------------\n");
                    System.out.println("子节点初始化成功");
                } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)) {  // 添加子节点时触发
                    if (event.getData().getPath().trim().equals(nodePath)) {
                        System.out.println("\n--------------\n");
                        System.out.print("子节点：" + event.getData().getPath() + " 添加成功，");
                        System.out.println("该子节点的数据为：" + new String(event.getData().getData()));
                    }
                    System.out.println("\n--------------\n");
                    System.out.print("子节点：" + event.getData().getPath() + " 添加成功，");
                    System.out.println("该子节点的数据为：" + new String(event.getData().getData()));
                } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {  // 删除子节点时触发
                    if (event.getData().getPath().trim().equals(nodePath)) {
                        System.out.println("\n--------------\n");
                        System.out.println("子节点：" + event.getData().getPath() + " 删除成功");
                    }
                    System.out.println("\n--------------\n");
                    System.out.println("子节点：" + event.getData().getPath() + " 删除成功");
                } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {  // 修改子节点数据时触发
                    if (event.getData().getPath().trim().equals(nodePath)) {
                        System.out.println("\n--------------\n");
                        System.out.print("子节点：" + event.getData().getPath() + " 数据更新成功，");
                        System.out.println("子节点：" + event.getData().getPath() + " 新的数据为：" + new String(event.getData().getData()));
                    }
                    System.out.println("\n--------------\n");
                    System.out.print("子节点：" + event.getData().getPath() + " 数据更新成功，");
                    System.out.println("子节点：" + event.getData().getPath() + " 新的数据为：" + new String(event.getData().getData()));
                }
            }
        });

    }
    public static void main(String[] args) throws Exception {
//        getZkStatus();
//        createNonde();
//       int result=updateNode();
//        System.out.println(result);
//        deleteNode();
//        String reuslt=getNodeInfo();
//        System.out.println(reuslt);
//        getNodeListInfo();
//        isNodeModify();
    }

}
