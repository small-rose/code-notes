package cn.com.lgs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author luguosong
 * @date 2021/11/9 15:52
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests表示开启权限配置
        http.authorizeRequests()
                //表示所有的请求都要认证之后才能访问
                .anyRequest().authenticated()
                //相当于又回到HttpSecurity实例，重新开启新一轮的配置
                .and()
                //开启表单登录配置
                .formLogin()
                //用来配置登录页面地址
                .loginPage("/login.html")
                //用来配置登录接口地址
                .loginProcessingUrl("/doLogin")
                //登录成功后的跳转地址
                .defaultSuccessUrl("/index")
                //登录失败后的跳转地址
                .failureUrl("/login.html")
                //表示登录用户名的参数名称
                .usernameParameter("uname")
                //表示登录密码的参数名称
                .passwordParameter("passwd")
                //表示跟登录相关的页面和接口不做拦截
                .permitAll()
                //相当于又回到HttpSecurity实例，重新开启新一轮的配置
                .and()
                //表示禁用CSRF防御功能
                .csrf().disable();
    }
}
