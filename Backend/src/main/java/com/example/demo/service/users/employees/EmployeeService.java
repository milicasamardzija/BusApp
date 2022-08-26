package com.example.demo.service.users.employees;

import com.example.demo.dto.users.employees.EmployeeRequest;
import com.example.demo.model.users.employees.Employee;
import com.example.demo.repository.users.AddressRepository;
import com.example.demo.repository.users.employees.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Employee> getAll() {
        return  this.employeeRepository.findAll();
    }

    public void changeEmployee(EmployeeRequest employee) {
        Employee employeeExist = this.employeeRepository.findById(employee.id);
        employeeExist.setName(employee.name);
        employeeExist.setSurname(employee.surname);
        employeeExist.setTelephone(employee.telephone);
        employeeExist.setAddress(addressRepository.save(employee.address));
        employeeExist.setEmployeeType(employee.employeeType);
        employeeExist.setSalary(employee.salary);
        this.employeeRepository.save(employeeExist);
    }

    public void deleteById(int id) {
        this.employeeRepository.deleteById(id);
    }
}
