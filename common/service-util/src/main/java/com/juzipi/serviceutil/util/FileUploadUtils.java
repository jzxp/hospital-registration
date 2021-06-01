package com.juzipi.serviceutil.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.serviceutil.properties.OssProperties;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @Author juzipi
 * @Date 2021/6/1 14:49
 * @Info
 */
public class FileUploadUtils {


    public static String uploadOss(MultipartFile file) {
        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        try {
            // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
            String endpoint = OssProperties.ENDPOINT;
            // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = OssProperties.ACCESS_KEY_ID;
            String accessKeySecret = OssProperties.SECRET;
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            String fileName = getFileName(file);
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(OssProperties.BUCKET, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            return OssProperties.PATH+fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("上传文件失败");
        }
    }


    /**
     * 生成文件名称
     * @param file
     * @return
     */
    private static String getFileName(MultipartFile file){
        String format = DateFormatUtils.format(new Date(),"yyyy/MM/dd");
        String filename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        return format+"/"+uuid+filename;
    }


}
