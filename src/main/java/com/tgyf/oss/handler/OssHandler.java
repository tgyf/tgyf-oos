package com.tgyf.oss.handler;

import com.tgyf.oss.model.BaseOss;
import com.tgyf.oss.properties.OssExtendsProperty;
import com.tgyf.oss.properties.OssProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OssHandler {
    private final OssProperty ossProperty;
    private final OssExtendsProperty ossExtendsProperty;

    public OssHandler(OssProperty ossProperty, OssExtendsProperty ossExtendsProperty) {
        this.ossProperty = ossProperty;
        this.ossExtendsProperty = ossExtendsProperty;
    }

    /**
     * 上传操作
     *
     * @param name
     * @param args
     */
    public void upload(String name, Object[] args) {
        BaseOss baseOss = parseArgs(args);
    }

    /**
     * 下载操作
     *
     * @param name
     * @param args
     */
    public void download(String name, Object[] args) {
        BaseOss baseOss = parseArgs(args);
    }

    /**
     * 解析参数
     * @param args
     * @return
     */
    public BaseOss parseArgs(Object[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }
        for (Object obj : args) {
            if (obj instanceof BaseOss) {
                return (BaseOss) obj;
            }
        }
        return null;
    }
}
