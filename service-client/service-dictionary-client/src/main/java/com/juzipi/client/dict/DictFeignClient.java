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


    /*
    feign远程调用的方法返回的值是对象的时候，该对象需要有无参构造
     */
    @GetMapping("admin/dict/getName/{dictCode}/{dictValue}")
    public String getName(@PathVariable("dictCode") String dictCode, @PathVariable("dictValue") String dictValue);

    //Result需要有无参构造
    @GetMapping("admin/dict/getName/{dictValue}")
    public Result getName(@PathVariable("dictValue") String dictValue);


}
