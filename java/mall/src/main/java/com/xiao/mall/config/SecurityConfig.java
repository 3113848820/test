package com.xiao.mall.config;

import com.xiao.mall.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedPage("/view/userLogin");//配置没有权限访问跳转的自定义页面
        http.authorizeRequests()
                .antMatchers("/","/view/userLogin").permitAll()
                .antMatchers("/customer/**","/product/**").hasRole("admin")
                .anyRequest().authenticated();
//              自定义登录
        http.formLogin()
                .loginPage("/view/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/view/userLogin?error");
//      自定义登出
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/view/userLogin");
//       记住我功能
        http.rememberMe()
                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(259200);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //配置静态文件不需要认证
        web.ignoring().antMatchers("/static/**");
    }

}
