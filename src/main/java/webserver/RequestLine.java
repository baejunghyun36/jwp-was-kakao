package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class RequestLine {
    private Method method;
    private Uri uri;
    private HttpVersion httpVersion;

    public RequestLine(InputStream in) {
        try {
            (new Parser(in)).parse();
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

        Parser(InputStream in) {
            this.br = new BufferedReader(new InputStreamReader(in));
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
}
