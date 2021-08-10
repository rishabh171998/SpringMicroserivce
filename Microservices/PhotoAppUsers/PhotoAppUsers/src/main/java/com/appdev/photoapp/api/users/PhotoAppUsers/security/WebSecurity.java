package com.appdev.photoapp.api.users.PhotoAppUsers.security;


import com.appdev.photoapp.api.users.PhotoAppUsers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
     private BCryptPasswordEncoder bcrypt;
    @Autowired
    private Environment env;
    @Override
    protected  void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll().and().addFilter(getAuthenticationFilter());
                http.headers().frameOptions().disable();
    }
  private AuthenticationFilter getAuthenticationFilter() throws Exception
  {
      AuthenticationFilter authenticationFilter=new AuthenticationFilter(userService,env,authenticationManager());
      authenticationFilter.setFilterProcessesUrl(env.getProperty("login.url.path"));
      try {
         // authenticationFilter.setAuthenticationManager(authenticationManager());
      } catch (Exception e) {
          e.printStackTrace();
      }
      return authenticationFilter;
  }
  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
      try
      {
          auth.userDetailsService(userService).passwordEncoder(bcrypt);

      }catch(Exception e)
      {
          e.printStackTrace();
      }
  }
}
