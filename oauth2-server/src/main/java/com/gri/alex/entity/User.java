package com.gri.alex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {

    private String username;
    private String password;
    private String[] roles;

}
