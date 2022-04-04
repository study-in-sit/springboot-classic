package com.example.test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerIUpdateDTO {
    private Integer id;
    private String customerName;
    private String phone;
    private String city;
    private String country;
    private String addressLine1;
    private String contactLastName;
    private String contactFirstName;
}
