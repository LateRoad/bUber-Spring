package com.tensionsoft.buber.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(long id, String token);

}