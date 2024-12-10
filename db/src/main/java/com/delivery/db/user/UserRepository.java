package com.delivery.db.user;

import com.delivery.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // select * from user where id = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, UserStatus userStatus);

    // select * from user where email = ? and password = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus userStatus);
}
