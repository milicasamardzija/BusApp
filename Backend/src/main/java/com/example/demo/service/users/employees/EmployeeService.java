package com.example.demo.service.users.employees;

import com.example.demo.dto.users.employees.EmployeeRequest;
import com.example.demo.enums.BussStaffType;
import com.example.demo.enums.EmployeeType;
import com.example.demo.model.users.employees.BusController;
import com.example.demo.model.users.employees.BusDriver;
import com.example.demo.model.users.employees.Employee;
import com.example.demo.repository.users.AddressRepository;
import com.example.demo.repository.users.RoleRepository;
import com.example.demo.repository.users.employees.EmployeeRepository;
import com.example.demo.service.users.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private BusDriverService busDriverService;
    @Autowired
    private BusControllerService busControllerService;
    @Autowired
    private RoleService roleService;

    public List<Employee> getAll() {
        List<BusDriver> busDrivers = this.busDriverService.findAll();
        List<BusController> busControllers = this.busControllerService.findAll();
        List<Employee> employees = new ArrayList<>();

        for (BusDriver busDriver : busDrivers) {
            employees.add(new Employee(busDriver.getId(), busDriver.getName(), busDriver.getSurname(), busDriver.getEmail(), busDriver.getTelephone(), busDriver.getAddress(), busDriver.getPicture(), busDriver.getRole(), busDriver.getEmployeeType(), busDriver.getSalary()));
        }

        for (BusController busDriver : busControllers) {
            employees.add(new Employee(busDriver.getId(), busDriver.getName(), busDriver.getSurname(), busDriver.getEmail(), busDriver.getTelephone(), busDriver.getAddress(), busDriver.getPicture(), busDriver.getRole(), busDriver.getEmployeeType(), busDriver.getSalary()));
        }
        
        return  employees;
    }

    public void saveEmployee(EmployeeRequest employee) {
        if (employee.type.equals("Sluzbenik")){
            this.addNewStaff(employee);
        } else if (employee.type.equals("Vozac")){
            this.addNewBusDriver(employee);
        }else if (employee.type.equals("Kondukter")){
            this.addNewBusController(employee);
        }
    }

    private void addNewBusController(EmployeeRequest employee) {
        BusController busController = new BusController();
        busController.setRole(this.roleService.findByName("ROLE_STAFF"));
        busController.setName(employee.name);
        busController.setSurname(employee.surname);
        busController.setEmail(employee.email);
        busController.setTelephone(employee.telephone);
        busController.setAddress(addressRepository.save(employee.address));
        busController.setSalary(employee.salary);
        busController.setEmployeeType(EmployeeType.BUS_STAFF);
        busController.setBussStaffType(BussStaffType.Kondukter);
        this.busControllerService.save(busController);
    }

    private void addNewBusDriver(EmployeeRequest employee) {
        BusDriver busDriver = new BusDriver();
        busDriver.setRole(this.roleService.findByName("ROLE_STAFF"));
        busDriver.setName(employee.name);
        busDriver.setSurname(employee.surname);
        busDriver.setTelephone(employee.telephone);
        busDriver.setAddress(this.addressRepository.save(employee.address));
        busDriver.setEmail(employee.email);
        busDriver.setSalary(employee.salary);
        busDriver.setEmployeeType(EmployeeType.BUS_STAFF);
        busDriver.setBussStaffType(BussStaffType.Vozac);
        this.busDriverService.save(busDriver);
    }

    private void addNewStaff(EmployeeRequest employee) {
        Employee employeeNew = new Employee();
        employeeNew.setRole(this.roleService.findByName("ROLE_STAFF"));
        employeeNew.setName(employee.name);
        employeeNew.setSurname(employee.surname);
        employeeNew.setTelephone(employee.telephone);
        employeeNew.setEmail(employee.email);
        employeeNew.setAddress(addressRepository.save(employee.address));
        employeeNew.setSalary(employee.salary);
        employeeNew.setEmployeeType(EmployeeType.STAFF);
        this.employeeRepository.save(employeeNew);
    }


    public void changeEmployee(EmployeeRequest employee) {
        Employee employeeExist = this.employeeRepository.findById(employee.id);
        employeeExist.setName(employee.name);
        employeeExist.setSurname(employee.surname);
        employeeExist.setTelephone(employee.telephone);
        employeeExist.setAddress(addressRepository.save(employee.address));
        employeeExist.setSalary(employee.salary);
        employeeExist.setEmail(employee.email);
        this.employeeRepository.save(employeeExist);
    }

    private EmployeeType getEmployeeType(String employeeType) {
        if (employeeType.equals("Sluzbenik")){
            return  EmployeeType.STAFF;
        } else if (employeeType.equals("Vozac")){
            return EmployeeType.BUS_STAFF;
        }else if (employeeType.equals("Kondukter")){
            return EmployeeType.BUS_STAFF;
        }
        return null;
    }

    public void deleteById(int id) {
        this.employeeRepository.deleteById(id);
    }

    public String getEmployeeType(int id, EmployeeType employeeType) {
        if (employeeType == EmployeeType.BUS_STAFF){
            BusDriver busDriver = this.busDriverService.getById(id);
            if (busDriver != null)
                return "Vozac";
            BusController busController = this.busControllerService.getById(id);
            if (busController != null)
                return "Kondukter";
        }
        return "Sluzbenik";
    }

    public Employee getById(int id) {
        return this.employeeRepository.findById(id);
    }
}
