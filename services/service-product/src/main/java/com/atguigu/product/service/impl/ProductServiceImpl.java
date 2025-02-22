package com.atguigu.product.service.impl;

import com.atguigu.product.bean.Product;
import com.atguigu.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long productId) {
        System.out.println(productId);
        Product product = new Product();
        product.setId(productId);
        product.setName("apple");
        product.setPrice(new BigDecimal("99"));
        product.setNumber(2);

//        try {
//            TimeUnit.SECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return product;
    }
}
