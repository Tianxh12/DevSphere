package com.devsphere.service;

import com.devsphere.dto.EmployeeDTO;
import com.devsphere.dto.EmployeeLoginDTO;
import com.devsphere.dto.EmployeePageQueryDTO;
import com.devsphere.entity.Employee;
import com.devsphere.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO 登录信息
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO 员工信息
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO 分页查询条件
     * @return 分页查询结果
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工
     * @param status 0禁用 1启用
     * @param id 员工id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据id查询员工
     * @param id 员工id
     * @return 员工信息
     */
    Employee getById(Long id);

    /**
     * 修改员工信息
     * @param employeeDTO 员工信息
     */
    void update(EmployeeDTO employeeDTO);
}
