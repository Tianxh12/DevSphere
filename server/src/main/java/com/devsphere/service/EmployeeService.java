package com.devsphere.service;

import com.devsphere.dto.EmployeeLoginDTO;
import com.devsphere.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}
