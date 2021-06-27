package com.app.nbco.user.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}