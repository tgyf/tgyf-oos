package com.tgyf.oss.handler;

import com.tgyf.oss.properties.OssExtendsProperty;
import com.tgyf.oss.properties.OssProperty;

public class OssHandler {
    private final OssProperty ossProperty;
    private final OssExtendsProperty ossExtendsProperty;

    public OssHandler(OssProperty ossProperty, OssExtendsProperty ossExtendsProperty) {
    this.ossProperty=ossProperty;
    this.ossExtendsProperty=ossExtendsProperty;
    }

    /**
     * 通过反射进行上传操作
     * @param name
     * @param args
     */
    public void upload(String name, Object[] args) {
    }
    /**
     * 通过反射进行下载操作
     * @param name
     * @param args
     */
    public void download(String name, Object[] args) {
    }
}
