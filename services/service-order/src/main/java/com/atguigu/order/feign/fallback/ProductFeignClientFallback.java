package com.atguigu.order.feign.fallback;

import com.atguigu.order.feign.ProductFeignClient;
import com.atguigu.product.bean.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProduct(Long productId) {
        System.out.println("兜底回调");
        return null;
    }
}
