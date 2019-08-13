package com.yongliang.elk.dao;

import com.yongliang.elk.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * &lt;p&gt;Copyright: Copyright (c) 2011&lt;/p&gt;
 * &lt;p&gt;公司名称 : 中国电信甘肃万维公司&lt;/p&gt;
 * &lt;p&gt;项目名称 : &lt;/p&gt;
 * &lt;p&gt;创建时间 :  &lt;/p&gt;
 * &lt;p&gt;类描述 :         &lt;/p&gt;
 *
 * @author &lt;a href=" "&gt;zhangyongliang&lt;/a&gt;
 * @version 1.0.0
 */
public interface EmployeeDao extends ElasticsearchCrudRepository<Employee, Long> {
    List<Employee> findByOrganizationName(String name);
    List<Employee> findByName(String name);
}
