package com.example.datademo.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.datademo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{


	

}
