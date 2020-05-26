package com.eyatoo.service;

import java.util.Map;

/**
 * <p>支付接口层</p>
 *
 * @author att
 * @date 2018年5月27日
 * @since jdk1.8
 * @version 1.0
 */
public interface PaymentService {

    Map<String,String> xcxPayment(String orderNo, double money,String openId) throws Exception;

    int xcxNotify(Map<String,Object> map) throws Exception;
}
