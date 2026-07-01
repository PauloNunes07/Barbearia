package br.com.Spa.service;

import br.com.Spa.security.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AuthService {

   private final AuthenticationManager authenticationManager;

   public AuthService(AuthenticationManager authenticationManager) {
       this.authenticationManager = authenticationManager;
   }

    private final String SECRET_KEY = "barbeariaSistema@jwt-2026-cod-secreta-auth-api";


    public String login(String login, String senha) {
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(login, senha));


       UserDetails user = (UserDetails) authentication.getPrincipal();

       return Jwts.builder().setSubject(user.getUsername())
               .claim("roles",user.getAuthorities())
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + 14400000))
               .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
               .compact();
   }


}
