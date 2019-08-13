package com.yongliang.elk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 组织部门实体
 *
 * @author zhangyongliang
 * @create 2019-08-13 18:17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization  implements Serializable {
    private static final long serialVersionUID = -4511095189836094802L;
    private Long id;
    private String name;
    private String address;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
