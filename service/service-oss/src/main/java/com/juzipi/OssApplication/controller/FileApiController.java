package com.juzipi.OssApplication.controller;

import com.juzipi.OssApplication.service.FileService;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author juzipi
 * @Date 2021/6/1 14:44
 * @Info
 */
@Api("文件上传")
@RestController
@RequestMapping("api/oss/file")
public class FileApiController extends BaseController {


    @Autowired
    private FileService fileService;


    @ApiOperation("上传文件")
    @PostMapping("upload")
    public Result uploadFile(MultipartFile file){
        String path = fileService.upload(file);
        return judgmentResult(path);
    }


}
