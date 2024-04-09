package webserver;

import java.util.Arrays;
import java.util.Optional;

public enum Method {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    OPTIONS("OPTIONS"),
    CONNECT("CONNECT"),
    HEAD("HEAD");

    private final String method;

    Method(String method) {
        this.method = method;
    }

    public static Method of(String method) {
        Optional<Method> optionalMethod = Arrays.stream(Method.values())
                .filter(e -> e.method.equals(method))
                .findAny();
        if (optionalMethod.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 메서드입니다.");
        }
        return optionalMethod.get();
    }

    public boolean isRequestBodyAcceptable() {
        return this != GET && this != HEAD && this != DELETE;
    }
}
