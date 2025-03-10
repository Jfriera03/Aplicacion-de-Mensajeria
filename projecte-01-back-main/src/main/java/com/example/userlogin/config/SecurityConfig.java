package com.example.userlogin.config;

import com.example.userlogin.services.UserServiceImpl;
import com.example.userlogin.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
/*
 * FUNCIONALIDAD
 * Clase donde se implementa la configuración del manejo de seguridad de la aplicación.
 * Se configuran las reglas de seguridad HTTP, la codificación de contraseñas y el administrador de autenticación.
 * Se añade el filtro JWT antes del filtro de autenticación.
 */
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(@Lazy JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    // Configurar el codificador de contraseñas
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // Configurar las reglas de seguridad HTTP
    public SecurityFilterChain configureHttpSecurity(HttpSecurity httpSecurity, JwtRequestFilter jwtRequestFilter) throws Exception {
        httpSecurity
                // Deshabilitar CSRF para llamadas API REST
                .csrf(AbstractHttpConfigurer::disable)
                // Configurar las reglas de autorización
                .cors(Customizer.withDefaults()) // Enable CORS
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/register").permitAll() // Permitir acceso público
                        .requestMatchers("/auth/login").permitAll()    // Permitir acceso público
                        .requestMatchers("/api/messaging/**").authenticated() // Proteger rutas de mensajes
                        .requestMatchers("/api/conversations/**").authenticated() // Proteger rutas de conversaciones
                        .anyRequest().authenticated()                    // Proteger cualquier otra ruta
                )
                // Usar autenticación sin estado para REST
                .sessionManagement(session -> session.sessionCreationPolicy(
                        org.springframework.security.config.http.SessionCreationPolicy.STATELESS
                ))

                // Añadir el filtro JWT antes del filtro de autenticación
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

                // Configuración adicional para autenticación y cierre de sesión
                .httpBasic(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    // Configurar el administrador de autenticación
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}