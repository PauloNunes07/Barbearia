package br.com.Spa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String SECRET_KEY =
            "barbeariaSistema@jwt-2026-cod-secreta-auth-api";
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtAuthFilter jwtAuthFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Login liberado
                        .requestMatchers("/auth/login","/usuarios/cadastrar").permitAll()

                        // Rotas que qualquer usuario pode fazer no sistema
                        .requestMatchers(
                                "/clientes/cadastrar",
                                "/servicos/listar",
                                "/avaliacao/cadastrar",
                                "/agendamento/cadastrar",
                                "/pagamento/cadastrar").authenticated()

                        // Rotas protegidas
                        .requestMatchers("/clientes/**").hasAnyRole("ROLE_FUNCIONARIO", "ROLE_BARBEIRO")
                        .requestMatchers("/servicos/**").hasAnyRole("FUNCIONARIO", "BARBEIRO")
                        .requestMatchers("/agendamento/**").hasAnyRole("FUNCIONARIO", "BARBEIRO")
                        .requestMatchers("/pagamento/**").hasAnyRole("FUNCIONARIO", "BARBEIRO")
                        .requestMatchers("/avaliacao/**").hasAnyRole("FUNCIONARIO", "BARBEIRO")



                        // Qualquer outra rota também exige autenticação
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}