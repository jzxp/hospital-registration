package com.juzipi.hospital.service;

import com.github.pagehelper.PageInfo;
import com.juzipi.inter.model.pojo.hospital.Department;

import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/5 19:51
 * @Info
 */
public interface DepartmentService {

    /**
     * 上传科室接口
     * @param map
     * @return
     */
    Department insertDepartment(Map<String, Object> map);

    /**
     * 查询科室分页结果集
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo queryPageDepartment(Integer pageNum, Integer pageSize);

    /**
     * 根据医院编码和科室编码删除科室
     * @param hoCode
     * @param depCode
     * @return
     */
    Integer removeDepartment(String hoCode, String depCode);

}
