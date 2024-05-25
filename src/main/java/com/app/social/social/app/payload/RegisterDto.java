package com.app.social.social.app.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto extends LoginDto{
    @NotNull
    @Size(min = 3 , message = "size must be greater than or equal 3")
    private String firstName;
    @NotNull
    @Size(min = 3 , message = "size must be greater than or equal 3")
    private String secondName;
    @NotNull
    @Email
    private String email;
}
