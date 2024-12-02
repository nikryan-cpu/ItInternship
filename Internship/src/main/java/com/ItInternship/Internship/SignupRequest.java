package com.ItInternship.Internship;

import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
}
