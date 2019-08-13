package com.yongliang.elk.controller;

import com.yongliang.elk.dao.EmployeeDao;
import com.yongliang.elk.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工查询控制类
 *
 * @author zhangyongliang
 * @create 2019-08-13 18:24
 **/
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeDao repository;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping("/{name}")
    public List<Employee> findByName(@PathVariable("name") String name) {
        return repository.findByName(name);
    }

    @GetMapping("/organization/{organizationName}")
    public List<Employee> findByOrganizationName(@PathVariable("organizationName") String organizationName) {
        return repository.findByOrganizationName(organizationName);
    }
}
