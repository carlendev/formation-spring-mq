package com.orange.ivdev.formation.spring.formationspringcrudmq.Service;

import com.orange.ivdev.formation.spring.formationspringcrudmq.Entity.User;
import com.orange.ivdev.formation.spring.formationspringcrudmq.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> byId(final int id) {
        return this.userRepository.findById(id);
    }

    public List<User> all() {
        return this.userRepository.findAll();
    }

    public User save(final User user) {
        return this.userRepository.save(user);
    }
}
