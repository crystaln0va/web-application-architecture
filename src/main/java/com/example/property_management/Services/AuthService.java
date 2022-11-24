package com.example.property_management.Services;


import com.example.property_management.Security.CustomUserDetailsService;
import com.example.property_management.entity.User;
import com.example.property_management.entity.dto.LoginRequestDto;
import com.example.property_management.entity.dto.LoginResponseDto;
import com.example.property_management.entity.dto.RefreshRequestDto;
import com.example.property_management.entity.dto.RefreshResponseDto;
import com.example.property_management.jwt.JwtUtils;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;

    public LoginResponseDto login(LoginRequestDto loginRequest){


        String accessToken = jwtUtils.generateToken(loginRequest.getEmail());
        String refreshToken = jwtUtils.generateRefreshToken(loginRequest.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        return new LoginResponseDto(accessToken,refreshToken,true,user.getRole().getName());

    }


    public RefreshResponseDto refresh(String oldToken){
        boolean isValid = jwtUtils.validateToken(oldToken);
        if(isValid){
            String accessToken = jwtUtils.generateToken(jwtUtils.getUsernameFromToken(oldToken));
            String refreshToken = jwtUtils.generateRefreshToken(jwtUtils.getUsernameFromToken(oldToken));
            return new RefreshResponseDto(accessToken,refreshToken);
        }
        return null;
    }

}
