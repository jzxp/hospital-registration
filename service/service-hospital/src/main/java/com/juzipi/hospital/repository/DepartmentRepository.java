package com.juzipi.hospital.repository;

import com.juzipi.inter.model.pojo.hospital.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author juzipi
 * @Date 2021/5/5 19:50
 * @Info
 */
public interface DepartmentRepository extends MongoRepository<Department, String> {

    Department queryDepartmentByHpCodeAndDepCode(String hpCode, String depCode);

}
