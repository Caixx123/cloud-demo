package com.atguigu.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")//Spring Boot提供的这个批量绑定注解，在nacos下，无需@RefreshScope就可实现自动刷新配置
@Data
public class OrderProperties {


    String timeout;

    String autoConfirm;

    String dbUrl;
}
