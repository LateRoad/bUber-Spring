package com.tensionsoft.buber.security;

import com.tensionsoft.buber.security.web.CustomAuthenticationProvider;
import com.tensionsoft.buber.security.web.CustomWebAuthenticationDetailsSource;
import com.tensionsoft.buber.service.CustomRememberMeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final LogoutSuccessHandler myLogoutSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final CustomWebAuthenticationDetailsSource authenticationDetailsSource;


    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*", "/login*", "/logout*", "/signin/**", "/signup/**", "/customLogin",
                        "/user/registration*", "/registrationConfirm*", "/expiredAccount*", "/registration*",
                        "/badUser*", "/user/resendRegistrationToken*", "/forgetPassword*", "/user/resetPassword*",
                        "/user/changePassword*", "/emailError*", "/resources/**", "/old/user/registration*", "/successRegister*", "/qrcode*").permitAll()
                .antMatchers("/invalidSession*").anonymous()
                .antMatchers("/user/updatePassword*", "/user/savePassword*", "/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                .anyRequest().hasAuthority("READ_PRIVILEGE")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/homepage.html")
                .failureUrl("/login?error=true")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .authenticationDetailsSource(authenticationDetailsSource)
                .permitAll()
                .and()
                .sessionManagement()
                .invalidSessionUrl("/invalidSession.html")
                .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
                .sessionFixation().none()
                .and()
                .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .invalidateHttpSession(false)
                .logoutSuccessUrl("/logout.html?logSucc=true")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .rememberMe().rememberMeServices(rememberMeServices()).key("theKey");
        // @formatter:on
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        CustomRememberMeServices rememberMeServices = new CustomRememberMeServices("theKey", userDetailsService, new InMemoryTokenRepositoryImpl());
        return rememberMeServices;
    }
}
