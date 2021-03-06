package com.juzipi.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.mapper.HospitalSetMapper;
import com.juzipi.hospital.service.HospitalSetService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.model.pojo.hospital.HospitalSet;
import com.juzipi.inter.model.pojo.order.SignInfoVo;
import org.springframework.stereotype.Service;

/**
 * @Author juzipi
 * @Date 2021/4/27 14:18
 * @Info Service 实现类
 */
@Service
//第一个参数是mapper，第二个是实体类，继承这个ServiceImpl它里面帮我们注入了mapper，所以就不用再注入mapper了
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {


    //重写ServiceImpl里的getBaseMapper,这个方法就是注入mapper，跟直接注入一样
    @Override
    public HospitalSetMapper getBaseMapper() {
        return super.getBaseMapper();
    }


    @Override
    public Integer addHospitalSet(HospitalSet hospitalSet) {
        return getBaseMapper().addHospitalSet(hospitalSet);
    }


    @Override
    public Integer modifyHospitalSet(HospitalSet hospitalSet) {
        return getBaseMapper().updateById(hospitalSet);
    }

    @Override
    public Page<HospitalSet> queryHospitalPage(PageBody pageBody) {
        Page<HospitalSet> hospitalSetPage = new Page<>(pageBody.getPageNum(), pageBody.getPageSize());
        LambdaQueryWrapper<HospitalSet> like =
                new QueryWrapper<HospitalSet>().lambda().like(HospitalSet::getHpName, pageBody.getKey())
                        .or().eq(HospitalSet::getHpCode,pageBody.getKey());
        return getBaseMapper().selectPage(hospitalSetPage, like);
    }

    @Override
    public Integer lockHospitalSet(Long id,Integer status) {
        return getBaseMapper().lockHospitalSet(id,status);
    }



    @Override
    public SignInfoVo getSignInfoVo(String hpCode) {
        HospitalSet hospitalSet = baseMapper.selectOne(new QueryWrapper<HospitalSet>().lambda().eq(HospitalSet::getHpCode, hpCode));
        if (StringUtils.isNull(hospitalSet)){
            throw new BaseException(this.getClass().getName(),500,"签名校验失败");
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        return signInfoVo;
    }


}
