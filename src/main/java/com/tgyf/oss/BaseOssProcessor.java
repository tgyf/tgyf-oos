package com.tgyf.oss;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public interface BaseOssProcessor {
    Map<String, String> getSignature(String directory) throws UnsupportedEncodingException;
    /**
     * 获取可用的合法上传URL
     *
     * @param fileName 文件名称
     * @return URL字符串
     */
    String getUploadUrl(String directory, String fileName);

    /**
     * 获取可用的合法下载URL
     *
     * @param fileName 文件名称
     * @return URL字符串
     */
    String getDownloadUrl(String directory, String fileName);
}
