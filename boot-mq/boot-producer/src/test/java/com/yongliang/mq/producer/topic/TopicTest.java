package com.yongliang.mq.producer.topic;

import cn.hutool.core.date.DateUtil;
import com.yongliang.mq.producer.BootProducerApplication;
import com.yongliang.mq.producer.producer.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangyongliang
 * @create 2019-10-28 16:39
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootProducerApplication.class)
public class TopicTest {
    @Autowired
    private TopicSender topicSender;

    @Test
    public void Test() {
        topicSender.topicSender("time => " + DateUtil.now());
    }
}
