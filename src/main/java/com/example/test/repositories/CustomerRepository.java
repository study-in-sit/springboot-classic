package com.example.test.repositories;

import com.example.test.entities.Customer;
import com.example.test.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//    public List<Customer> findAllByCustomerNameContaining(String name);
//
//    public List<Customer> findAllByCustomerNameContaining(String name, Pageable pageable);
//
    public List<Customer> findAllByCreditLimitBetween(BigDecimal lower, BigDecimal upper);
//    public List<Customer> findAllByCreditLimitBetween(BigDecimal lower, BigDecimal upper, Pageable pageable);
//
//
//    public List<Customer> findAllByCustomerNameBetween(String lower, String upper);
}
