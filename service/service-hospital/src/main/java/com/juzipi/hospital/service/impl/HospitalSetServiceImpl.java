package com.juzipi.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.hospital.mapper.HospitalSetMapper;
import com.juzipi.hospital.service.HospitalSetService;
import com.juzipi.inter.model.pojo.HospitalSet;
import org.springframework.stereotype.Service;

/**
 * @Author juzipi
 * @Date 2021/4/27 14:18
 * @Info Service 实现类
 */
@Service
//第一个参数是mapper，第二个是实体类，继承这个ServiceImpl它里面帮我们注入了mapper，所以就不用再注入mapper了
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {



}
