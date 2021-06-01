package com.juzipi.OssApplication.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.juzipi.OssApplication.service.FileService;
import com.juzipi.serviceutil.util.FileUploadUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author juzipi
 * @Date 2021/6/1 14:46
 * @Info
 */
@Service
public class FileServiceImpl implements FileService {


    @Override
    public String upload(MultipartFile file) {
        String path = FileUploadUtils.uploadOss(file);
        return path;
    }



}
