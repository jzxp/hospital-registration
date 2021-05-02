package com.juzipi.dictionary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzipi.inter.model.pojo.dictionary.Dict;
import com.juzipi.inter.vo.DictExcelVo;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/4/30 8:55
 * @Info
 */
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 根据id查询子数据
     * @param id
     * @return
     */
    List<Dict> queryChildDataById(Long id);

    /**
     * 判断是否有子数据
     * @param dictId
     * @return
     */
    Integer isChildren(Long dictId);

    /**
     * 查询出要导出到excel的部分数据
     */
    List<DictExcelVo> queryDictExcelVoList();
}
