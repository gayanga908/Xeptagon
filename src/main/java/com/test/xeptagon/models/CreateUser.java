package com.test.xeptagon.models;

import com.test.xeptagon.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {
    @NotBlank
    private String userName;
    @NotNull
    private UserType userType;
}
