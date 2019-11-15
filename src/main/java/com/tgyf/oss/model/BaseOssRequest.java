package com.tgyf.oss.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseOssRequest {
    /**
     * 方法名
     */
    protected String methodName;

    /**
     * 方法参数信息
     */
    protected List<Object> parames;

}

