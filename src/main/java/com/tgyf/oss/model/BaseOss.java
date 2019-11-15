package com.tgyf.oss.model;

import com.tgyf.oss.constant.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class BaseOss extends AbstractConvert{
    /** 合法上传/下载URL */
    private String url;
    /** 文件名称 */
    @NotBlank(message = "fileName can not be null.please find it in your parameter.")
    private String fileName;
    /** 响应信息 */
    private String message;
    /** 响应码 */
    @Builder.Default
    private ResultCode code = ResultCode.SUCCESS;
    /** 响应判断 */
    public boolean isSuccess() {
        return code == ResultCode.SUCCESS;
    }
}

