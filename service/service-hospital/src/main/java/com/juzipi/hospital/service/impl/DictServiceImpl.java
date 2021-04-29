package com.juzipi.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.hospital.mapper.DictMapper;
import com.juzipi.hospital.service.DictService;
import com.juzipi.inter.model.pojo.dictionary.Dict;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author juzipi
 * @Date 2021/4/29 18:13
 * @Info
 */
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public String queryDictByCodeAndValue(String dictCode, String dictValue) {
        Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>().lambda().eq(Dict::getDictCode, dictCode).or().eq(Dict::getDictValue, dictValue));
        return dict.getDictName();

    }


}
