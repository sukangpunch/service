package com.delivery.db.userorder;

import com.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {
    // 특정 유저의 모든 주문
    // SELECT * FROM user_order WHERE user_id=? AND status=? ORDER BY id DESC;
    List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    // SELECT * FROM user_order WHERE user_id=? AND status in (?, ?, ?...) ORDER BY id DESC;
    List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId,List<UserOrderStatus> status);

    // 특정 유저의 특정 주문
    // SELECT * FROM user_order WHERE id=? AND status=? And user_id=? ;
    Optional<UserOrderEntity> findAllByIdAndStatusAndUserId(Long id, UserOrderStatus status, Long userId);

    // 특정 유저의 특정 주문(status 없이)
    // SELECT * FROM user_order WHERE id=? And user_id=? ;
    Optional<UserOrderEntity> findAllByIdAndUserId(Long id, Long userId);

}
