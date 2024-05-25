package com.app.social.social.app.payload;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull
    @Size(min = 5 , message = "size must be greater than or equal 5")
    private String username;
    @NotNull
    @Size(min = 6 , message = "size must be greater than or equal 6")
    private String password;
}
