package com.yongliang.elk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangyongliang
 * @create 2019-08-13 18:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department  implements Serializable {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
