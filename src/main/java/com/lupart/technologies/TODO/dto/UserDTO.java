package com.lupart.technologies.TODO.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String createdBy;
    private String lastModifiedBy;
    private String active;
    @Email
    @NotBlank(message = "Please enter the email")
    private String email;
    @Length(max = 37, min = 2)
    @NotBlank(message = "Please enter the password")
    private String password;
    @JsonIgnore
    private Collection<RoleDTO> roles = new ArrayList<>();
}
