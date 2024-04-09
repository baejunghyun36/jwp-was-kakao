package webserver;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FormUrlEncodedParsingStrategy implements RequestBodyParsingStrategy {
    private final String contents;
    private final Map<String, Object> requestBody;

    public FormUrlEncodedParsingStrategy(String contents) {
        this.contents = contents;
        this.requestBody = new HashMap<>();
    }

    @Override
    public void perform() {
        String[] split = this.contents.split("&");
        for (String s : split) {
            String[] regex = s.split("=");
            String key = URLDecoder.decode(regex[0], StandardCharsets.UTF_8);
            String value = URLDecoder.decode(regex[1], StandardCharsets.UTF_8);
            requestBody.put(key, value);
        }
    }

    @Override
    public Map<String, Object> requestBody() {
        return this.requestBody;
    }
}
