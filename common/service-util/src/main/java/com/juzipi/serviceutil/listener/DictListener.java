package com.juzipi.serviceutil.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzipi.inter.model.pojo.dictionary.Dict;
import com.juzipi.inter.vo.DictExcelVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author juzipi
 * @Date 2021/5/2 16:19
 * @Info
 */
@Component
public class DictListener extends AnalysisEventListener<DictExcelVo> {

    @Autowired
    private BaseMapper baseMapper;


    @Override
    public void invoke(DictExcelVo data, AnalysisContext context) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(data, dict);
        baseMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }


}
