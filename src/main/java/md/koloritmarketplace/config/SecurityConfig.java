package md.koloritmarketplace.config;

import lombok.RequiredArgsConstructor;
import md.amdaris.common.security.jwt.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtConfigurer jwtConfigurer) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource)
                .and()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers( "/**").permitAll()
                .antMatchers("/account/authentication","/**/activation").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/swagger-ui/**", "/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**", "/webjars/**", "/v3/api-docs/**").permitAll().anyRequest().authenticated()
                .and()
                .apply(jwtConfigurer)
                .and().exceptionHandling().accessDeniedPage("/accessdenied");
        return http.build();
    }

//    @Bean
//    public AccessDeniedHandler accessDeniedHandler(){
//        return new AccountAccessDeniedHandler();
//    }
//
    @Bean
    DispatcherServlet dispatcherServlet () {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
}
