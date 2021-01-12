package com.codecool.earthbnb.gateway.model.DTO;

import com.codecool.earthbnb.gateway.service.validation.annotation.ValidEmailFormat;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {

    //@NotEmpty(message = "Email can't be null")
    @ValidEmailFormat
    private String email;

   // @NotEmpty(message = "Password can't be null")
    @ValidPassword
    private String password;

}
