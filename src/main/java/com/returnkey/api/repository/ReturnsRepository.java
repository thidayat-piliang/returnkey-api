package com.returnkey.api.repository;

import com.returnkey.api.entity.ReturnsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnsRepository extends JpaRepository<ReturnsEntity, String> {
    ReturnsEntity findByOrderIdAndEmailAddress(String orderId, String emailAddress);
}
