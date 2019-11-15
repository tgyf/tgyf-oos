package com.tgyf.oss.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OssOperation {
    /** 上传 */
    UPLOAD("upload","上传"),
    /** 下载 */
    DOWNLOAD("download","下载");
    private String code;
    private String msg;

    /**
     * 获取操作枚举
     * @param value
     * @return
     */
    public static OssOperation getInstanceByCode(String value) {
        for(OssOperation ossOperation:values()){
            if(ossOperation.code.equalsIgnoreCase(value)){
                return ossOperation;
            }
        }
        throw new IllegalArgumentException();
    }
}
