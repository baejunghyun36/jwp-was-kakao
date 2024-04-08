package webserver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RequestLineTest {
    @Test
    void RequestLine_객체의_필수_인자를_받아서_생성한다() {
        String method = "GET";
        String uri = "/root?param=1";
        String httpVersion = "HTTP/1.1";
        String info = "GET /root?param=1 HTTP/1.1";
        RequestLine requestLine = new RequestLine(info);
        Assertions.assertThat(requestLine.method()).isEqualTo(Method.of(method));
        Assertions.assertThat(requestLine.uri()).isEqualTo(new Uri(uri));
        Assertions.assertThat(requestLine.httpVersion()).isEqualTo(new HttpVersion(httpVersion));
    }

    @Test
    void RequestLine_객체의_필수_인자가_부족하면_RuntimeException이_발생한다() {
        String info = "GET /root?param=1";
        assertThatThrownBy(() -> new RequestLine(info)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void RequestLine_객체의_필수_인자가_많으면_RuntimeException이_발생한다() {
        String info = "GET /root?param=1 /param=1 GET";
        assertThatThrownBy(() -> new RequestLine(info)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Get /index.html HTTP/1.1",
            "pet /index.html HTTP/1.1",
            "GET /root??param=1 HTTP/1.1",
            "GET /index.html HTTP/1.",
            "GET /index.html HTTP/1",
            "POST /index.html Http/1",
    })
    void RequestLine_객체에_잘못된_정보가_주입되면_RuntimeException이_발생한다(String info) {
        assertThatThrownBy(() -> new RequestLine(info)).isInstanceOf(IllegalArgumentException.class);
    }
}
