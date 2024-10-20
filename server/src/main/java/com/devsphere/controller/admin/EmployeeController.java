package com.devsphere.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
public class EmployeeController {

    @PostMapping("/login")
    public Result<T> login(@RequestBody)
}
