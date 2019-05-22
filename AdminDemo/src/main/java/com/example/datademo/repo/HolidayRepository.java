package com.example.datademo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.datademo.model.Holiday;
@Repository
public interface HolidayRepository extends JpaRepository<Holiday,Long>{

}
