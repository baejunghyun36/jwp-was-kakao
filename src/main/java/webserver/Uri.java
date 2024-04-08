package webserver;

import java.util.Map;

public final class Uri {
    private final Method method;
    private final String path;
    private final Map<String, String> params;

    public Uri(String method, String path, Map<String, String> params) {
        this.method = Method.of(method);
        this.path = path;
        this.params = params;
    }

    public Method method() {
        return this.method;
    }

    public String path() {
        return this.path;
    }



    public Map<String,String> params() {
        return this.params;
    }
}
