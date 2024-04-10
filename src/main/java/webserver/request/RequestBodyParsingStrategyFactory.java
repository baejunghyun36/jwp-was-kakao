package webserver.request;

import org.springframework.http.MediaType;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class RequestBodyParsingStrategyFactory {
    public static RequestBodyParsingStrategy create(String contentType, String contents) {
        if (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType)) {
            return new FormUrlEncodedParsingStrategy(contents);
        }
        throw new IllegalArgumentException("존재하지 않는 미디어 타입입니다.");
    }

    private static final class FormUrlEncodedParsingStrategy implements RequestBodyParsingStrategy {
        private static final String ENTRY_DELIMITER = "&";
        private static final String FIELD_DELIMITER = "=";
        private static final int KEY_INDEX = 0;
        private static final int VALUE_INDEX = 1;

        private final String contents;
        private final Map<String, Object> requestBody;

        FormUrlEncodedParsingStrategy(String contents) {
            this.contents = URLDecoder.decode(contents, StandardCharsets.UTF_8);
            this.requestBody = new HashMap<>();
        }

        @Override
        public void perform() {
            String[] split = this.contents.split(ENTRY_DELIMITER);
            Arrays.stream(split)
                    .map(s -> s.split(FIELD_DELIMITER))
                    .forEach(regex -> requestBody.put(regex[KEY_INDEX], regex[VALUE_INDEX]));
        }

        @Override
        public Map<String, Object> requestBody() {
            return this.requestBody;
        }
    }
}
