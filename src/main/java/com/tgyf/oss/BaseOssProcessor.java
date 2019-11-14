package com.tgyf.oss;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface BaseOssProcessor {
    Map<String, String> getSignature(String directory) throws UnsupportedEncodingException;

}
