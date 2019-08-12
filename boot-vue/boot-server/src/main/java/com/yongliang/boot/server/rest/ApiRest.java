package com.yongliang.boot.server.rest;

import com.yongliang.boot.server.entity.UserInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyongliang
 * @create 2019-07-28 15:03
 **/
@RestController
@RequestMapping(value = "/api")
public class ApiRest {
    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> userLogin(@RequestBody UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println("请求了后台信息");
        if (userInfo.getUsername().equals("admin") && userInfo.getPassword().equals("123456")) {
            result.put("code", "0");
            result.put("msg", "登录成功");
        }
        return result;
    }
}
