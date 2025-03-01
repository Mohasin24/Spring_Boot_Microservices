package org.api_gateway.constants;

import java.util.Set;

public class Paths
{
    private static final String Test =  "/test";
    private static final String Auth_Login = "/api/v1/auth/login";
    private static final String Auth_Register = "/api/v1/auth/register";
    
    public static final Set<String> API_PATHS = Set.of(Test,Auth_Login, Auth_Register);
}
