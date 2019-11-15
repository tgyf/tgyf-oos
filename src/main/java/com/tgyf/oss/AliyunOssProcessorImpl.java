package com.tgyf.oss;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.tgyf.oss.properties.AliyunOssProperty;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
public class AliyunOssProcessorImpl implements AliyunOssProcessor {
    private OSSClient ossClient;
    private AliyunOssProperty aliyunOssProperty;

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
     * 获取可用的合法上传URL
     *
     * @param fileName 文件名称
     * @return URL字符串
     */
    @Override
    public String getUploadUrl(String directory, String fileName) {
        //基础数据备
        String bucket = aliyunOssProperty.getBucket();
        String dir = getDirectory(directory);
        long expireTime = aliyunOssProperty.getSignatureExpireSeconds();
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        //生成签名数据
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, dir + fileName, HttpMethod.POST);
        request.setExpiration(expiration);
        URL signedUrl = ossClient.generatePresignedUrl(request);
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
        //基础数据备
        String bucket = aliyunOssProperty.getBucket();
        String dir = getDirectory(directory);
        long expireTime = aliyunOssProperty.getSignatureExpireSeconds();
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        //生成签名数据
        URL signedUrl = ossClient.generatePresignedUrl(bucket, dir + fileName, expiration);
        return signedUrl.toString();
    }

    /**
     * 获取可用的合法签名
     *
     * @return 阿里云OSS客户端签名
     */
    @Override
    public Map<String, String> getSignature(String directory) throws UnsupportedEncodingException {
        //基础数据备
        String endpoint = aliyunOssProperty.getEndpoint();
        String bucket = aliyunOssProperty.getBucket();
        String dir = getDirectory(directory);
        long expireTime = aliyunOssProperty.getSignatureExpireSeconds();
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        //POST方式上传文件令牌准备
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);
        //组装返回数据
        Map<String, String> respMap = new LinkedHashMap<>();
        respMap.put("accessId", aliyunOssProperty.getAccessKeyId());
        respMap.put("policy", encodedPolicy);
        respMap.put("postSignature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", "http://" + bucket + "." + endpoint);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return respMap;
    }
}

