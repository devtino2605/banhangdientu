package com.example.web_banhang.configuration;

import com.example.web_banhang.Utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.OAuth2ResourceServerDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    private final RSAKeyProperties keys;

    public SecurityConfiguration(RSAKeyProperties keys){
        this.keys = keys;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService detailsService){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
//        lấy thông tin người dùng từ nguồn dữ liệu như cơ sở dữ liệu.
        daoProvider.setUserDetailsService(detailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoProvider);
    }

    @Bean
//     một đối tượng cung cấp các phương thức để cấu hình quy tắc bảo mật.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
//        cho phép bạn gửi yêu cầu từ các nguồn khác nhau mà không cần xác thực CSRF token.
                .csrf(csrf -> csrf.disable())
//        được sử dụng để xác định quy tắc bảo mật cho các yêu cầu HTTP
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/api/auth/**").permitAll();
                            auth.requestMatchers("/api/admin/**").hasRole("ADMIN");
                            auth.requestMatchers("/api/user/").hasAnyRole("ADMIN","USER");
                            auth.anyRequest().authenticated();
                        });
        http
//        một phương thức khác được gọi để thiết lập cách chuyển đổi JWT thành đối tượng xác thực.
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(authenticationConverter());
        http
//        cho biết rằng không có phiên HTTP sẽ được tạo ra hoặc sử dụng trong quá trình xác thực.
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        ây dựng đối tượng HttpSecurity và trả về một SecurityFilterChain được sử dụng để cấu hình bộ lọc bảo mật
        return http.build();

    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    //        chuyển đổi JWT thành đối tượng Authentication trong Spring Security
//        Nó sử dụng JwtGrantedAuthoritiesConverter chuyển đổi thông tin quyền từ JWT thành danh sách các GrantedAuthority.
    @Bean
    public JwtAuthenticationConverter authenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
}
