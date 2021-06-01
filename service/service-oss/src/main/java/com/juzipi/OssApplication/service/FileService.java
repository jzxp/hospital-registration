package com.juzipi.OssApplication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author juzipi
 * @Date 2021/6/1 14:46
 * @Info
 */
public interface FileService {


    String upload(MultipartFile file);


}
