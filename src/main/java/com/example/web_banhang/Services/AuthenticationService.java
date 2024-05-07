package com.example.web_banhang.Services;

import com.example.web_banhang.model.Dto.JwtResponse;
import com.example.web_banhang.model.Dto.LoginResponseDto;
import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.Dto.RefreshTokenRequest;
import com.example.web_banhang.model.RefreshToken;
import com.example.web_banhang.model.Roles;
import com.example.web_banhang.reponsibility.RoleReponsitory;
import com.example.web_banhang.reponsibility.UserReponsitory;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserReponsitory userReponsitory;

    @Autowired
    private RoleReponsitory roleReponsitory;

    @Autowired
    private RefreshTokenSerivice refreshTokenService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username,String email,String phone, String password){
        String encoderPassword = encoder.encode(password);
        Roles userRole = roleReponsitory.findByAutherity("USER").get();

        Set<Roles> authorities = new HashSet<>();

        authorities.add(userRole);

        return userReponsitory.save(new ApplicationUser(0,username,email,phone,encoderPassword,authorities));
    }

    public LoginResponseDto loginUser(String email, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            if (auth.isAuthenticated()) {
                ApplicationUser user = userReponsitory.findByEmail(email).get();
                RefreshToken existingRefreshToken = refreshTokenService.findByUser(user.getUsername()).get();

                String token;
                if (existingRefreshToken!=null){
                    token = tokenService.generateJwt(auth);
                    return new LoginResponseDto(user, token,existingRefreshToken.getToken());
                }else {
                    RefreshToken refreshToken = refreshTokenService.createRefreshToken(email);
                    token = tokenService.generateJwt(auth);
                    return new LoginResponseDto(user, token,refreshToken.getToken());
                }
            } else {
                throw new UsernameNotFoundException("invalid user request !");
            }

        } catch(AuthenticationException e){
            return new LoginResponseDto(null, "",null);
        }
    }

}
