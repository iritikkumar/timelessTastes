package com.ritik.timelessTastes_backend.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}