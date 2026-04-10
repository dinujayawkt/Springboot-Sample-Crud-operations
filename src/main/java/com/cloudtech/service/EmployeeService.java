package com.cloudtech.service;


import com.cloudtech.entity.Employee;
import com.cloudtech.model.EmployeeAddRequest;
import com.cloudtech.model.EmployeeAddResponse;
import com.cloudtech.model.EmployeeShowResponse;
import com.cloudtech.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;


@Log
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {




    private final EmployeeRepository repository;




    //add employee.........

    public EmployeeAddResponse addEmployee(EmployeeAddRequest request) {

        try{



            Employee employee = new Employee();
            employee.setName(request.getName());
            employee.setDepartment(request.getDepartment());
            employee.setSalary(request.getSalary());

            Employee storedEmployee = repository.save(employee);


            log.info("Employee added successfully with id: " + storedEmployee.getId());
            return new EmployeeAddResponse(
                    employee.getId(),
                    employee.getName(),
                    employee.getDepartment(),
                    employee.getSalary()
            );


        }catch (Exception e){
            log.severe("Error occurred while adding employee: " + e.getMessage());
            throw new RuntimeException("Failed to add employee", e);
        }


    }



    //update..........

    @SuppressWarnings("unused")
    public EmployeeShowResponse updateEmployee(Long id, EmployeeAddRequest request) {

        try{

            Employee employee = repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Employee not found with id: " + id));

            employee.setName(request.getName());
            employee.setDepartment(request.getDepartment());
            employee.setSalary(request.getSalary());
            repository.save(employee);


            log.info("Employee updated successfully with id: " + id);


            return new EmployeeShowResponse(
                    employee.getId(),
                    employee.getName(),
                    employee.getDepartment(),
                    employee.getSalary()
            );

        }catch (ResponseStatusException e){
            log.warning("Employee not found with id: " + id);
            throw e;
        }catch (Exception e){
            log.severe("Error occurred while updating employee with id: " + id + ". Error: " + e.getMessage());
            throw new RuntimeException("Failed to update employee with id: " + id, e);
        }

    }




    //delete..........

    public void deleteEmployee(Long id) {

        try{
            repository.existsById(id);


            if (!repository.existsById(id)) {
                throw new ResponseStatusException(NOT_FOUND, "Employee not found with id: " + id);
            }

            repository.deleteById(id);

            log.info("Employee deleted successfully with id: " + id);
        }catch (ResponseStatusException e){
            log.warning("Employee not found with id: " + id);
            throw e;
        }catch (Exception e){
            log.severe("Error occurred while deleting employee with id: " + id + ". Error: " + e.getMessage());
            throw new RuntimeException("Failed to delete employee with id: " + id, e);
        }
    }





    //show all.............

    public List<EmployeeShowResponse> showEmployees() {

        try{
            List<EmployeeShowResponse> var2 = repository.findAll().stream()
                    .map(employee -> new EmployeeShowResponse(
                            employee.getId(),
                            employee.getName(),
                            employee.getDepartment(),
                            employee.getSalary()
                    ))
                    .toList();


            log.info("Fetched " + var2.size() + " employees successfully");
            return var2;
        }catch (Exception e){
            log.severe("Error occurred while fetching employees: " + e.getMessage());
            throw new RuntimeException("Failed to fetch employees", e);
        }
    }
}
