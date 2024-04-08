package webserver;

public final class RequestLine {
    private Method method;
    private Uri uri;
    private HttpVersion httpVersion;

    public RequestLine(String requestLine) {
        (new RequestLine.Parser(requestLine)).parse();
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
        private final String requestLine;

        Parser(String requestLine) {
            this.requestLine = requestLine;
        }

        void parse() {
            String[] chunks = requestLine.split(" ");
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
