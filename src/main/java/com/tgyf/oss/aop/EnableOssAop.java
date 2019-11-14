package com.tgyf.oss.aop;

import com.tgyf.oss.handler.OssHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class EnableOssAop {

    private OssHandler ossHandler;

    public EnableOssAop(OssHandler ossHandler) {
        this.ossHandler = ossHandler;
    }
}
