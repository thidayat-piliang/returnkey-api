package com.returnkey.api.repository;

import com.returnkey.api.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, String> {

    List<OrdersEntity> findAll();

    @Query(value = "select coalesce(select distinct order_id || '|' || email_address from orders where order_id = :orderId and email_address = :emailAddress, 'Order Not Found') from dual", nativeQuery = true)
    String getPendingOrders(@Param("orderId") String orderId, @Param("emailAddress") String emailAddress);

    @Query(value = "select sum(price * quantity) from orders where order_id = :orderId and email_address = :emailAddress", nativeQuery = true)
    double getRefundAmount(@Param("orderId") String orderId, @Param("emailAddress") String emailAddress);

    @Modifying
    @Query(value = "update orders set return_status = 'AWAITING_APPROVAL' where order_id = :orderId and email_address = :emailAddress", nativeQuery = true)
    void updateOrdersReturnStatusToAwaitingApproval(@Param("orderId") String orderId, @Param("emailAddress") String emailAddress);

    @Query(value = "select a from OrdersEntity a " +
            "join ReturnsEntity b on b.orderId = a.orderId and b.emailAddress = a.emailAddress " +
            "where b.id = :returnId and a.sku = :itemId ")
    OrdersEntity getOrderByReturnIdAndItemId(@Param("returnId") String returnId, @Param("itemId") String itemId);

    @Query(value = "select distinct return_status from orders where order_id = :orderId", nativeQuery = true)
    List<String> getOrdersReturnStatus(@Param("orderId") String orderId);
}
