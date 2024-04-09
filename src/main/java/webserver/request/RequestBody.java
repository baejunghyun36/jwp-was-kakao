package webserver.request;

import java.util.HashMap;
import java.util.Map;

public final class RequestBody {
    private Map<String, Object> contents;

    public RequestBody() {
        this.contents = new HashMap<>();
    }

    public RequestBody(RequestBodyParsingStrategy strategy) {
        strategy.perform();
        this.contents = strategy.requestBody();
    }

    public Map<String, Object> contents() {
        return this.contents;
    }
}
