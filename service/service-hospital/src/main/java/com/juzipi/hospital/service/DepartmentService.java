package com.juzipi.hospital.service;

import com.github.pagehelper.PageInfo;
import com.juzipi.commonutil.tool.PageResult;
import com.juzipi.inter.model.pojo.hospital.Department;
import com.juzipi.inter.vo.DepartmentSelectVo;
import org.springframework.data.domain.Page;

import java.util.List;
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


    PageInfo queryPageDepartment(Integer pageNum, Integer pageSize);

    Integer removeDepartment(String hoCode, String depCode);

}
