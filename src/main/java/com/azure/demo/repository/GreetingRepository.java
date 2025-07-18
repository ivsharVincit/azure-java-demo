package com.azure.demo.repository;

import com.azure.demo.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    List<Greeting> findAll(Sort sort);
}