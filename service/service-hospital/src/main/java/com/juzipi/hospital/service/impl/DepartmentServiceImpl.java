package com.juzipi.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juzipi.commonutil.constant.BaseConstants;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.commonutil.constant.ManageConstants;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.repository.DepartmentRepository;
import com.juzipi.hospital.service.DepartmentService;
import com.juzipi.inter.model.base.BaseMongoEntity;
import com.juzipi.inter.model.pojo.hospital.Department;
import com.juzipi.inter.model.pojo.hospital.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/5 19:51
 * @Info
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Department insertDepartment(Map<String, Object> map) {
        String string = JSONObject.toJSONString(map);
        Department department = JSONObject.parseObject(string, Department.class);
        //因为每个医院里都有科室吧，根据医院编号和对应的科室编号就能确定唯一的科室了
        Department departmentExists = checkDepartmentExists(department.getHpCode(), department.getDepCode());
        if (StringUtils.isNotNull(departmentExists)){
            //非空就是更新
            departmentExists.setUpdateTime(new Date());
            return departmentRepository.save(departmentExists);
        }
        department.setCreateTime(new Date());
        department.setUpdateTime(department.getCreateTime());
        department.setDeleted(ConstantsMp.DELETED_VALUE);
        return departmentRepository.save(department);
    }


    /**
     * 根据hpCode和depCode判断 department 是否存在
     * @param hpCode
     * @param depCode
     * @return true or false
     */
    private Department checkDepartmentExists(String hpCode, String depCode){
        return departmentRepository.queryDepartmentByHpCodeAndDepCode(hpCode, depCode);
    }

}
