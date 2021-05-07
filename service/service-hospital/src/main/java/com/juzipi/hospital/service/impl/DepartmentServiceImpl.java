package com.juzipi.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.commonutil.constant.MongoConstants;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.repository.DepartmentRepository;
import com.juzipi.hospital.service.DepartmentService;
import com.juzipi.inter.model.pojo.hospital.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

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



    @Override
    public Page<Department> queryPageDepartment(Integer pageNum, Integer pageSize, String hpCode) {
        //mongodb的分页查询索引从0开始
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        //这个department里面的数据就是查询条件
        Department department = new Department();
        department.setHpCode(hpCode);
        department.setDeleted(MongoConstants.DELETED_VALUE_FALSE);
        //CONTAINING：包含，可能是类似模糊查询的吧，后面的是不区分大小写？
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        /*
        小结：mongoRepository的一些查询需要两个参数：一个是案例，一个是分页的，
        案例里有两个参数：一个是实体类（它会根据里面的属性去跟查询出的属性对比），一个是匹配器，匹配器可以设置匹配规则，比如包含，精确等
        分页就是springdata的了，感觉上没有pagehelper好用，没有过多的了解
         */
        return departmentRepository.findAll(Example.of(department, exampleMatcher), pageable);
    }



    @Override
    public Integer removeDepartment(String hpCode, String depCode) {
        Department department = departmentRepository.queryDepartmentByHpCodeAndDepCode(hpCode, depCode);
        department.setDeleted(MongoConstants.DELETED_VALUE_TRUE);//设置为1
        department.setUpdateTime(new Date());//设置更新时间
        Department save = departmentRepository.save(department);
        Department departmentExists = checkDepartmentExists(save.getHpCode(), save.getHpCode());
        if (Objects.equals(departmentExists.getDeleted(), MongoConstants.DELETED_VALUE_TRUE)){
            return 1;
        }
        return 0;
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
