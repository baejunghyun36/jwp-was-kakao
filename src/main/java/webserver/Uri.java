package webserver;

import java.util.Map;

public final class Uri {
    private final String path;
    private final Map<String, String> params;

    public Uri(String path, Map<String, String> params) {
        this.path = path;
        this.params = params;
    }

    public String path() {
        return this.path;
    }

    public Map<String, String> params() {
        return this.params;
    }
}
