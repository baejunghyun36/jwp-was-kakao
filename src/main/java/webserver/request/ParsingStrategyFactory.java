package webserver.request;

import org.springframework.http.MediaType;

public final class ParsingStrategyFactory {
    public static RequestBodyParsingStrategy create(String contentType, String contents) {
        if (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType)) {
            return new FormUrlEncodedParsingStrategy(contents);
        }
        throw new IllegalArgumentException("존재하지 않는 미디어 타입입니다.");
    }
}
