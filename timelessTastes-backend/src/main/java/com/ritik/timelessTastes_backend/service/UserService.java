package com.ritik.timelessTastes_backend.service;

import com.ritik.timelessTastes_backend.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
