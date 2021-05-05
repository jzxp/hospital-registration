package com.juzipi.hospital.service;

import com.juzipi.inter.model.pojo.hospital.Department;

import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/5 19:51
 * @Info
 */
public interface DepartmentService {

    Department insertDepartment(Map<String, Object> map);
}
