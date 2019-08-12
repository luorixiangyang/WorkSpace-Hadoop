package com.yongliang.boot.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyongliang
 * @create 2019-07-28 15:09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String username;
    private String password;
}
