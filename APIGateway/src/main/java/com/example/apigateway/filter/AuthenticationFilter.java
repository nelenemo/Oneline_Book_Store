package com.example.apigateway.filter;

import com.example.apigateway.util.JwtUtil;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;
    //
//    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil util;

    public AuthenticationFilter() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {


            if (validator.isSecured.test(exchange.getRequest())) {

                //header consist of token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);

                }
                try {
                    //Rest call to Auth sevice
//                    template.getForObject("http://USER-SERVICE/validate?token"+authHeader,String.class);//token is a auth header
                    util.validateToken(authHeader);

                } catch (Exception e) {
                    System.out.println("invalid access");
                    throw new RuntimeException("unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}

//before you redirect a request to corresponding
// microservices how can i tell that just go to the application.yml file