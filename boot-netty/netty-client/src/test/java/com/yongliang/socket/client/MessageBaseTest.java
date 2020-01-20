package com.yongliang.socket.client;

import com.yongliang.socket.protobuf.MessageBase;

import java.util.UUID;

/**
 * @author zhangyongliang
 * @create 2020-01-20 16:57
 **/
public class MessageBaseTest {
    public static void main(String[] args) {
        MessageBase.Message message = MessageBase.Message.newBuilder()
                .setRequestId(UUID.randomUUID().toString())
                .setContent("{\"studyInfo\":{\"updatedTime\":\"2020-01-04 12:30:48\",\"studyTime\":\"1970-01-01 08:01:24\",\"studyLocalUrl\":\"G:\\\\20170110\\\\0260061B.img24316461.4917023\",\"studyRemoteUrl\":\"36a9e0d958b4188269a9-LF20190514345488/Storage/2017/01/10/DX/24316461.4917023\",\"studyNum\":0,\"receiveStatus\":2,\"patientFk\":\"8a92005e6f6641f2016f6ed1e914138b\",\"seriesList\":[{\"sopCuid\":\"1.2.840.10008.5.1.4.1.1.1.1\",\"updatedTime\":\"2020-01-04 12:30:48\",\"modality\":\"DX\",\"seriesNum\":0,\"seriesNo\":5472,\"seriesDesc\":\"Cervical-spine\",\"tsuid\":\"1.2.840.10008.1.2\",\"seriesPk\":\"8a92005e6f6641f2016f6ed1e979138f\",\"bodyPart\":\"CSPINE\",\"studyFk\":\"8a92005e6f6641f2016f6ed1e942138d\",\"institution\":\"LIN FEN SHI HP\",\"instanceList\":[{\"sopCuid\":\"1.2.840.10008.5.1.4.1.1.1.1\",\"seriesFk\":\"8a92005e6f6641f2016f6ed1e979138f\",\"contentDate\":\"20170110\",\"updatedTime\":\"2020-01-04 12:30:48\",\"contentTime\":\"08:48:28\",\"dicomSize\":\"2237330\",\"instancePk\":\"8a92005e6f6641f2016f6ed1e9a11391\",\"instNo\":1,\"sopIuid\":\"1.2.840.113619.2.203.4.2147483647.1484059708.878767\",\"createdTime\":\"2020-01-04 12:30:48\",\"storageUrl\":\"36a9e0d958b4188269a9-LF20190514345488/Storage/2017/01/10/DX/24316461.4917023/1.2.840.113619.2.203.4.2147483647.1484059611.4768/1.2.840.113619.2.203.4.2147483647.1484059708.878767.dcm\",\"studyIuid\":\"24316461.4917023\",\"hosCode\":\"LF20190514345488\"},{\"sopCuid\":\"1.2.840.10008.5.1.4.1.1.1.1\",\"seriesFk\":\"8a92005e6f6641f2016f6ed1e979138f\",\"contentDate\":\"20170110\",\"updatedTime\":\"2020-01-04 12:30:48\",\"contentTime\":\"08:50:09\",\"dicomSize\":\"2128636\",\"instancePk\":\"8a92005e6f6641f2016f6ed1ea021393\",\"instNo\":5,\"sopIuid\":\"1.2.840.113619.2.203.4.2147483647.1484059809.656924\",\"createdTime\":\"2020-01-04 12:30:48\",\"storageUrl\":\"36a9e0d958b4188269a9-LF20190514345488/Storage/2017/01/10/DX/24316461.4917023/1.2.840.113619.2.203.4.2147483647.1484059611.4768/1.2.840.113619.2.203.4.2147483647.1484059809.656924.dcm\",\"studyIuid\":\"24316461.4917023\",\"hosCode\":\"LF20190514345488\"}],\"createdTime\":\"2020-01-04 12:30:48\",\"stationName\":\"082407080110\",\"seriesIuid\":\"1.2.840.113619.2.203.4.2147483647.1484059611.4768\",\"seriesSize\":0}],\"createdTime\":\"2020-01-04 12:30:48\",\"studyPk\":\"8a92005e6f6641f2016f6ed1e942138d\",\"studyDate\":\"20170110\",\"studyId\":\"24316461\",\"hosName\":\"临汾市人民医院\",\"studySize\":0,\"accessTime\":\"2020-01-04 12:30:48\",\"studyIuid\":\"24316461.4917023\",\"hosCode\":\"LF20190514345488\"},\"patientInfo\":{\"dicomattrsFk\":\"8a92005e6f6641f2016f6ed1e905138a\",\"patientName\":\"LIU RUN ZHI\",\"updatedTime\":\"2020-01-04 12:30:48\",\"patBirthdate\":\"19620704\",\"patientPk\":\"8a92005e6f6641f2016f6ed1e914138b\",\"patientId\":\"24316461\",\"studiesNum\":0,\"patSex\":\"F\",\"createdTime\":\"2020-01-04 12:30:48\",\"hosCode\":\"LF20190514345488\"}}").build();
        System.out.println("message: " + message.toString());
    }
}
