package com.tgyf.oss.anno;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface EnableOssListener {
    /**
     * 当value所对应配置文件中的值为upload时,OSS上传;为download时,OSS下载.
     * @return
     */
    String value() default "upload";
}

