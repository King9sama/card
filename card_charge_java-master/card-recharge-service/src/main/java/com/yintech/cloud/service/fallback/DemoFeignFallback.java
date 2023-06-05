package com.yintech.cloud.service.fallback;

import com.yintech.cloud.service.feign.DemoFeignService;
import com.yintech.commons.response.BaseResponse;

/**
 * @author kai.tang
 * @version 1.0-SNAPSHOT
 * @date 2020/5/18 11:34
 */

public class DemoFeignFallback implements DemoFeignService {


    @Override
    public BaseResponse getDemo() {
        //失败处理
        return BaseResponse.error();
    }
}
