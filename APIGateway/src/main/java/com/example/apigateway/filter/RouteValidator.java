package com.example.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka",
            "/auth/**",
            "/api/book/getAllBooks",
            "/api/book/getBookById/**"

    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));


    public static final List<String> adminEndpoints = List.of(
            "/api/book/**"
    );
//    public Predicate<ServerHttpRequest> isAdminAccess =
//            request -> adminEndpoints
//                    .stream()
//                    .anyMatch(uri -> uri.equals(request.getURI().getPath()));
    public Predicate<ServerHttpRequest> isAdminAccess =
            request -> adminEndpoints
                    .stream()
                    .anyMatch(uri -> new AntPathMatcher().match(uri, request.getURI().getPath()));

}
