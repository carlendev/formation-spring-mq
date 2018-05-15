package com.orange.ivdev.formation.spring.formationspringcrudmq.Repository;

import com.orange.ivdev.formation.spring.formationspringcrudmq.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
