package webserver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RequestLineTest {
    @Test
    void RequestLine_객체의_필수_인자를_받아서_생성한다() {
        String method = "GET";
        String uri = "/root?param=1";
        String httpVersion = "HTTP/1.1";
        String info = "GET /root?param=1 HTTP/1.1\r\n";
        InputStream bis = new ByteArrayInputStream(info.getBytes());
        RequestLine requestLine = new RequestLine(bis);
        Assertions.assertThat(requestLine.method()).isEqualTo(Method.of(method));
        Assertions.assertThat(requestLine.uri()).isEqualTo(new Uri(uri));
        Assertions.assertThat(requestLine.httpVersion()).isEqualTo(new HttpVersion(httpVersion));
    }

    @Test
    void RequestLine_객체의_필수_인자가_부족하면_RuntimeException이_발생한다() {
        String info = "GET /root?param=1\r\n";
        InputStream bis = new ByteArrayInputStream(info.getBytes());
        assertThatThrownBy(() -> new RequestLine(bis)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void RequestLine_객체의_필수_인자가_많으면_RuntimeException이_발생한다() {
        String info = "GET /root?param=1 /param=1 GET\r\n";
        InputStream bis = new ByteArrayInputStream(info.getBytes());
        assertThatThrownBy(() -> new RequestLine(bis)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Get /index.html HTTP/1.1\r\n",
            "pet /index.html HTTP/1.1\r\n",
            "GET /root??param=1 HTTP/1.1\r\n",
            "GET /index.html HTTP/1.\r\n",
            "GET /index.html HTTP/1\r\n",
            "POST /index.html Http/1\r\n",
    })
    void RequestLine_객체에_잘못된_정보가_주입되면_RuntimeException이_발생한다(String info) {
        InputStream bis = new ByteArrayInputStream(info.getBytes());
        assertThatThrownBy(() -> new RequestLine(bis)).isInstanceOf(IllegalArgumentException.class);
    }
}
