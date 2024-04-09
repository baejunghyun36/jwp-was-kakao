package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public final class RequestLine {
    private Method method;
    private Uri uri;
    private HttpVersion httpVersion;

    public RequestLine(BufferedReader br) {
        try {
            (new Parser(br)).parse();
        } catch (IOException e) {
            throw new IllegalArgumentException("Request Line을 파싱할 수 없습니다.");
        }
    }

    public Method method() {
        return method;
    }

    public Uri uri() {
        return uri;
    }

    public HttpVersion httpVersion() {
        return httpVersion;
    }

    private final class Parser {
        private final BufferedReader br;

        Parser(BufferedReader br) {
            this.br = br;
        }

        void parse() throws IOException {
            String[] chunks = br.readLine().split(" ");
            validateSchemeChunk(chunks);
            RequestLine.this.method = Method.of(chunks[0]);
            RequestLine.this.uri = new Uri(chunks[1]);
            RequestLine.this.httpVersion = new HttpVersion(chunks[2]);
        }

        void validateSchemeChunk(String[] chunks) {
            if (chunks.length != 3) {
                throw new IllegalArgumentException("request line 정보가 정확하지 않습니다.");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return method == that.method && Objects.equals(uri, that.uri) && Objects.equals(httpVersion, that.httpVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, uri, httpVersion);
    }
}
