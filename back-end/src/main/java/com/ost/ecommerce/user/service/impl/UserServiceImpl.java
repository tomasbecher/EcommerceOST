package com.ost.ecommerce.user.service.impl;

import com.ost.ecommerce.permissions.repository.entity.Role;
import com.ost.ecommerce.permissions.repository.entity.UserRole;
import com.ost.ecommerce.permissions.repository.entity.UserRolePK;
import com.ost.ecommerce.permissions.service.RoleService;
import com.ost.ecommerce.person.repository.Person;
import com.ost.ecommerce.person.service.PersonService;
import com.ost.ecommerce.subscription.repository.entity.ESubscriptionType;
import com.ost.ecommerce.subscription.repository.entity.UserSubscription;
import com.ost.ecommerce.subscription.service.SubscriptionService;
import com.ost.ecommerce.user.repository.UserRepository;
import com.ost.ecommerce.user.repository.entity.User;
import com.ost.ecommerce.user.service.UserService;
import com.ost.ecommerce.user.service.domain.UserCreate;
import com.ost.ecommerce.user.service.domain.UserProfile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    private final SubscriptionService subscriptionService;

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
        subscriptionService.create(user.getId());
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findOrFail(Integer userId) {
        // TODO agregar a ExceptionHandler
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException(
                        "User not found.")
        );
    }

    @Override
    public UserProfile getUserProfile() {
        User user = roleService.getCurrentUser();
        Person person = personService.getByIdOrFail(user.getPersonId());
        UserSubscription userSubscription = subscriptionService.getByUserIdOrFail(user.getId());
        UserProfile userProfile = userSubscription.getSubscriptionType().equals(ESubscriptionType.NO_SUBSCRIPTION)
                ? createFromUnSubscribedUser(person, userSubscription, user.getReceiveNewsletter())
                : createFromSubscribedUser(person, userSubscription,user.getReceiveNewsletter());
        userProfile.setAvailableSubscriptionTypeList(
                Arrays.stream(ESubscriptionType.values()).collect(Collectors.toSet())
        );
        return userProfile;
    }

    private UserProfile createFromSubscribedUser(Person person, UserSubscription userSubscription, Boolean receiveNewsletter){
        return new UserProfile(
                person.getName(),
                person.getLastName(),
                person.getEmail(),
                receiveNewsletter,
                userSubscription.getSubscriptionType(),
                userSubscription.getSubscriptionState(),
                userSubscription.getStartDate(),
                userSubscription.getExpirationDate(),
                null,
                "Only subscribers users can see this content."
        );
    }

    private UserProfile createFromUnSubscribedUser(Person person, UserSubscription userSubscription, Boolean receiveNewsletter){
        return new UserProfile(
                person.getName(),
                person.getLastName(),
                person.getEmail(),
                receiveNewsletter,
                userSubscription.getSubscriptionType(),
                userSubscription.getSubscriptionState(),
                null,
                null,
                null,
                null
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
