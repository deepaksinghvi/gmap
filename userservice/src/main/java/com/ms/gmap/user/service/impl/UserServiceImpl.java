package com.ms.gmap.user.service.impl;

import com.ms.gmap.user.domain.User;
import com.ms.gmap.user.repository.UserRepository;
import com.ms.gmap.user.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User getUser(Long tenantId, Long id) {
    Optional<User> result =  userRepository.findUser(tenantId, id);
    return result.isPresent()?result.get():null;
  }

  @Override
  public User getUser(String tenantId, String userId) {
    Optional<User> result =  userRepository.findUser(tenantId, userId);
    return result.isPresent()?result.get():null;
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public User updateUser(User user) {
    return userRepository.save(user);
  }
}
