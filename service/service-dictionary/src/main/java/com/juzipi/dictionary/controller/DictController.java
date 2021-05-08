package com.juzipi.dictionary.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.dictionary.service.DictService;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author juzipi
 * @Date 2021/4/30 8:54
 * @Info 字典管理
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping("admin/dict")
public class DictController extends BaseController {


    @Autowired
    private DictService dictService;


    @ApiOperation("根据id查询子数据")
    @GetMapping("getChild/{id}")
    public Result getChildData(@PathVariable Long id) {
        return judgmentResult(dictService.queryChildDataById(id));
    }


    @ApiOperation(value = "导出数据字典")
    @GetMapping("export")
    public void exportData(HttpServletResponse response) {
        dictService.exportData(response);
    }


    @ApiOperation(value = "导入字典数据")
    @PostMapping("import")
    public Result importData(MultipartFile uploadFiles) {
        return judgmentResult(dictService.importData(uploadFiles));
    }


    @ApiOperation(value = "根据字典编码和字典值查询字典名字")
    @GetMapping("getName/{dictCode}/{dictValue}")
    public Result getDictName(@PathVariable String dictCode, @PathVariable String dictValue) {
        return judgmentResult(dictService.queryDictByCodeAndValue(dictCode, dictValue));
    }


    @ApiOperation(value = "根据字典值查询字典名字")
    @GetMapping("getName/{dictValue}")
    public Result getDictName(@PathVariable String dictValue) {
        return judgmentResult(dictService.queryDictByCodeAndValue("",dictValue));
    }


}
