package com.returnkey.api.service;

import com.returnkey.api.entity.OrdersEntity;
import com.returnkey.api.entity.ReturnsEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface OmsService {
    List<OrdersEntity> getOrders();
    String getPendingOrders(String orderId, String emailAddress);
    ReturnsEntity returns(String orderId, String emailAddress);
    public ReturnsEntity getReturns(String returnId);
    void setReturnStatus(String id, String itemId, String status);
}
