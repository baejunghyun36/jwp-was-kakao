package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Method 관련 테스트")
public class MethodTest {
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
}
