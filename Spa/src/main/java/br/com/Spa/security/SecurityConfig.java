package br.com.Spa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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

    private final String SECRET_KEY = "spa@chave-secreta-jwt-2026";

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtAuthFilter jwtAuthFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Apenas ADMIN pode cadastrar
                        .requestMatchers(
                                "/clientes/cadastrar",
                                "/servicos/cadastrar"
                        ).hasRole("ADMIN")

                        // Rotas protegidas
                        .requestMatchers("/clientes/**").authenticated()
                        .requestMatchers("/servicos/**").authenticated()
                        .requestMatchers("/agendamento/**").authenticated()
                        .requestMatchers("/pagamento/**").authenticated()
                        .requestMatchers("/avaliacao/**").authenticated()

                        // Login liberado
                        .requestMatchers("/auth/login").permitAll()

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
    public InMemoryUserDetailsManager userDetailsService(
            PasswordEncoder passwordEncoder) {

        var usuario = User.builder()
                .username("usuario")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();

        var admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("142536"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(usuario, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}