# 对象存储聚合工具
## 项目说明：
* 项目是一个基于SpringBoot的对象存储极速开发聚合工具，项目fork于：
[https://gitee.com/w6513017/HDIS-Framework/tree/master/framework-oss](https://gitee.com/w6513017/HDIS-Framework/tree/master/framework-oss)

* 在fork项目中做了部分修改，将原项目调用模式修改为aop注解方式并聚合了腾讯云与七牛云的对象存储，增加了redis与线程池支持。

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.tgyf.framework</groupId>
    <artifactId>oss</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>


    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.4.RELEASE</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>

        <fastjson>1.2.62</fastjson>
        <aliyun-sdk-oss>2.8.3</aliyun-sdk-oss>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

        <!--阿里云Json序列化工具-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>${fastjson}</version>
        </dependency>

        <!--阿里云对象存储服务-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
            <version>${aliyun-sdk-oss}</version>
        </dependency>

        <!--工具包-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>
</project>
```

## 操作说明
![配置](images/C4D18EE2-6990-4e3e-87FF-008C1A4EFCE0.png)

application.yml
```
tgyf:
  oss:
    enable: true
    type: aliyun
    aliyun:
      endpoint: oss-cn-shenzhen.aliyuncs.com
      access-key-id: 
      access-key-secret: 
      bucket: 
      signature-expire-seconds: 
```

* 创建一个bean
```

@Component
public class Demo {
    //@EnableOssListener(value = "upload")获取上传url
    //其他操作参见 com.tgyf.oss.constant.OssOperation
    //Tip：需要参数且必须BaseOss或BaseOss的继承类
    @EnableOssListener(value = "download")
    public void test(BaseOss oss) {
        if (oss.isSuccess()) {
            System.out.println("oss = " + oss.getUrl());
        }else{
            System.out.println("oss = " + oss);
        }
    }
}
```
* 测试类：
```
@SpringBootTest
@RunWith(SpringRunner.class)
public class BasicApplicationTest {
    @Resource
    private Demo demo;
    @Test
    public void test() {
        BaseOss build = BaseOss.builder()
                .directory("test/auth-img/")
                .fileName("2d1ae6c6e2a8405a90c4fbc3fe230b44.jpg")
                .build();
        demo.test(build);
    }

}
```

## 相关API：
* 阿里云oss:
[https://www.alibabacloud.com/help/zh/doc-detail/32016.htm?spm=a2c63.p38356.b99.236.334af664GBODPY](https://www.alibabacloud.com/help/zh/doc-detail/32016.htm?spm=a2c63.p38356.b99.236.334af664GBODPY)
* 腾讯云cos：
[https://cloud.tencent.com/document/product/436/35217](https://cloud.tencent.com/document/product/436/35217)