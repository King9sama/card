package com.yintech.cloud.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yintech.cloud.entity.Employee;
import com.yintech.cloud.mapper.EmployeeMapper;
import com.yintech.cloud.service.DemoService;
import com.yintech.cloud.service.entity.dto.QueryDTO;
import com.yintech.commons.annotation.Retry;
import com.yintech.commons.response.PageBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kai.tang
 * @version 1.0-SNAPSHOT
 * @date 2020/5/15 15:44
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Retry(remark = "测试保存", retryNum = 1, duration = 2000)
    public void saveDemo() {
        Employee employee = new Employee();
        employee.setCreateTime(new Date());
        employee.setPhone("13212345678");
        employee.setUpdateTime(new Date());
        employee.setUserName("demo1");
        employee.setWorkCode("342623199301284456");
        employeeMapper.insert(employee);

        Employee employee2 = new Employee();
        employee2.setCreateTime(new Date());
        employee2.setPhone("13212345678");
        employee2.setUpdateTime(new Date());
        employee2.setUserName("demo2");
        employee2.setWorkCode("342623199301284456");
        employeeMapper.insertSelective(employee2);
    }

    @Override
    public void saveList() {
        Employee employee = new Employee();
        employee.setCreateTime(new Date());
        employee.setPhone("13212345678");
        employee.setUpdateTime(new Date());
        employee.setUserName("demo3");
        employee.setWorkCode("342623199301284456");

        Employee employee2 = new Employee();
        employee2.setCreateTime(new Date());
        employee2.setPhone("13212345678");
        employee2.setUpdateTime(new Date());
        employee2.setUserName("demo4");
        employee2.setWorkCode("342623199301284456");
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);
        employeeMapper.insertBatch(list);
    }

    @Override
    public void update() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setCreateTime(new Date());
        employee.setPhone("13212345678");
        employee.setUpdateTime(new Date());
        employee.setUserName("demo3_update");
        employee.setWorkCode("342623199301284456");
        employeeMapper.updateByPrimaryKeySelective(employee);

        Employee employee2 = new Employee();
        employee2.setCreateTime(new Date());
        employee2.setPhone("13212345678");
        employee2.setUpdateTime(new Date());
        employee2.setUserName("demo4_update");
        employee2.setWorkCode("342623199301284456");
        Example example = new Example(Employee.class);
        example.createCriteria()
                .andEqualTo("userName", "demo4");
        employeeMapper.updateByExample(employee2, example);
    }

    @Override
    public PageBaseResponse<Employee> selectByPage(QueryDTO dto) {
        PageInfo<Employee> objectPageInfo = PageHelper.startPage(dto.getPageNo(), dto.getPageSize())
                .doSelectPageInfo(() -> employeeMapper.selectAll());
        return PageBaseResponse.success(objectPageInfo.getList(), (int) objectPageInfo.getTotal());
    }

    @Override
    public Employee getOne() {
        return employeeMapper.selectByPrimaryKey(1);
    }

    @Override
    public List<Employee> getList() {
        return employeeMapper.selectAll();
    }
}
