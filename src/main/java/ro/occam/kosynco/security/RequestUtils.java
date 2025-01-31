package ro.occam.kosynco.security;

import io.micronaut.http.HttpRequest;

public class RequestUtils {

    private static final String X_AUTH_USER_HEADER = "x-auth-user";
    private static final String X_AUTH_KEY_HEADER = "x-auth-key";

    public static String getUsername(HttpRequest<?> request) {
        return request.getHeaders().get(X_AUTH_USER_HEADER);
    }

    public static String getPassword(HttpRequest<?> request) {
        return request.getHeaders().get(X_AUTH_KEY_HEADER);
    }
}
