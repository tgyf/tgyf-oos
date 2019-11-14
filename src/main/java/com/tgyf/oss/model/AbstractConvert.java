package com.tgyf.oss.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class AbstractConvert implements Serializable {
    /**
     * 实体转换
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T convert(Class<T> clazz) {
        return JSONObject.parseObject(JSONObject.toJSONString(this), clazz);
    }

}
