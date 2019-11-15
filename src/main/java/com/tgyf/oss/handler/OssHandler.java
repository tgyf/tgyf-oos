package com.tgyf.oss.handler;

import com.tgyf.oss.BaseOssProcessor;
import com.tgyf.oss.constant.ResultCode;
import com.tgyf.oss.model.BaseOss;
import com.tgyf.oss.properties.OssExtendsProperty;
import com.tgyf.oss.properties.OssProperty;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class OssHandler {
    private final OssProperty ossProperty;
    private final OssExtendsProperty ossExtendsProperty;
    private BaseOssProcessor ossProcessor;

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
        Assert.notNull(baseOss,"BaseOss can not be null,please find it in your parameter. >> ".concat(name));
        String uploadUrl = ossProcessor.getUploadUrl(baseOss.getDirectory(), baseOss.getFileName());
        if (StringUtils.hasText(uploadUrl)) {
            baseOss.setUrl(uploadUrl);
            baseOss.setCode(ResultCode.SUCCESS);
        }
    }

    /**
     * 下载操作
     *
     * @param name
     * @param args
     */
    public void download(String name, Object[] args) {
        BaseOss baseOss = parseArgs(args);
        Assert.notNull(baseOss,"BaseOss can not be null,please find it in your parameter. >> ".concat(name));
        String downloadUrl = ossProcessor.getDownloadUrl(baseOss.getDirectory(), baseOss.getFileName());
        if (StringUtils.hasText(downloadUrl)) {
            baseOss.setUrl(downloadUrl);
            baseOss.setCode(ResultCode.SUCCESS);
        }
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
