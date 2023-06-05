package com.yintech.cloud.service;

import com.yintech.cloud.entity.Employee;
import com.yintech.cloud.service.entity.dto.QueryDTO;
import com.yintech.commons.response.PageBaseResponse;

import java.util.List;

/**
 * @author kai.tang
 * @version 1.0-SNAPSHOT
 * @date 2020/5/15 15:43
 */
public interface DemoService {
    void saveDemo();

    void saveList();

    void update();
    PageBaseResponse<Employee> selectByPage(QueryDTO dto);

    Employee getOne();

    List<Employee> getList();
}
