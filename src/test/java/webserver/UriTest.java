package webserver;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class UriTest {
    @Test
    void Uri_객체를_생성한다() {
        Uri uri = new Uri();
        assertThat(uri).isNotNull();
    }

    @Test
    void Uri_객체의_필수_인자를_받아서_생성한다(){
        String method = "GET";
        String path = "/";
        Map<String,String> params = new HashMap<>(){{
            put("Host", "localhost:8080");
            put("Connection", "keep-alive");
            put("Accept", "*/*");
        }};

        Uri uri = new Uri(method, path, params);
        assertThat(uri.method()).isEqualTo(method);
        assertThat(uri.path()).isEqualTo(path);
        assertThat(uri.params()).isEqualTo(params);
    }
}
