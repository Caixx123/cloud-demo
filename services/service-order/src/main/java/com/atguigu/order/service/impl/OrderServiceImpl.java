package com.atguigu.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.order.bean.Order;
import com.atguigu.order.feign.ProductFeignClient;
import com.atguigu.order.service.OrderService;
import com.atguigu.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    ProductFeignClient productFeignClient;

    @SentinelResource(value = "createOrder",blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = getProductFromRemoteWithLoadBalanceAnno(productId);

        //使用Feign
        Product product = productFeignClient.getProduct(productId);
        Order order = new Order();


        order.setId(1L);
        order.setUserId(233L);
        order.setNickName("csj");
        order.setAddress("sdfsd");

        //TODO RPC
        order.setTotalPrice(product.getPrice().multiply(new BigDecimal(product.getNumber())));

        order.setProducts(Arrays.asList(product));

        return order;
    }

    //兜底回调
    public Order createOrderFallback(Long productId, Long userId, BlockException e) {

        Order order = new Order();


        order.setId(0L);
        order.setUserId(userId);
        order.setNickName("000");
        order.setAddress("异常信息::" + e.getClass());

        return order;
    }

    //远程调用
    public Product getProductFromRemote(Long productId) {

        //1、获取商品服务所在的所有机器ip+port
        ServiceInstance serviceInstance = discoveryClient.getInstances("service-product").get(0);
        //url

        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + productId;
        log.info("url::"+url);
        //2、给远程发送请求
        Product forObject = restTemplate.getForObject(url, Product.class);
        return forObject;

    }

    //负载均衡远程调用
    public Product getProductFromRemoteWithLoadBalance(Long productId) {

        //1、获取商品服务所在的所有机器ip+port
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        //url

        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("url::"+url);
        //2、给远程发送请求
        Product forObject = restTemplate.getForObject(url, Product.class);
        return forObject;

    }
    //注解式负载均衡远程调用
    public Product getProductFromRemoteWithLoadBalanceAnno(Long productId) {
        String url = "http://service-product/product/" + productId;
        //2、给远程发送请求
        Product forObject = restTemplate.getForObject(url, Product.class);
        return forObject;

    }
}
