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
}
