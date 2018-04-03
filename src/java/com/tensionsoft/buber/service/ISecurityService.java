package com.tensionsoft.buber.service;

public interface ISecurityService {

    String findLoggedInByUsername();

    void autoLogin(String username, String password);
}
