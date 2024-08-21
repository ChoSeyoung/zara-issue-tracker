package my.bt.zara.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import my.bt.zara.common.ApiResponse;
import my.bt.zara.util.ApiResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
          .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login", "/register").permitAll()
                .anyRequest().authenticated()
          )
          .formLogin(form -> form
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler())
                .failureHandler(customAuthenticationFailureHandler())
                .permitAll()
          )
          .logout(LogoutConfigurer::permitAll)
          .exceptionHandling(exception -> exception
                      .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                // 인증되지 않은 경우 401 반환
          )
          .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
          .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 추가

    ;

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(List.of("*")); // allowedOriginPatterns로 변경
    configuration.setAllowedMethods(
          List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 허용할 HTTP 메서드
    configuration.setAllowedHeaders(List.of("*")); // 모든 헤더 허용
    configuration.setAllowCredentials(true); // 자격 증명 허용

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
    return (request, response, authentication) -> {
      response.setStatus(HttpStatus.OK.value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);

      // 공통 응답 객체 생성
      ApiResponse<String> apiResponse = ApiResponseUtil.createSuccessResponse(
            authentication.getName(), "Login successful");

      // JSON 데이터를 응답 본문에 작성
      ObjectMapper objectMapper = new ObjectMapper();
      response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
      response.getWriter().flush();
    };
  }

  @Bean
  public AuthenticationFailureHandler customAuthenticationFailureHandler() {
    return (request, response, exception) -> {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);

      // 공통 에러 응답 객체 생성
      ApiResponse<Object> apiResponse = ApiResponseUtil.createErrorResponse(
            HttpStatus.UNAUTHORIZED, "Login failed", "AuthenticationException", exception.getMessage());

      // JSON 데이터를 응답 본문에 작성
      ObjectMapper objectMapper = new ObjectMapper();
      response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
      response.getWriter().flush();
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
