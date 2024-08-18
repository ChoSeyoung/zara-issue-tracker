package my.bt.zara.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
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
          .cors(cors -> {
            if (corsConfigurationSource() != null) {
              cors.configurationSource(corsConfigurationSource());
            }
          })
          .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login", "/register").permitAll()
                .anyRequest().authenticated()
          )
          .formLogin(form -> form
                .loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", false) // 리다이렉트를 하지 않도록 false 설정
                .failureUrl("/") // 리다이렉트를 하지 않도록 false 설정
                .successHandler(customAuthenticationSuccessHandler()) // 성공 시 핸들러
                .failureHandler(customAuthenticationFailureHandler()) // 실패 시 핸들러
                .permitAll()
          )
          .logout(LogoutConfigurer::permitAll)
          .exceptionHandling(exception -> exception
                      .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                // 인증되지 않은 경우 401 반환
          )
          .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
    ;

    return http.build();
  }

  @Bean
  public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
    return (request, response, authentication) -> {
      response.setStatus(HttpStatus.OK.value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);

      // JSON 데이터를 생성
      Map<String, Object> data = new HashMap<>();
      data.put("code", HttpStatus.OK.value());
      data.put("data", authentication.getName());

      // JSON 데이터를 응답 본문에 작성
      ObjectMapper objectMapper = new ObjectMapper();
      response.getWriter().write(objectMapper.writeValueAsString(data));
      response.getWriter().flush();
    };
  }

  @Bean
  public AuthenticationFailureHandler customAuthenticationFailureHandler() {
    return (request, response, exception) -> {
      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);

      // JSON 데이터를 생성
      Map<String, Object> data = new HashMap<>();
      data.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());

      Map<String, Object> message = new HashMap<>();
      message.put("title", "로그인 실패");
      message.put("description", "계정 정보를 확인해주세요.");

      data.put("data", message);

      // JSON 데이터를 응답 본문에 작성
      ObjectMapper objectMapper = new ObjectMapper();
      response.getWriter().write(objectMapper.writeValueAsString(data));
      response.getWriter().flush();
    };
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(List.of("*")); // 모든 Origin 허용
    configuration.setAllowedMethods(List.of("*")); // 모든 HTTP 메서드 허용
    configuration.setAllowedHeaders(List.of("*")); // 모든 헤더 허용
    configuration.setAllowCredentials(true); // 자격 증명 허용

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails user = User.builder()
          .username("user")
          .password(passwordEncoder.encode("password"))
          .roles("USER")
          .build();

    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
