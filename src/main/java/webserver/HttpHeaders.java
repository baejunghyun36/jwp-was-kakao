package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class HttpHeaders {
    private Map<String, String> headers;

    public HttpHeaders(InputStream in) {
        try {
            (new Parser(in)).parse();
        } catch (IOException e) {
            throw new IllegalArgumentException("Http 헤더를 파싱할 수 없습니다.");
        }
    }

    public Map<String, String> get() {
        return headers;
    }

    private final class Parser {
        private final BufferedReader br;

        Parser(InputStream in) {
            this.br = new BufferedReader(new InputStreamReader(in));
        }

        void parse() throws IOException {
            HttpHeaders.this.headers = new HashMap<>();
            String line = br.readLine();
            while (line != null && !line.isBlank()) {
                putHeader(line);
                line = br.readLine();
            }
        }

        private void putHeader(String line) {
            String[] keyValue = line.split(": ");
            HttpHeaders.this.headers.put(keyValue[0], keyValue[1]);
        }
    }
}
