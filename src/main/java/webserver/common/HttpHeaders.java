package webserver.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class HttpHeaders {
    private static final String HEADER_DELIMITER = ": ";

    private Map<String, String> headers;

    public HttpHeaders(BufferedReader br) {
        try {
            (new Parser(br)).parse();
        } catch (IOException e) {
            throw new IllegalArgumentException("Http 헤더를 파싱할 수 없습니다.");
        }
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(headers.get(key));
    }

    private final class Parser {
        private final BufferedReader br;

        Parser(BufferedReader br) {
            this.br = br;
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
            String[] keyValue = line.split(HEADER_DELIMITER);
            HttpHeaders.this.headers.put(keyValue[0], keyValue[1]);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpHeaders headers1 = (HttpHeaders) o;
        return Objects.equals(headers, headers1.headers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headers);
    }
}
