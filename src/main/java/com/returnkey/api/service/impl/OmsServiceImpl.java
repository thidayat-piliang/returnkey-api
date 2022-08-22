package com.returnkey.api.service.impl;

import com.returnkey.api.entity.OrdersEntity;
import com.returnkey.api.entity.ReturnsEntity;
import com.returnkey.api.repository.OrdersRepository;
import com.returnkey.api.repository.ReturnsRepository;
import com.returnkey.api.service.OmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OmsServiceImpl implements OmsService {
    private final OrdersRepository ordersRepository;
    private final ReturnsRepository returnsRepository;

    public List<OrdersEntity> getOrders(){
        return ordersRepository.findAll();
    }

    public String getPendingOrders(String orderId, String emailAddress){
        return ordersRepository.getPendingOrders(orderId, emailAddress);
    }

    public ReturnsEntity returns(String orderId, String emailAddress){

        if(returnsRepository.findByOrderIdAndEmailAddress(orderId, emailAddress) == null) {
            var returnOrder = new ReturnsEntity();

            returnOrder.setOrderId(orderId);
            returnOrder.setEmailAddress(emailAddress);
            returnOrder.setStatus("AWAITING_APPROVAL");
            returnOrder.setRefundAmount(ordersRepository.getRefundAmount(orderId, emailAddress));

            ordersRepository.updateOrdersReturnStatusToAwaitingApproval(orderId, emailAddress);

            return returnsRepository.save(returnOrder);
        }

        return null;
    }

    public ReturnsEntity getReturns(String returnId){
        return returnsRepository.findById(returnId).get();
    }

    public void setReturnStatus(String id, String itemId, String status){
        var returnOrder = returnsRepository.findById(id).get();
        var order = ordersRepository.getOrderByReturnIdAndItemId(id, itemId);

        order.setReturnStatus(status);

        if(status.equalsIgnoreCase("REJECTED")) {
            returnOrder.setRefundAmount(returnOrder.getRefundAmount() - (order.getQuantity() * order.getPrice()));
        }

        if(ordersRepository.getOrdersReturnStatus(returnOrder.getOrderId()).size() == 1){
            returnOrder.setStatus("COMPLETE");
        }
    }
}
