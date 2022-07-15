package com.lupart.technologies.TODO.auth;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JWTRequest {
    private String username;
    private String password;
}
