package com.juzipi.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzipi.inter.model.pojo.dictionary.Dict;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/4/30 8:51
 * @Info
 */
public interface DictService extends IService<Dict> {

    /**
     * 根据字典编码和值查询字典
     * @param dictCode
     * @param dictValue
     * @return
     */
    String queryDictByCodeAndValue(String dictCode, String dictValue);

    /**
     * 根据id查询子数据列表
     * @param id
     * @return
     */
    List<Dict> queryChildDataById(Long id);

//    void exportData(HttpServletResponse response);
}
