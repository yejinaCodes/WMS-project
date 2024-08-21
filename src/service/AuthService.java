package service;

import dto.request.AuthRequestDto;
import dto.response.AuthResponseDto;
import javax.naming.AuthenticationException;

public interface AuthService {

    AuthResponseDto loginUser(AuthRequestDto user) throws AuthenticationException;
    AuthResponseDto loginAdmin(AuthRequestDto user) throws AuthenticationException;
}
