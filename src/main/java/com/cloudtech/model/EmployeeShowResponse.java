package com.cloudtech.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeShowResponse {

    private Long id;
    private String name;
    private String department;
    private Double salary;




}
