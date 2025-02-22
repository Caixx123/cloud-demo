package com.atguigu.order.feign;

import com.atguigu.order.feign.fallback.ProductFeignClientFallback;
import com.atguigu.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class) //feign客户端
public interface ProductFeignClient {

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable("id") Long productId);
}
