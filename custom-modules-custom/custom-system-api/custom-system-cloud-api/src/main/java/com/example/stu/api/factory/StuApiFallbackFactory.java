package com.example.stu.api.factory;

import com.example.stu.api.IStuApi;
import com.example.stu.api.fallback.StuApiFallback;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author w
 */
@Component
public class StuApiFallbackFactory implements FallbackFactory<IStuApi> {

    @Override
    public IStuApi create(Throwable cause) {
        StuApiFallback fallback = new StuApiFallback();
        fallback.setCause(cause);
        return fallback;
    }
}
