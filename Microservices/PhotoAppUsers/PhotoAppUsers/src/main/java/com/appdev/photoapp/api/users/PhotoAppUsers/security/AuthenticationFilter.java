package com.appdev.photoapp.api.users.PhotoAppUsers.security;

import com.appdev.photoapp.api.users.PhotoAppUsers.models.LoginRequestModel;
import com.appdev.photoapp.api.users.PhotoAppUsers.services.UserService;
import com.appdev.photoapp.api.users.PhotoAppUsers.shared.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
 private UserService userService;

 private Environment env;

   public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager)
   {
       this.env=env;
       this.userService=userService;
       super.setAuthenticationManager(authenticationManager);
   }

     @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException
     {
         try
         {
             LoginRequestModel creds=new ObjectMapper().readValue(req.getInputStream(),LoginRequestModel.class);
             return getAuthenticationManager().authenticate(
                     new UsernamePasswordAuthenticationToken(
                             creds.getEmail(),
                             creds.getPassword(),
                             new ArrayList<>())
                     );


         }catch(IOException e)
         {
             throw new RuntimeException(e);
         }

     }
     @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,Authentication auth) throws IOException, ServletException
     {
        String userName=((User)auth.getPrincipal()).getUsername();
        UserDto userDetails=userService.getUserDetailsByEmail(userName);
        String token= Jwts.builder()
                .setSubject(userDetails.getUserId())
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(String.valueOf(324243224))))
                .signWith(SignatureAlgorithm.HS512,env.getProperty("token.secret")).compact();
        res.addHeader("token",token);
        res.addHeader("UserId",userDetails.getUserId());
     }
}
