package com.juzipi.dictionary.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.dictionary.mapper.DictMapper;
import com.juzipi.dictionary.service.DictService;
import com.juzipi.inter.model.pojo.dictionary.Dict;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/4/30 8:51
 * @Info
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {


    @Override
    public String queryDictByCodeAndValue(String dictCode, String dictValue) {
        Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>().lambda().eq(Dict::getDictCode, dictCode).or().eq(Dict::getDictValue, dictValue));
        return dict.getDictName();

    }

    @Override
    public List<Dict> queryChildDataById(Long id) {
        List<Dict> dicts = baseMapper.selectList(new QueryWrapper<Dict>().lambda().eq(Dict::getParentId, id));
        //判断是否有子数据，遍历集合，把结果设置给hasChildren
        dicts.forEach(dict -> dict.setHasChildren(isChildren(dict.getId())));
        return dicts;
    }


//    @Override
//    public void exportData(HttpServletResponse response) {
//        EasyExcel.write()
//    }


    /**
     * 根据字典id判断是否有子数据
     * @param dictId
     * @return 布尔
     */
    private Boolean isChildren(Long dictId){
        return baseMapper.isChildren(dictId) > 0;
    }

}
