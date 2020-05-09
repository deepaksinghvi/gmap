package com.ms.gmap.user.service;

import com.ms.gmap.user.domain.User;

public interface UserService {

  User createUser(User user);

  User getUser(Long tenantId, Long userId);

  User getUser(String tenantId, String userId);

  void deleteUser(Long id);

  User updateUser(User user);

}
