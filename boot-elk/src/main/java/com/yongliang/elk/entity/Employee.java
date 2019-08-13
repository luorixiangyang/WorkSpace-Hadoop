package com.yongliang.elk.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 员工实体属性
 *
 * @author zhangyongliang
 * @create 2019-08-13 18:04
 **/
@Document(indexName = "sample",type = "employee")
@Data
public class Employee  implements Serializable {
    private static final long serialVersionUID = -3384411498001245566L;
    @Id
    private Long id;
    @Field(type = FieldType.Object)
    private Organization organization;
    @Field(type = FieldType.Object)
    private Department department;
    private String name;
    private int age;
    private String position;
}
