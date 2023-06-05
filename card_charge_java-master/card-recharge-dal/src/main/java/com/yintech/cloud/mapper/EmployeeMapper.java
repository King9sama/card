package com.yintech.cloud.mapper;

import com.yintech.cloud.entity.Employee;
import com.yintech.cloud.base.BaseMapper;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {

    int insertBatch(List<Employee> list);
}