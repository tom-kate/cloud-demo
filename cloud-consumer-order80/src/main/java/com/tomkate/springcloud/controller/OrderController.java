package com.tomkate.springcloud.controller;

import com.tomkate.springcloud.entities.CommomResult;
import com.tomkate.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Tom
 * @version 1.0
 * @date 2021/1/5 17:59
 */
@RestController
@Slf4j
public class OrderController {

    /**
     * 服务提供者地址
     */
    private static final String PAYMENT_URL = "http://localhost:8001";

    /**
     * 请求类
     */
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/payment/create")
    public CommomResult<Payment> create(Payment payment) {
        log.info("******consumer-create接口");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommomResult.class);
    }

    @GetMapping(value = "consumer/payment/get/{id}")
    public CommomResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("******consumer-get接口");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommomResult.class);
    }

}
