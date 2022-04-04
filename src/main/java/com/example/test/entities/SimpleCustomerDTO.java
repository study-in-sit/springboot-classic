package com.example.test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleCustomerDTO {
    private String customerName;
    private String phone;
    private String city;
    private String country;
    private SimpleEmployeeDTO salesPerson;

    private String salesRepEmployeeFirstName;
    private String salesRepEmployeeLastName;

    @JsonIgnore
    private SimpleEmployeeDTO sales;

    public String getSalesPerson() {
        return sales == null ? "-" : sales.getName();
    }
    public String getSalesRepEmployeeFirstName() {
        return sales == null ? "-" : sales.getFirstName();
    }
    public String getSalesRepEmployeeLastName() {
        return sales == null ? "-" : sales.getLastName();
    }
}
