package webserver.common;

import java.util.Objects;

public final class HttpVersion {
    private static final String SCHEME_DELIMITER = "/";
    private static final String VERSION_DELIMITER = "\\.";

    private String scheme;
    private int major;
    private int minor;
    private final String raw;

    public HttpVersion(String version) {
        (new HttpVersion.Parser(version)).parse();
        this.raw = version;
    }

    private void validateSchemeChunk(String[] chunks) {
        checkExistence(chunks);
        checkCaseSensitive(chunks[0]);
    }

    private void checkExistence(String[] chunks) {
        if (chunks.length < 1) {
            throw new IllegalArgumentException("스킴이 존재하지 않습니다.");
        }
    }

    private void checkCaseSensitive(String scheme) {
        if (!scheme.toUpperCase().equals(scheme)) {
            throw new IllegalArgumentException("스킴은 대문자로 구성되어야 합니다.");
        }
    }

    private String[] validateVersionChunks(String[] chunks) {
        checkChunkLength(chunks);
        String[] versions = chunks[1].split(VERSION_DELIMITER);
        checkVersionsLength(versions);
        return versions;
    }

    private void checkChunkLength(String[] chunks) {
        if (chunks.length < 2) {
            throw new IllegalArgumentException("버전 필드를 확인할 수 없습니다.");
        }
    }

    private void checkVersionsLength(String[] versions) {
        if (versions.length != 2) {
            throw new IllegalArgumentException("메이저 버전과 마이너 버전을 찾을 수 없습니다.");
        }
    }

    public String scheme() {
        return this.scheme;
    }

    public int major() {
        return this.major;
    }

    public int minor() {
        return this.minor;
    }

    public String raw() {
        return this.raw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpVersion that = (HttpVersion) o;
        return Objects.equals(raw, that.raw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raw);
    }

    private final class Parser {
        private final String version;

        Parser(String version) {
            this.version = version;
        }

        void parse() {
            String[] chunks = version.split(SCHEME_DELIMITER);
            validateSchemeChunk(chunks);
            HttpVersion.this.scheme = chunks[0];
            String[] versions = validateVersionChunks(chunks);
            HttpVersion.this.major = Integer.parseInt(versions[0]);
            HttpVersion.this.minor = Integer.parseInt(versions[1]);
        }
    }
}
