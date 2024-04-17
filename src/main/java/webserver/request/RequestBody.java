package webserver.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class RequestBody {
    private final Map<String, Object> contents;

    public RequestBody() {
        this.contents = new HashMap<>();
    }

    public RequestBody(RequestBodyParsingStrategy strategy) {
        strategy.perform();
        this.contents = strategy.requestBody();
    }

    public Object get(String key) {
        return contents.get(key);
    }

    public Map<String, Object> contents() {
        return this.contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestBody that = (RequestBody) o;
        return Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }
}
