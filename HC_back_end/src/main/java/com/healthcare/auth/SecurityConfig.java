package com.healthcare.auth;

import com.healthcare.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    @Autowired
    public PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private UserDetailsService userDetailsService=new UserDetailsServiceImpl();
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 注销操作处理器
     */
    @Autowired
    private MyLogoutHandler myLogoutHandler;

    /**
     * 注销成功处理器
     */
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    /**
     * session过期(超时)处理方案
     */
    @Autowired
    private MyInvalidSessionStrategy myInvalidSessionStrategy;

    @Autowired
    private MyExpiredSessionStrategy myExpiredSessionStrategy;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中配置用户名和密码
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder.encode("123"))
//                .roles("user")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder.encode("123"))
//                //.authorities("user:add","user:update")
//                .roles("admin")
//                .and()
//                .passwordEncoder(passwordEncoder);//配置BCrypt加密
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行swagger
        web.ignoring().antMatchers(HttpMethod.GET,
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*//开启httpBasic认证
        http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest()
                //所有请求都需要登录认证才能访问
                .authenticated();*/
        http.authorizeRequests()
                //需要放行的url在这里配置,必须要放行/login和/login.html,不然会报错
                .antMatchers("/login","/registerUser","/mail","/swagger-ui.html","/hcpic/**","/hcpho/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout().permitAll()
                .addLogoutHandler(myLogoutHandler)
                .logoutSuccessHandler(myLogoutSuccessHandler)
                //登出之后删除cookie
                .deleteCookies("JSESSIONID")
                .and()
                // 设置登陆页
                .formLogin()
                //登录表单form中action的地址，也就是处理认证请求的路径
                .loginProcessingUrl("/login")
                //登录表单form中用户名输入框input的name名，不修改的话默认是username
                .usernameParameter("username")
                //登录表单form中密码输入框input的name名，不修改的话默认是password
                .passwordParameter("password")
                //登录认证成功后默认转跳的路径
//                .defaultSuccessUrl("/home").permitAll()
                // 登录失败Url
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler)
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .and()
                .sessionManagement()
//                .invalidSessionStrategy(myInvalidSessionStrategy)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false);
                //无状态的,任何时候都不会使用session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //关闭CSRF跨域
        http.csrf().disable();
    }

}