package com.altarix.exercisetwo.companystructuremanagement.controllers;

import com.altarix.exercisetwo.companystructuremanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
}
