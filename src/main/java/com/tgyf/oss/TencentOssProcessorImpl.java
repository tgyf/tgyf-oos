package com.tgyf.oss;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.tgyf.oss.properties.TencentOssProperty;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

@AllArgsConstructor
public class TencentOssProcessorImpl implements BaseOssProcessor{
    private COSClient cosClient;
    private TencentOssProperty tencentOssProperty;
    /**
     * 获取合法地址文件夹
     *
     * @param directory 文件夹地址
     * @return 合法地址文件夹
     */
    private String getDirectory(String directory) {
        while (!StringUtils.isEmpty(directory) && "/".equals(String.valueOf(directory.charAt(0)))) {
            directory = directory.substring(1);
        }
        return directory;
    }

    /**
     * 获取可用的合法签名
     *
     * @return 腾讯云OSS客户端签名
     */
    @Override
    public Map<String, String> getSignature(String directory) throws UnsupportedEncodingException {
        return null;
    }
    /**
     * 获取可用的合法上传URL
     *
     * @param fileName 文件名称
     * @return URL字符串
     */
    @Override
    public String getUploadUrl(String directory, String fileName) {
        //基础数据准备
        String bucket = tencentOssProperty.getBucket();
        String dir = getDirectory(directory);
        long expireTime = tencentOssProperty.getSignatureExpireSeconds();
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        //生成签名数据
        URL signedUrl = cosClient.generatePresignedUrl(bucket, dir.concat(fileName), expiration, HttpMethodName.PUT);
        return signedUrl.toString();
    }

    /**
     * 获取可用的合法下载URL
     *
     * @param fileName 文件名称
     * @return URL字符串
     */
    @Override
    public String getDownloadUrl(String directory, String fileName) {
        //基础数据准备
        String bucket = tencentOssProperty.getBucket();
        String dir = getDirectory(directory);
        long expireTime = tencentOssProperty.getSignatureExpireSeconds();
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        GeneratePresignedUrlRequest req =new GeneratePresignedUrlRequest(bucket, dir.concat(fileName), HttpMethodName.GET);
        req.setExpiration(expiration);
        //生成签名数据
        URL signedUrl = cosClient.generatePresignedUrl(req);
        return signedUrl.toString();
    }
}
