package com.example.test.controllers;

import com.example.test.entities.Office;
import com.example.test.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {
    @Autowired
    private OfficeRepository repository;

    @GetMapping("")
    public Page<Office> getAllOffice(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "asc") String orderBy
    ){
        Order order;
        if(orderBy.equals("asc")){
            System.out.println("asc");
            order = new Order(Sort.Direction.ASC,sortBy);
        }else{
            System.out.println("desc");
            order = new Order(Sort.Direction.DESC,sortBy);
        }
        Sort sort = Sort.by(order);
        return repository.findAll(PageRequest.of(page,pageSize,sort));
    }
}
