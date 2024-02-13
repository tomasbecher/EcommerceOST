package com.ost.ecommerce.user.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserCreate {

    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private Boolean receiveNewsletter;
    private Boolean admin;

    public Boolean isAdmin(){
        return admin;
    }
}
