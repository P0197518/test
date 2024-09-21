package org.example.config;

import org.example.Enum.ResultData;
import org.example.Enum.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2023-11-30 20:22
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi
{
    @Override
    public ResultData getPayByOrderNo(String orderNo)
    {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}
