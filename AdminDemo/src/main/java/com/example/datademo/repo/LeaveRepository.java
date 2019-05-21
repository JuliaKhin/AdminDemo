package com.example.datademo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.datademo.model.Leave;


@Repository
public interface LeaveRepository extends JpaRepository<Leave,String> {

}
