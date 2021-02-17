package xyz.oa.dao.impl;

import org.junit.Test;
import xyz.oa.dao.EmployeeDao;
import xyz.oa.entity.Employee;

import static org.junit.Assert.*;

public class EmployeeDaoImplTest {

    EmployeeDao employeeDao=new EmployeeDaoImpl();
    @Test
    public void selectLeader() {
        Employee employee = new Employee();
        employee.setDepartmentId(2L);
        employee.setLevel(5);
        employee.setEmployeeId(4L);
        employee.setName("曹鹏煊");
        employee.setTitle("研发工程");
        Employee employee1 = employeeDao.SelectLeader(employee);
        System.out.println(employee1.getEmployeeId());
        // Employee employee2 = employeeDao.SelectEmpById(2L);
        // System.out.println(employee2.getEmployeeId());
    }
}