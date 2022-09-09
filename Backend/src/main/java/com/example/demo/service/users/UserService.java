package com.example.demo.service.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.users.Role;

import com.example.demo.model.users.Address;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.users.auth.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private AddressService addressService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AddressService addressService, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
        this.roleService = roleService;
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User save(UserRequest user){
        User u = new User();

        Role role = roleService.findByName(user.role);
        if (role == null) {
            role = new Role(user.role);
            roleService.save(role);
        }
        u.setRole(role);

        u.setPassword(passwordEncoder.encode(user.password));
        u.setName(user.name);
        u.setSurname(user.surname);
        u.setTelephone(user.telephone);
        u.setEmail(user.email);
        u.setEnabled(true);
        u.setAddress(addressService.save(new Address(user.country, user.city, user.street, user.number)));
        this.userRepository.save(u);
        return u;
    }

    public void update(UserRequest updatedUser) {
        User user = this.userRepository.findByEmail(updatedUser.email);
        Role role = roleService.findByName(updatedUser.role);
        if (role == null) {
            role = new Role(updatedUser.role);
            roleService.save(role);
        }
        user.setRole(role);
        user.setPicture(updatedUser.image);
        user.setName(updatedUser.name);
        user.setSurname(updatedUser.surname);
        user.setTelephone(updatedUser.telephone);
        user.setEmail(updatedUser.email);
        user.setEnabled(true);
        user.setAddress(addressService.save(new Address(updatedUser.country, updatedUser.city, updatedUser.street, updatedUser.number)));
        this.userRepository.save(user);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public void changePassword(String password, User user) {
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
    }
}
