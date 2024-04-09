package webserver.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("HttpVersion 관련 테스트")
class HttpVersionTest {
    @Test
    void HttpVersion_객체를_생성한다() {
        String version = "HTTP/1.1";
        HttpVersion httpVersion = new HttpVersion(version);
        assertThat(httpVersion).isNotNull();
        assertThat(httpVersion.scheme()).isEqualTo("HTTP");
        assertThat(httpVersion.major()).isEqualTo(1);
        assertThat(httpVersion.minor()).isEqualTo(1);
        assertThat(httpVersion.raw()).isEqualTo(version);
    }

    @Test
    void 소문자_스킴_주입하면_RuntimeException이_발생한다() {
        String version = "Http/1.1";
        assertThatThrownBy(() -> new HttpVersion(version)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메이저_버전과_마이너_버전이_존재하지_않으면_RuntimeException이_발생한다() {
        String version = "HTTP/1";
        assertThatThrownBy(() -> new HttpVersion(version)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 불완전한_버전_표기에도_RuntimeException이_발생한다() {
        String version = "HTTP/1.";
        assertThatThrownBy(() -> new HttpVersion(version)).isInstanceOf(IllegalArgumentException.class);
    }


}
