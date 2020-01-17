package com.yongliang.mq.producer.delay;

import cn.hutool.core.date.DateUtil;
import com.yongliang.mq.producer.BootProducerApplication;
import com.yongliang.mq.producer.producer.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangyongliang
 * @create 2019-10-28 17:50
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootProducerApplication.class)
public class DelayedTest {
    @Autowired
    private Sender sender;

    @Test
    public void Test() throws InterruptedException {
        System.out.println(DateUtil.now());
        sender.sendDelayMsg("Hi Admin.");
//        Thread.sleep(5 * 1000); //等待接收程序执行之后，再退出测试
    }
}
