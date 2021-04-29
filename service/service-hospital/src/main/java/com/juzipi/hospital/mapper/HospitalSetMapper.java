package com.juzipi.hospital.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzipi.inter.model.pojo.HospitalSet;
import org.springframework.data.repository.query.Param;

/**
 * @Author juzipi
 * @Date 2021/4/27 14:31
 * @Info 医院设置实体类mapper
 */
public interface HospitalSetMapper extends BaseMapper<HospitalSet> {


    /**
     *
     * @param hospitalSet
     * @return 数据库受影响行数
     */
    Integer addHospitalSet(HospitalSet hospitalSet);


    Integer lockHospitalSet(@Param("id") Long id, @Param("status") Integer status);
}
