package com.example.test.controllers;

import com.example.test.entities.CustomerIUpdateDTO;
import com.example.test.entities.SimpleCustomerDTO;
import com.example.test.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public Page<SimpleCustomerDTO> getAllCustomers(@RequestParam(defaultValue = "id") String sortBy,
                                                   @RequestParam(defaultValue = "asc") String sort,
                                                   @RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        return customerService.findAll(sortBy,sort, page, pageSize);
    }

    @PostMapping("")
    public CustomerIUpdateDTO create(@RequestBody CustomerIUpdateDTO newCustomer) {
        return customerService.save(newCustomer);
    }

    @GetMapping("/credit/{lower}/{upper}")
    public List<SimpleCustomerDTO> getAllCustomersByCredit(@PathVariable BigDecimal lower, @PathVariable BigDecimal upper) {
        return customerService.findAllByCreditLimitBetween(lower, upper);
    }

    @DeleteMapping("/{id}")
    public CustomerIUpdateDTO deleteById(@PathVariable Integer id) {
        return customerService.delete(id);
    }

    @PutMapping("/{id}")
    public CustomerIUpdateDTO update(@RequestBody CustomerIUpdateDTO updateDTO, @PathVariable Integer id) {
        return customerService.update(id, updateDTO);
    }

    @GetMapping("/{id}")
    public SimpleCustomerDTO getCustomerById(@PathVariable Integer id) {
        return customerService.getSimpleCustomerById(id);
    }
}


