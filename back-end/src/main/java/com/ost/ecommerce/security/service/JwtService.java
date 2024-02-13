package com.ost.ecommerce.security.service;

public interface JwtService {

    String generateToken(String userName);
}
