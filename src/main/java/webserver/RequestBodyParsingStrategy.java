package webserver;

import java.util.Map;

public interface RequestBodyParsingStrategy {
    void perform();

    Map<String, Object> requestBody();
}
