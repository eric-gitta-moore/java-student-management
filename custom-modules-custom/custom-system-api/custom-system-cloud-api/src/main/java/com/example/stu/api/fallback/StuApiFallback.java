package com.example.stu.api.fallback;

import com.example.stu.api.IStuApi;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author w
 */
@Slf4j
public class StuApiFallback implements IStuApi {

    @Setter
    private Throwable cause;
}
