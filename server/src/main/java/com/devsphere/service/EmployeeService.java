package com.devsphere.service;

import com.devsphere.dto.EmployeeDTO;
import com.devsphere.dto.EmployeeLoginDTO;
import com.devsphere.dto.EmployeePageQueryDTO;
import com.devsphere.entity.Employee;
import com.devsphere.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
