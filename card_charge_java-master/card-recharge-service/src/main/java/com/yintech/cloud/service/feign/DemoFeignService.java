package com.yintech.cloud.service.feign;

import com.yintech.cloud.service.fallback.DemoFeignFallback;
import com.yintech.commons.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kai.tang
 * @version 1.0-SNAPSHOT
 * @date 2020/5/18 11:32
 */
@FeignClient(value = "order-service", path = "/order-service", fallback = DemoFeignFallback.class)
public interface DemoFeignService {

    @GetMapping("/demo/get")
    BaseResponse getDemo();
}
