package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import webserver.common.HttpVersion;
import webserver.common.Uri;
import webserver.enums.Method;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("RequestLine 관련 테스트")
class RequestLineTest {
    @Test
    void RequestLine_객체의_필수_인자를_받아서_생성한다() {
        String method = "GET";
        String uri = "/root?param=1";
        String httpVersion = "HTTP/1.1";
        String info = "GET /root?param=1 HTTP/1.1\r\n";
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(info.getBytes())));
        RequestLine requestLine = new RequestLine(br);
        assertThat(requestLine.method()).isEqualTo(Method.of(method));
        assertThat(requestLine.uri()).isEqualTo(new Uri(uri));
        assertThat(requestLine.httpVersion()).isEqualTo(HttpVersion.of(httpVersion));
    }

    @Test
    void RequestLine_객체의_필수_인자가_부족하면_RuntimeException이_발생한다() {
        String info = "GET /root?param=1\r\n";
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(info.getBytes())));
        assertThatThrownBy(() -> new RequestLine(br)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void RequestLine_객체의_필수_인자가_많으면_RuntimeException이_발생한다() {
        String info = "GET /root?param=1 /param=1 GET\r\n";
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(info.getBytes())));
        assertThatThrownBy(() -> new RequestLine(br)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("generateRequestLines")
    void RequestLine_객체에_잘못된_정보가_주입되면_RuntimeException이_발생한다(String info) {
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(info.getBytes())));
        assertThatThrownBy(() -> new RequestLine(br)).isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> generateRequestLines() {
        return Stream.of(
                Arguments.of("Get /index.html HTTP/1.1\r\n"),
                Arguments.of("pet /index.html HTTP/1.1\r\n"),
                Arguments.of("GET /root??param=1 HTTP/1.1\r\n"),
                Arguments.of("GET /index.html HTTP/1.\r\n"),
                Arguments.of("GET /index.html HTTP/1\r\n"),
                Arguments.of("POST /index.html Http/1\r\n")
        );
    }
}
