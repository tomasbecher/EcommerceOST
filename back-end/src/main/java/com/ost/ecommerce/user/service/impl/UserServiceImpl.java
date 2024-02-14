package com.ost.ecommerce.user.service.impl;

import com.ost.ecommerce.error.exceptions.NotFoundException;
import com.ost.ecommerce.error.exceptions.OperationNotValidException;
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
        validateUserCreate(userCreate);
        Person person = setPerson(userCreate.getName(), userCreate.getLastName(), userCreate.getEmail());
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
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("user-not-found","User not found.")
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
        return personService.save(
                new Person(name,lastName,email)
        );
    }

    private void validateUserCreate(UserCreate userCreate){
        if (userCreate.getUsername().isBlank() || userCreate.getPassword().isBlank() ||
                userCreate.getName().isBlank() || userCreate.getLastName().isBlank()
                || userCreate.getEmail().isBlank() || userCreate.getReceiveNewsletter() == null
        )
            throw makeError("All fields are required.");
        if (!validateOnlyLetter(userCreate.getUsername()))
            throw makeError("Name field should contain only letters.");
        if (!validateOnlyLetter(userCreate.getLastName()))
            throw makeError("Last name field should contain only letters.");
        if (!validateEmail(userCreate.getEmail()))
            throw makeError("Please enter a valid email address format.");
        if (usernameAlreadyExists(userCreate.getUsername()))
            throw makeError("This username is already taken. Please choose a different one.");
    }

    private boolean validateOnlyLetter(String attr){
        return attr.matches("^[a-zA-Z]+$");
    }

    private boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }

    private boolean usernameAlreadyExists(String username){
        return findByUsername(username).isPresent();
    }

    private OperationNotValidException makeError(String msg, Object ...args) {
        return new OperationNotValidException("user-create", msg, args);
    }

}
