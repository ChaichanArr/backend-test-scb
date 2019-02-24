package com.example.demo.user.view;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UserRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String date_of_birth;
}
