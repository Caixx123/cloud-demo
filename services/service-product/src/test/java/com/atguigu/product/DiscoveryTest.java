package com.atguigu.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void test(){
        for (String service : discoveryClient.getServices()) {
            System.out.println(service);
            for (ServiceInstance instance : discoveryClient.getInstances(service)) {
                System.out.println("ip::"+instance.getHost() + "; port::" + instance.getPort());
            }
        }
    }
}
