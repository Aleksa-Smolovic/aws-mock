package com.quantox.awsmock.service.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
