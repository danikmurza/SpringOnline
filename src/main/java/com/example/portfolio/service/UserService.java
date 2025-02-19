package com.example.portfolio.service;

import com.example.portfolio.entity.User;
import com.example.portfolio.repository.RoleRepository;
import com.example.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
    }


    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        User users = userRepository.save(user);
        return users;
    }

    public User userFindByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Object updateUser(User body) {
        Object email = userRepository.findByEmail(body.getEmail());
        System.out.println(email);
        return "Updated";
    }

    public User registerNewUser(User dto) {

        String passwd = encoder.encode(dto.getPassword());
//        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
//        System.out.print(Arrays.asList(adminRole));
        user.setPassword(passwd);
        user.setEnabled(true);
        user.setUrlAvatar(dto.getUrlAvatar() == null ? "https://resume-dnc.s3.amazonaws.com/download.png" : dto.getUrlAvatar());
//        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        User r = userRepository.save(user);
        return r;
    }

    public boolean checkPassword(String password, String enPasswd) {
        return encoder.matches(password, enPasswd);
    }


    public boolean email(String e) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        return e.matches(emailRegex);
    }

    public boolean password(String passwd) {
        String p = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&.()–[{}]:;',?/*~$^+=<>]).{6,20}$";
        return passwd.matches(p);
    }
}
