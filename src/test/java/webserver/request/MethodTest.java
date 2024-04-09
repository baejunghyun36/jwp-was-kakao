package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Method 관련 테스트")
class MethodTest {
    @Test
    void 소문자로_구성된_메서드를_주입하면_RuntimeException이_발생한다() {
        String method = "get";
        assertThatThrownBy(() -> Method.of(method)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 존재하지_않는_메서드를_주입하면_RuntimeException이_발생한다() {
        String method = "ASDF";
        assertThatThrownBy(() -> Method.of(method)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"GET:false", "HEAD:false", "DELETE:false", "POST:true"}, delimiter = ':')
    void RequestBody_존재여부_파악할_수_있다(String method, boolean isAcceptable) {
        Method httpMethod = Method.of(method);
        assertThat(httpMethod.isRequestBodyAcceptable()).isEqualTo(isAcceptable);
    }
}
