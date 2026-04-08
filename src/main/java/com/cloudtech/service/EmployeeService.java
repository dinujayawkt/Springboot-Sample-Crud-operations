package com.cloudtech.service;


import com.cloudtech.entity.Employee;
import com.cloudtech.model.EmployeeAddRequest;
import com.cloudtech.model.EmployeeAddResponse;
import com.cloudtech.model.EmployeeShowResponse;
import com.cloudtech.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;




@Service
@RequiredArgsConstructor
public class EmployeeService {




    private final EmployeeRepository repository;




    //add employee.........

    public EmployeeAddResponse addEmployee(EmployeeAddRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());

        Employee storedEmployee = repository.save(employee);
        return new EmployeeAddResponse(
                storedEmployee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getSalary()
        );
    }



    //update..........

    @SuppressWarnings("unused")
    public EmployeeShowResponse updateEmployee(Long id, EmployeeAddRequest request) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Employee not found with id: " + id));

        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        repository.save(employee);


        return new EmployeeShowResponse(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getSalary()
        );
    }




    //delete..........

    public void deleteEmployee(Long id) {

        repository.existsById(id);


        if (!repository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Employee not found with id: " + id);
        }

        repository.deleteById(id);
    }





    //show all.............

    public List<EmployeeShowResponse> showEmployees() {

        List<EmployeeShowResponse> var2 = repository.findAll().stream()
                .map(employee -> new EmployeeShowResponse(
                        employee.getId(),
                        employee.getName(),
                        employee.getDepartment(),
                        employee.getSalary()
                ))
                .toList();
        return var2;
    }
}
