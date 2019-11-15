package com.tgyf.oss.aop;

import com.tgyf.oss.anno.EnableOssListener;
import com.tgyf.oss.constant.OssOperation;
import com.tgyf.oss.handler.OssHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class EnableOssAop {

    private OssHandler ossHandler;

    public EnableOssAop(OssHandler ossHandler) {
        this.ossHandler = ossHandler;
    }

    @Before(value = "@within(listener)", argNames = "listener")
    public void enableOss(JoinPoint joinPoint, EnableOssListener listener) {
        ossOperationRoute(joinPoint.getSignature().getName(), joinPoint.getArgs(), listener.value());
    }

    @Before(value = "@annotation(listener)", argNames = "listener")
    public void enableOssWithMethod(JoinPoint joinPoint, EnableOssListener listener) {
        ossOperationRoute(joinPoint.getSignature().getName(), joinPoint.getArgs(), listener.value());
    }

    /**
     * oss操作路由
     * @param name
     * @param args
     * @param value upload;download
     */
    private void ossOperationRoute(String name, Object[] args, String value) {
        OssOperation ossOperation = OssOperation.getInstanceByCode(value) ;
        switch (ossOperation) {
            case UPLOAD:
                ossHandler.upload(name,args);
                break;
            case DOWNLOAD:
                ossHandler.download(name,args);
                break;
            default:
                break;
        }
    }
}
