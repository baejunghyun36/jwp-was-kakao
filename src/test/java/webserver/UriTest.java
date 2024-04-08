package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Uri 관련 테스트")
public class UriTest {
    @Test
    void Uri_객체의_필수_인자를_받아서_생성한다() {
        String path = "/?param=param&a=b&c=d";
        Uri uri = new Uri(path);
        assertThat(uri).isNotNull();
        assertThat(uri.params().size()).isEqualTo(3);
    }
}