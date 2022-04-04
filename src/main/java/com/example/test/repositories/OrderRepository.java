package com.example.test.repositories;

import com.example.test.entities.Customer;
import com.example.test.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
