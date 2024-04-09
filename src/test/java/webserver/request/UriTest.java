package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Uri 관련 테스트")
class UriTest {
    @Test
    void Uri_객체의_필수_인자를_받아서_생성한다() {
        String path = "/?param=param&a=b&c=d";
        Uri uri = new Uri(path);
        assertThat(uri).isNotNull();
        assertThat(uri.params().size()).isEqualTo(3);
    }

    @Test
    void Uri_구성이_올바르지_않은_경우_RuntimeException이_발생한다() {
        String path = "abcd,,,,";
        assertThatThrownBy(() -> new Uri(path)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Uri_쿼리_파라미터_구분자가_여럿인_경우_RuntimeException이_발생한다() {
        String path = "/root?param=param?a=b";
        assertThatThrownBy(() -> new Uri(path)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Uri_쿼리_파라미터_구성이_올바르지_않은_경우_RuntimeException이_발생한다() {
        String path = "/root?param=param&";
        assertThatThrownBy(() -> new Uri(path)).isInstanceOf(IllegalArgumentException.class);
    }
}