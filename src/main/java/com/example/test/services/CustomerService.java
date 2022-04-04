package com.example.test.services;

import com.example.test.entities.*;
import com.example.test.repositories.CustomerRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository repository;


    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ModelMapper modelMapper;


//    Page<Customer>

    public SimpleCustomerDTO getSimpleCustomerById(Integer customerId) {
        Customer customer = repository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer id " + customerId + "Does Not Exist !!!"));
        return modelMapper.map(customer, SimpleCustomerDTO.class);
//        return convertEntityToDto(customer);
    }

    public List<SimpleCustomerDTO> findAllByCreditLimitBetween(BigDecimal lower, BigDecimal upper) {
        return repository.findAllByCreditLimitBetween(lower, upper).stream().map(e -> modelMapper.map(e, SimpleCustomerDTO.class)).collect(Collectors.toList());
    }

    public Page<SimpleCustomerDTO> findAll(String sortBy, String sort, Integer page, Integer pageSize) {
        Sort sorting;
        if (sort.equals("asc")) {
            sorting = Sort.by(Sort.Direction.ASC, sortBy);
        } else {
            sorting = Sort.by(Sort.Direction.DESC, sortBy);
        }
        Page<Customer> entities = repository.findAll(PageRequest.of(page, pageSize, sorting));
        Page<SimpleCustomerDTO> dtoPage = entities.map(e -> modelMapper.map(e, SimpleCustomerDTO.class));
        return dtoPage;
    }

    public CustomerIUpdateDTO save(CustomerIUpdateDTO newCustomer) {
        Customer e = modelMapper.map(newCustomer, Customer.class);
        return modelMapper.map(repository.saveAndFlush(e), CustomerIUpdateDTO.class);
    }

    public CustomerIUpdateDTO delete(Integer id) {
        Customer e = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " does not exist !!!"));
        repository.deleteById(id);
        return modelMapper.map(e, CustomerIUpdateDTO.class);
    }

    public CustomerIUpdateDTO update(Integer id, CustomerIUpdateDTO updateDTO) {
        Customer e = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " does not exist !!!"));
        Customer newCustomer = modelMapper.map(updateDTO, Customer.class);
        return modelMapper.map(repository.saveAndFlush(newCustomer), CustomerIUpdateDTO.class);
    }

    private SimpleCustomerDTO convertEntityToDto(Customer customer) {
        SimpleCustomerDTO simpleCustomerDTO = new SimpleCustomerDTO();
        simpleCustomerDTO.setCustomerName(customer.getCustomerName());
        simpleCustomerDTO.setCity(customer.getCity());
        simpleCustomerDTO.setCountry(customer.getCountry());
        simpleCustomerDTO.setPhone(customer.getPhone());
        simpleCustomerDTO.setCustomerName(customer.getCustomerName());
        Employee employee = customer.getSalesRepEmployee();
        SimpleEmployeeDTO employeeDTO = new SimpleEmployeeDTO();
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        simpleCustomerDTO.setSales(employeeDTO);
        simpleCustomerDTO.setSalesRepEmployeeFirstName(employee.getFirstName());
        simpleCustomerDTO.setSalesRepEmployeeLastName(employee.getLastName());
        return simpleCustomerDTO;
    }
}

