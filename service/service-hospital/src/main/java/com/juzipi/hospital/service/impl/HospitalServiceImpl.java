package com.juzipi.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juzipi.client.dict.DictFeignClient;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.commonutil.constant.MongoConstants;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.mapper.HospitalSetMapper;
import com.juzipi.hospital.repository.HospitalRepository;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.model.pojo.hospital.Hospital;
import com.juzipi.inter.model.pojo.hospital.HospitalSet;
import com.juzipi.inter.vo.hospital.HospitalSelectVo;
import com.juzipi.serviceutil.util.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author juzipi
 * @Date 2021/5/4 21:28
 * @Info
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalSetMapper hospitalSetMapper;

    @Autowired
    private DictFeignClient dictFeignClient;


    @Override
    public Hospital insertHospital(Map<String, Object> map) {
        //先转为String, 再用parseObject方法 传入相应的参数转为hospitalEntity对象
        String string = JSONObject.toJSONString(map);
        Hospital hospital = JSONObject.parseObject(string, Hospital.class);

        //查询数据库得到的为空表示不存在此数据就是新增，否则就是更新
        //此处有问题，更新操作依然是新增
        Hospital hospitalExists = checkHospitalExists(hospital.getHpCode());
        if (StringUtils.isNull(hospitalExists)){
            //为空就是新增
            hospital.setCreateTime(new Date());
            //默认为0未上线
            hospital.setStatus(ConstantsMp.HOSPITAL_STATUS_VALUE_MONGO);
            hospital.setUpdateTime(hospital.getCreateTime());
            hospital.setDeleted(ConstantsMp.DELETED_VALUE);
            return hospitalRepository.save(hospital);
        }
        //不为空就是更新
        hospitalExists.setUpdateTime(new Date());
        //重复添加会报错，这个新增更新的操作也不透明，看不到它干了什么
        return hospitalRepository.save(hospitalExists);
    }



    @Override
    public Boolean compareHospitalSign(Object hospitalSign, Object hospitalCode) {
        /*
        注意：hospitalSet表里的signKey并没有加密，它只是随机生成的字符串
         */
        HospitalSet hospitalSet = hospitalSetMapper.selectOne(new QueryWrapper<HospitalSet>().lambda().select(HospitalSet::getSignKey).eq(HospitalSet::getHpCode, hospitalCode));
        String signKey = hospitalSet.getSignKey();
        //加密一下然后比较
        String encryptSignKey = MD5.encrypt(signKey);
        return Objects.equals(encryptSignKey, hospitalSign);
    }



    @Override
    public Hospital queryHospitalByHpCode(Object hospitalCode) {
        return hospitalRepository.queryHospitalByHpCode(hospitalCode.toString());
    }


    @Override
    public PageInfo<Hospital> queryHospitalPage(PageBody pageBody, HospitalSelectVo hospitalSelectVo) {
        //分页
        PageHelper.startPage(pageBody.getPageNum().intValue(), pageBody.getPageSize().intValue());
        //条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Hospital hospital = new Hospital();
        //把hospitalSelectVo里的值复制到hospital里
        BeanUtils.copyProperties(hospitalSelectVo, hospital);
        List<Hospital> hospitals = hospitalRepository.findAll(Example.of(hospital, exampleMatcher));
        PageInfo<Hospital> hospitalPageInfo = new PageInfo<>(hospitals);
        //远程调用查询dictName接口查询出医院等级
        hospitalPageInfo.getList().forEach(this::setHospitalHpType);

        return hospitalPageInfo;
    }



    @Override
    public Hospital updateHospitalStatus(String id, Integer status) {
        Hospital hospital = hospitalRepository.queryById(id);
        hospital.setStatus(status);
        hospital.setUpdateTime(new Date());
        return hospitalRepository.save(hospital);
    }



    @Override
    public Hospital getHospitalById(String id) {
        //查询出基本信息，然后再调用setHospitalHpType方法加入其他一些信息
        return this.setHospitalHpType(hospitalRepository.queryById(id));

    }


    @Override
    public Hospital getHospitalByhpCode(String hpCode) {
        return hospitalRepository.queryHospitalByHpCode(hpCode);
    }



    /**
     * 根据hpCode判断hospital是否存在
     * @param hpCode
     * @return 存在就是true，否则false
     */
    private Hospital checkHospitalExists(String hpCode){
        return hospitalRepository.queryHospitalByHpCode(hpCode);
    }

    /**
     * 设置医院等级
     * @param hospital
     */
    public Hospital setHospitalHpType(Hospital hospital) {
        //这里代码思路不太好吧，感觉有点捞
        //hospital表里的hpType就是data_dict表里的dict_value
        if (StringUtils.isNull(hospital)){
            return null;
        }
        Result hpType = dictFeignClient.getName(hospital.getHpType());
        //设置进hospital的param（map）字段里，键是hpType,值就是查出来的dictName
        hospital.getParam().put(MongoConstants.HP_TYPE, hpType.getData().toString());
        //查询省，市，地区
        Result provinceCode = dictFeignClient.getName(hospital.getProvinceCode());
        Result cityCode = dictFeignClient.getName(hospital.getCityCode());
        Result districtCode = dictFeignClient.getName(hospital.getDistrictCode());
        hospital.getParam().put(MongoConstants.FULL_ADDRESS,
                provinceCode.getData().toString() + cityCode.getData().toString() + districtCode.getData().toString());
        return hospital;
    }
}
