package com.example.web_banhang.Services;

import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.Roles;
import com.example.web_banhang.reponsibility.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    @Autowired
    private UserReponsitory userReponsitory;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("in the user service detail`");
        return userReponsitory.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user is not valid")  );
    }
}
