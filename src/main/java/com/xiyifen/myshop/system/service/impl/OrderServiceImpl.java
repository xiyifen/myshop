package com.xiyifen.myshop.system.service.impl;

import com.xiyifen.myshop.system.entity.Order;
import com.xiyifen.myshop.system.mapper.OrderMapper;
import com.xiyifen.myshop.system.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiyifen
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
