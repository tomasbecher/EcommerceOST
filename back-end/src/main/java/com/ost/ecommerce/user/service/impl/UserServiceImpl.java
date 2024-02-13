package com.ost.ecommerce.user.service.impl;

import com.ost.ecommerce.permissions.repository.entity.Role;
import com.ost.ecommerce.permissions.repository.entity.UserRole;
import com.ost.ecommerce.permissions.repository.entity.UserRolePK;
import com.ost.ecommerce.permissions.service.RoleService;
import com.ost.ecommerce.person.repository.Person;
import com.ost.ecommerce.person.service.PersonService;
import com.ost.ecommerce.user.repository.UserRepository;
import com.ost.ecommerce.user.repository.entity.User;
import com.ost.ecommerce.user.service.UserService;
import com.ost.ecommerce.user.service.domain.UserCreate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(UserCreate userCreate) {
        Person person = setPerson(userCreate.getName(), userCreate.getLastName(), userCreate.getEmail());
        // TODO validar username no existente
        User user = userRepository.save(
                new User(
                        userCreate.getUsername(),
                    person.getId(),
                    passwordEncoder.encode(userCreate.getPassword()),
                    userCreate.getReceiveNewsletter()
                )
        );
        setRoles(user.getId(), userCreate.isAdmin());
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findOrFail(Integer userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException(
                        "User not found.")
        );
    }

    private void setRoles(Integer userId, Boolean isAdmin){
        List<Role> roles = new ArrayList<>();
        roleService.findByName("ROLE_USER").ifPresent(roles::add);
        if (isAdmin)
            roleService.findByName("ROLE_ADMIN").ifPresent(roles::add);
        roles.forEach(
                role -> roleService.saveUserRole(
                        new UserRole(
                                new UserRolePK(userId,role.getId())
                        )
                )
        );
    }

    private Person setPerson(String name, String lastName, String email){
        // TODO Agregar validaciones nombre y apellido solo letras, email que cumpla el formato
        return personService.save(
                new Person(name,lastName,email)
        );
    }

}
