package com.tgyf.oss;

public interface AliyunOssProcessor extends BaseOssProcessor{
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
