package com.yongliang.elk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yongliang.elk.dao.EmployeeDao;
import com.yongliang.elk.entity.Department;
import com.yongliang.elk.entity.Employee;
import com.yongliang.elk.entity.Organization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhangyongliang
 * @create 2019-08-13 18:46
 **/
@Slf4j
public class SampleDataSet {
    private static final String INDEX_NAME = "sample";
    private static final String INDEX_TYPE = "employee";

    @Autowired
    EmployeeDao repository;
    @Autowired
    ElasticsearchTemplate template;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10000; i++) {
            bulk(i);
        }
    }

    public void bulk(int ii) {
        try {
            if (!template.indexExists(INDEX_NAME)) {
                template.createIndex(INDEX_NAME);
            }
            ObjectMapper mapper = new ObjectMapper();
            List<IndexQuery> queries = new ArrayList<>();
            List<Employee> employees = employees();
            for (Employee employee : employees) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(employee.getId().toString());
                indexQuery.setSource(mapper.writeValueAsString(employee));
                indexQuery.setIndexName(INDEX_NAME);
                indexQuery.setType(INDEX_TYPE);
                queries.add(indexQuery);
            }
            if (queries.size() > 0) {
                template.bulkIndex(queries);
            }
            template.refresh(INDEX_NAME);
            log.info("BulkIndex completed: {}", ii);
        } catch (Exception e) {
            log.error("Error bulk index", e);
        }
    }

    private List<Employee> employees() {
        List<Employee> employees = new ArrayList<>();
        int id = (int) repository.count();
        log.info("Starting from id: {}", id);
        for (int i = id; i < 10000 + id; i++) {
            Random r = new Random();
            Employee employee = new Employee();
            employee.setId((long) i);
            employee.setName("John Smith" + r.nextInt(1000000));
            employee.setAge(r.nextInt(100));
            employee.setPosition("Developer");
            int departmentId = r.nextInt(5000);
            employee.setDepartment(new Department((long) departmentId, "TestD" + departmentId));
            int organizationId = departmentId % 100;
            employee.setOrganization(new Organization((long) organizationId, "TestO" + organizationId, "Test Street No. " + organizationId));
            employees.add(employee);
        }
        return employees;
    }
}
