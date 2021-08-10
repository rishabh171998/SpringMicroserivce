package com.appdev.photoapp.api.gateway.PhotoAppSpringCloudGateway;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    @Autowired
    private Environment env;

   public AuthorizationHeaderFilter()
   {
       super(Config.class);
   }
    public static class  Config{
    ///Configuration Properities

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange,chain) ->
        {


            if(! exchange.getRequest().getHeaders().containsKey("Authorization"))
            {
               return onError(exchange,"No Authorization header", HttpStatus.UNAUTHORIZED);
            }
           String authorizationHeader=  exchange.getRequest().getHeaders().get("Authorization").get(0);
            String jwt=authorizationHeader.replace("Bearer","");
    if(!isJWTValid(jwt))
    {
        return onError(exchange,"JWT token is  not valid", HttpStatus.UNAUTHORIZED);
    }
            return chain.filter(exchange);
        };
    }
    private Mono<Void> onError(ServerWebExchange exchange,String err,HttpStatus httpStatus)
    {

        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }
    private boolean isJWTValid(String jwt)
    {
        boolean returnValue=true;
        System.out.println(env.getProperty("token.secret"));
        String subject=Jwts.parser().setSigningKey(env.getProperty("token.secret")).parseClaimsJws(jwt).getBody().getSubject();
        System.out.println(env.getProperty("token.secret"));
        if(subject==null||subject.isEmpty())
        {
            returnValue=false;
        }
        return returnValue;
    }
}
