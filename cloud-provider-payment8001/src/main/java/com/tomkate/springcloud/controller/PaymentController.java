package com.tomkate.springcloud.controller;

import com.tomkate.springcloud.entities.CommomResult;
import com.tomkate.springcloud.entities.Payment;
import com.tomkate.springcloud.service.Impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Tom
 * @version 1.0
 * @date 2021/1/5 16:24
 */
@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentServiceImpl paymentServiceImpl;

    @PostMapping(value = "/payment/create")
    public CommomResult create(@RequestBody Payment payment) {
        int result = paymentServiceImpl.create(payment);
        log.info("******插入结果为：" + result);
        if (result > 0) {
            return new CommomResult(200, "插入数据库成功", result);
        } else {
            return new CommomResult(500, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommomResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentServiceImpl.getPaymentById(id);
        log.info("******当前查询结果为：" + payment);
        if (null != payment) {
            return new CommomResult(200, "查询成功", payment);
        } else {
            return new CommomResult(500, "没有对应记录，查询ID：" + id, null);
        }
    }
}
