package com.yongliang.socket.utils;

import cn.hutool.core.collection.CollectionUtil;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChannelMap管理类
 *
 * @author zhangyongliang
 * @create 2020-01-21 10:04
 **/
public class ChannelMapUtil {
    private static int channelNum = 0;
    private static ConcurrentHashMap<String, ChannelHandlerContext> channelHashMap = null;

    public static ConcurrentHashMap<String, ChannelHandlerContext> getChannelHashMap() {
        return channelHashMap;
    }

    //获取特定channel
    public static ChannelHandlerContext getChannelByName(String name) {
        if (CollectionUtil.isEmpty(channelHashMap)) {
            return null;
        }
        return channelHashMap.get(name);
    }

    //增加Channel
    public static void addChannel(String name, ChannelHandlerContext channelContext) {
        if (channelHashMap == null) {
            channelHashMap = new ConcurrentHashMap<>(200);
        }
        channelHashMap.put(name, channelContext);
        channelNum++;
    }

    //移除channel
    public static int removeChannelByName(String name) {
        if (CollectionUtil.isNotEmpty(channelHashMap) && channelHashMap.containsKey(name)) {
            channelHashMap.remove(name);
            return 0;
        } else {
            return -1;
        }
    }

    public static int removeChannelContext(ChannelHandlerContext ctx) {
        if (CollectionUtil.isNotEmpty(channelHashMap)) {
            for (Map.Entry<String, ChannelHandlerContext> channelInfo : channelHashMap.entrySet()) {
                String hosCodeKey = channelInfo.getKey();
                ChannelHandlerContext channelResult = channelInfo.getValue();
                if (channelResult.equals(ctx)) {
                    channelHashMap.remove(hosCodeKey);
                    return 0;
                }
            }
        }
        return -1;
    }

}
