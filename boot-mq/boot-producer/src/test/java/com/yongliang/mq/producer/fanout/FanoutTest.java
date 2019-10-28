package com.yongliang.mq.producer.fanout;

import cn.hutool.core.date.DateUtil;
import com.yongliang.mq.producer.BootProducerApplication;
import com.yongliang.mq.producer.producer.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangyongliang
 * @create 2019-10-28 16:00
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootProducerApplication.class)
public class FanoutTest {
    @Autowired
    private Sender sender;
    @Test
    public void Test() throws InterruptedException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        sender.sendFanoutMsg1("Time1 => " + DateUtil.now());
        sender.sendFanoutMsg2("Date2 => " + DateUtil.now());
    }

}
