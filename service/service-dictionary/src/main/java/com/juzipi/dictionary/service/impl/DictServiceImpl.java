package com.juzipi.dictionary.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.commonutil.exception.ExcelException;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.dictionary.listener.DictListener;
import com.juzipi.dictionary.mapper.DictMapper;
import com.juzipi.dictionary.service.DictService;
import com.juzipi.inter.model.pojo.dictionary.Dict;
import com.juzipi.inter.vo.DictExcelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/4/30 8:51
 * @Info
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {



    @Autowired
    private DictMapper dictMapper;


    @Override
    public String queryDictByCodeAndValue(String dictCode, String dictValue) {
        //dictCode为空就表示它是分类下具体的条目，就根据它的dictValue去查询就能得到唯一值
        if (StringUtils.isEmpty(dictCode)){
            return dictMapper.selectOne(new QueryWrapper<Dict>().lambda().eq(Dict::getDictValue, dictValue)).getDictName();
        }
        //不为空就根据dictCode和dictValue查询
        Dict dict = dictMapper.selectOne(new QueryWrapper<Dict>().lambda().eq(Dict::getDictCode, dictCode));
        //用dictParentId和dictValue来确认唯一的分类值
        return dictMapper.selectOne(new QueryWrapper<Dict>().lambda().eq(Dict::getParentId, dict.getId()).eq(Dict::getDictValue, dict.getDictValue())).getDictName();
    }



    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
    @Override
    public List<Dict> queryChildDataById(Long id) {
        List<Dict> dicts = dictMapper.selectList(new QueryWrapper<Dict>().lambda().eq(Dict::getParentId, id));
        //判断是否有子数据，遍历集合，把结果设置给hasChildren
        dicts.forEach(dict -> dict.setHasChildren(isChildren(dict.getId())));
        return dicts;
    }



    @Override
    public void exportData(HttpServletResponse response) {
        //设置下载信息
        setResponse(response);
        //查询数据库
//        List<Dict> dicts = baseMapper.selectList(new QueryWrapper<Dict>().lambda().select(Dict::getId, Dict::getParentId, Dict::getDictName, Dict::getDictValue, Dict::getDictCode));
        List<DictExcelVo> dictExcelVos = dictMapper.queryDictExcelVoList();
        try {
            EasyExcel.write(response.getOutputStream(), DictExcelVo.class).sheet("dict").doWrite(dictExcelVos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException("导出数据", this.getClass().getName(), e.getMessage());
        }
    }



    //刷新缓存中的内容
    @CacheEvict(value = "dict", allEntries = true)
    @Override
    public Boolean importData(MultipartFile uploadFiles) {
        try {
            EasyExcel.read(uploadFiles.getInputStream(), DictExcelVo.class, new DictListener()).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException("导入数据", this.getClass().getName(), e.getMessage());
        }
    }




    /**
     * 根据字典id判断是否有子数据
     *
     * @param dictId
     * @return 布尔
     */
    private Boolean isChildren(Long dictId) {
        return dictMapper.isChildren(dictId) > 0;
    }


    /**
     * 设置返回结果信息
     *
     * @param response
     * @return
     */
    private HttpServletResponse setResponse(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "dict";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
        return response;
    }


}
