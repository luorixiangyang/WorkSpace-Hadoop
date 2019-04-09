package com.yongliang.webflux.webfluxdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author zhangyongliang
 * @create 2019-03-08 18:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
    private String id;
    //username 為唯一索引
    @Indexed(unique = true)
    private String username ;
    private String phone;
    private String email;
    private String name;
    private Date birthDay;

}
