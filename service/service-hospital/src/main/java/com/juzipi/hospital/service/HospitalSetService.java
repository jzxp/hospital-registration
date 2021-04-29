package com.juzipi.hospital.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.model.pojo.hospital.HospitalSet;

/**
 * @Author juzipi
 * @Date 2021/4/27 14:18
 * @Info Service接口
 */
public interface HospitalSetService extends IService<HospitalSet> {

    /**
     * 新增医院设置
     * @param hospitalSet
     */
    Integer addHospitalSet(HospitalSet hospitalSet);

    /**
     * 修改医院设置
     * @param hospitalSet
     * @return
     */
    Integer modifyHospitalSet(HospitalSet hospitalSet);

    /**
     * 查询返回分页
     * @param pageBody
     * @return
     */
    Page<?> queryHospitalPage(PageBody pageBody);

    Integer lockHospitalSet(Long id,Integer status);
}
