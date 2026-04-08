package com.cloudtech.controller;


import com.cloudtech.model.EmployeeAddRequest;
import com.cloudtech.model.EmployeeAddResponse;
import com.cloudtech.model.EmployeeShowResponse;
import com.cloudtech.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {



    private final EmployeeService service;



    //get all users and no input..therefore only return list
    @GetMapping("/show")
    public List<EmployeeShowResponse> showEmployees() {

        List<EmployeeShowResponse> var1 = service.showEmployees();
        
        
        return var1;
    }


    //add users AND setting response class to get desired data..so input is request..output is return
    @PostMapping("/add")
    public EmployeeAddResponse addEmployee(@RequestBody EmployeeAddRequest data) {


        EmployeeAddResponse var2 = service.addEmployee(data);

        return var2;

    }


    //update functionality..also showing response as the output..and the input is id of a

    @PutMapping("/update/{id}")
    public EmployeeShowResponse updateEmployee(@PathVariable Long id, @RequestBody EmployeeAddRequest data) {

        EmployeeShowResponse var3 = service.updateEmployee(id, data);
        return var3;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }



}
