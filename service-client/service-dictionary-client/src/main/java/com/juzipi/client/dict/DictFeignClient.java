package com.juzipi.client.dict;

import com.juzipi.commonutil.tool.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author juzipi
 * @Date 2021/5/8 21:39
 * @Info dict远程调用组件
 */
@FeignClient("service-dictionary")
public interface DictFeignClient {


    @GetMapping("admin/dict/getName/{dictCode}/{dictValue}")
    public String getName(@PathVariable("dictCode") String dictCode, @PathVariable("dictValue") String dictValue);

    @GetMapping("admin/dict/getName/{dictValue}")
    public String getName(@PathVariable("dictValue") String dictValue);

}
