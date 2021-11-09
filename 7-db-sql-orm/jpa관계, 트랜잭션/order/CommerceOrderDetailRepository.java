package com.example.commerce.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommerceOrderDetailRepository extends JpaRepository<CommerceOrderDetail, CommerceOrderDetailId> {
}
