package webserver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Uri {
    private final String path;
    private final Map<String, String> params;

    public Uri(String path, Map<String, String> params) {
        this.path = path;
        this.params = params;
    }

    public String path() {
        return this.path;
    }

    public Map<String, String> params() {
        return this.params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uri uri = (Uri) o;
        return Objects.equals(path, uri.path) && Objects.equals(params, uri.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, params);
    }

    private final class Parser {
        private final String uri;

        Parser(String uri) {
            Objects.requireNonNull(uri);
            this.uri = uri;
        }

        void parse() {
            Uri.this.params = new HashMap<>();
            String[] chunks = this.uri.split("\\?");
            if (chunks[0].isBlank()) {
                chunks[0] = DEFAULT_PATH;
            }
            validateAndExtractPath(chunks[0]);
            validateAndExtractParams(chunks);
        }

        private void validateAndExtractPath(String path) {
            Matcher matcher = PATH_PATTERN.matcher(path);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Path 형태가 올바르지 않습니다.");
            }
            Uri.this.path = path;
        }

        private void validateAndExtractParams(String[] chunks) {
            if (chunks.length > 2) {
                throw new IllegalArgumentException("쿼리 파라미터의 구분자는 1개만 주어져야 합니다.");
            }
            if (chunks.length == 1) {
                return;
            }
            extractParams(chunks);
        }

        private void extractParams(String[] chunks) {
            Matcher matcher = Uri.PARAM_PATTERN.matcher(chunks[1]);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("쿼리 파라미터 구성이 올바르지 않습니다.");
            }
            String[] params = chunks[1].split("&");
            Arrays.stream(params).forEach(this::putParam);
        }

        private void putParam(String param) {
            String[] keyValue = param.split("=");
            Uri.this.params.put(keyValue[0], keyValue[1]);
        }
    }
}