package com.returnkey.api.controller;

import com.returnkey.api.entity.OrdersEntity;
import com.returnkey.api.entity.ReturnsEntity;
import com.returnkey.api.repository.OrdersRepository;
import com.returnkey.api.service.OmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OmsController {

    private final OmsService omsService;

    @GetMapping("/orders")
    public List<OrdersEntity> getOrders() {

        return omsService.getOrders();
    }

    @PostMapping("/pending/returns")
    public String getPendingReturns(
            @RequestParam String orderId,
            @RequestParam String emailAddres
    ) {

        return omsService.getPendingOrders(orderId, emailAddres);
    }

    @PostMapping("/returns")
    public ReturnsEntity returns(
            @RequestParam String token
    ) {
        var strSplit = token.split("\\|");

        return omsService.returns(strSplit[0], strSplit[1]);
    }

    @GetMapping("/returns/{id}")
    public ReturnsEntity getReturn(
            @PathVariable String id
    ) {
        return omsService.getReturns(id);
    }

    @PutMapping("/returns/{id}/items/{itemId}/qc/{status}")
    public void setReturnStatus(
            @PathVariable String id,
            @PathVariable String itemId,
            @PathVariable String status
    ) {
        omsService.setReturnStatus(id, itemId, status);
    }
}
