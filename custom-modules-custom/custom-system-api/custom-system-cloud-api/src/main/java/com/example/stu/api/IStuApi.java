package com.example.stu.api;

import com.example.stu.api.factory.StuApiFallbackFactory;
import org.jeecg.common.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author w
 */
@Component
@FeignClient(contextId = "CustomStuRemoteApi", value = ServiceNameConstants.SERVICE_STU,
    fallbackFactory = StuApiFallbackFactory.class)
public interface IStuApi {

}
