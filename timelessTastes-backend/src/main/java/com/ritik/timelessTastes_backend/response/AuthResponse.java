package com.ritik.timelessTastes_backend.response;

import com.ritik.timelessTastes_backend.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
