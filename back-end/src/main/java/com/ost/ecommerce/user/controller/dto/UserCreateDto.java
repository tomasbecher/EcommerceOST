package com.ost.ecommerce.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateDto {

    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private Boolean receiveNewsletter;
    private Boolean admin;
}
