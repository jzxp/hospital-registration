package com.juzipi.dictionary.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.commonutil.exception.ExcelException;
import com.juzipi.dictionary.mapper.DictMapper;
import com.juzipi.dictionary.service.DictService;
import com.juzipi.inter.model.pojo.dictionary.Dict;
import com.juzipi.inter.vo.DictExcelVo;
import org.springframework.stereotype.Service;

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


    @Override
    public void exportData(HttpServletResponse response) {
        //设置下载信息
        setResponse(response);
        //查询数据库
//        List<Dict> dicts = baseMapper.selectList(new QueryWrapper<Dict>().lambda().select(Dict::getId, Dict::getParentId, Dict::getDictName, Dict::getDictValue, Dict::getDictCode));
        List<DictExcelVo> dictExcelVos = baseMapper.queryDictExcelVoList();
//        DictExcelVo dictExcelVo = new DictExcelVo();
//        ArrayList<DictExcelVo> dictExcelVos = new ArrayList<>();
//        dicts.forEach(dict -> BeanUtils.copyProperties(dict,dictExcelVo));
//        dictExcelVos.add(dictExcelVo);

        try {
            EasyExcel.write(response.getOutputStream(), DictExcelVo.class).sheet("dict").doWrite(dictExcelVos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException("导出excel数据", this.getClass().getName(), e.getMessage());
        }
    }



    /**
     * 根据字典id判断是否有子数据
     * @param dictId
     * @return 布尔
     */
    private Boolean isChildren(Long dictId){
        return baseMapper.isChildren(dictId) > 0;
    }


    private HttpServletResponse setResponse(HttpServletResponse response){
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "dict";
        response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xls");
        return response;
    }

}
