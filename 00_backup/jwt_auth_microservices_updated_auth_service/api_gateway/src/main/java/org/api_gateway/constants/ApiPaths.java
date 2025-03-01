package org.api_gateway.constants;

import java.util.Set;

public class ApiPaths {

    private static final String API_USER_REGISTRATION = "/api/v1/auth/register";
    private static final String API_USER_LOGIN = "/api/v1/auth/login";

    private static final String OPEN_ROUTE="/api/v1/test/test";

    public static final Set<String> ROUTES = Set.of(API_USER_REGISTRATION,API_USER_LOGIN,OPEN_ROUTE);
}
