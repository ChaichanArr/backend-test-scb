package com.example.demo.user.view;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
