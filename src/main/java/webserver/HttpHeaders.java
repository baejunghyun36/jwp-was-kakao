package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class HttpHeaders {
    private Map<String, String> headers;

//    public HttpHeaders(InputStream in) {
//        try {
//            (new Parser(in)).parse();
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Http 헤더를 파싱할 수 없습니다.");
//        }
//    }

    public HttpHeaders(BufferedReader br) {
        try {
            (new Parser(br)).parse();
        } catch (IOException e) {
            throw new IllegalArgumentException("Http 헤더를 파싱할 수 없습니다.");
        }
    }

    public Map<String, String> get() {
        return headers;
    }

    private final class Parser {
        private final BufferedReader br;

//        Parser(InputStream in) {
//            this.br = new BufferedReader(new InputStreamReader(in));
//        }

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
            String[] keyValue = line.split(": ");
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
