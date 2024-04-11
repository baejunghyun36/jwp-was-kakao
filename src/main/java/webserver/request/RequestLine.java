package webserver.request;

import webserver.common.HttpVersion;
import webserver.common.Uri;
import webserver.enums.Method;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public final class RequestLine {
    private static final String FIELD_DELIMITER = " ";
    private static final int METHOD_INDEX = 0;
    private static final int URI_INDEX = 1;
    private static final int VERSION_INDEX = 2;

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
            String[] chunks = br.readLine().split(FIELD_DELIMITER);
            validateSchemeChunk(chunks);
            RequestLine.this.method = Method.of(chunks[METHOD_INDEX]);
            RequestLine.this.uri = new Uri(chunks[URI_INDEX]);
            RequestLine.this.httpVersion = HttpVersion.of(chunks[VERSION_INDEX]);
        }

        void validateSchemeChunk(String[] chunks) {
            if (chunks.length != 3) {
                throw new IllegalArgumentException("Request Line 정보가 정확하지 않습니다.");
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
