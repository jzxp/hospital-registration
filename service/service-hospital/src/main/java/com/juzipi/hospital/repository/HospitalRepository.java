package com.juzipi.hospital.repository;

import com.juzipi.inter.model.pojo.hospital.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/5/4 21:26
 * @Info
 */
public interface HospitalRepository extends MongoRepository<Hospital, Long> {

    /**
     * 根据hpCode查询hospital
     * @param hpCode
     * @return
     */
    Hospital queryHospitalByHpCode(String hpCode);

    Hospital queryById(String id);


    List<Hospital> queryHospitalByHpNameLike(String hpName);
}
