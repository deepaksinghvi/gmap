package com.ms.gmap.user.repository;

import com.ms.gmap.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>,
    PagingAndSortingRepository<User, Long> {

  @Query("SELECT u FROM User u JOIN FETCH u.tenant t WHERE t.tenantId = :tenantId AND u.userid = :userId")
  Optional<User> findUser(@Param("tenantId") String tenantId, @Param("userId") String userId);

  @Query("SELECT u FROM User u JOIN FETCH u.tenant t WHERE t.id = :tenantId AND u.id = :userId")
  Optional<User> findUser(@Param("tenantId") Long tenantId, @Param("userId") Long userId);
}
