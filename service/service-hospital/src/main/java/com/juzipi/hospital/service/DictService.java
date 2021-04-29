package com.juzipi.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzipi.inter.model.pojo.dictionary.Dict;

/**
 * @Author juzipi
 * @Date 2021/4/29 18:12
 * @Info
 */
public interface DictService extends IService<Dict> {


    String queryDictByCodeAndValue(String dictCode, String dictValue);
}
