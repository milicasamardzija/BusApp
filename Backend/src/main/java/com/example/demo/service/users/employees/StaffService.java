package com.example.demo.service.users.employees;

import com.example.demo.repository.users.employees.StaffRepository;
import com.example.demo.model.users.Address;
import com.example.demo.model.users.Role;
import com.example.demo.model.users.UserRegistrationRequest;
import com.example.demo.model.users.employees.Staff;
import com.example.demo.service.users.AddressService;
import com.example.demo.service.users.auth.RoleService;
import com.example.demo.service.users.UserRegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private UserRegistrationRequestService userRegistrationRequestService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AddressService addressService;

    public void addStaffMember(int id) {
        UserRegistrationRequest user = this.userRegistrationRequestService.findById(id);
        Staff staff = new Staff();
        Role role = this.roleService.findByName("ROLE_STAFF");
        staff.setName(user.getName());
        staff.setSurname(user.getSurname());
        staff.setPassword(passwordEncoder.encode(user.getPassword()));
        staff.setAddress(addressService.save(new Address(user.getCountry(), user.getCity(), user.getStreet(), user.getNumber())));
        staff.setEmail(user.getEmail());
        staff.setEnabled(true);
        staff.setTelephone(user.getTelephone());
        staff.setRole(role);
        this.staffRepository.save(staff);
    }
}
