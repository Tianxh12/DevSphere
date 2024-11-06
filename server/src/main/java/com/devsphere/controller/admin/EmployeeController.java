package com.devsphere.controller.admin;

import com.devsphere.constant.JwtClaimsConstant;
import com.devsphere.dto.EmployeeDTO;
import com.devsphere.dto.EmployeePageQueryDTO;
import com.devsphere.entity.Employee;
import com.devsphere.properties.JwtProperties;
import com.devsphere.result.PageResult;
import com.devsphere.result.Result;
import com.devsphere.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.devsphere.vo.EmployeeLoginVO;
import com.devsphere.dto.EmployeeLoginDTO;
import com.devsphere.service.EmployeeService;

import java.util.Map;
import java.util.HashMap;


@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);
        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return 结果
     */
    @PostMapping("/logout")
    @ApiOperation("员工退出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     *
     * @param employeeDTO 员工信息
     * @return 结果
     */
    @PostMapping
    @ApiOperation("新增员工")
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }


    /**
     * 员工分页查询
     * @param employeePageQueryDTO 查询条件
     * @return 结果
     */
    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询，参数为{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 启用禁用员工账号
     * @param status 0禁用 1启用
     * @param id 员工id
     * @return 结果
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result<String> updateStatus(@PathVariable("status") Integer status, Long id) {
        log.info("启用禁用员工账号，状态为：{}", status);
        employeeService.startOrStop(status, id);
        return Result.success();
    }


    /**
     * 根据id查询员工
     * @param id 员工id
     * @return 结果
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工")
    public Result<Employee> getById(@PathVariable("id") Long id) {
        log.info("根据id查询员工，id为：{}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    @PutMapping
    @ApiOperation("修改员工信息")
    public Result<String> update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("修改员工信息，员工信息为：{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }






}
