package com.yongliang.elk.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 用户类实体
 *
 * @author zhangyongliang
 * @create 2019-08-13 17:58
 **/

@Document(indexName = "user", type = "student")
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 8954419017125416801L;
    private String id;
    private String username;
    private int age;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
