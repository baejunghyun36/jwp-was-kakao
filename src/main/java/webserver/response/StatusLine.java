package webserver.response;

import webserver.common.HttpVersion;
import webserver.enums.StatusCode;

import java.util.Objects;

public final class StatusLine {
    private final HttpVersion httpVersion;
    private final int statusCode;
    private final String reasonPhrase;

    public StatusLine(String httpVersion, StatusCode statusCode) {
        this.httpVersion = new HttpVersion(httpVersion);
        this.statusCode = statusCode.value();
        this.reasonPhrase = statusCode.reasonPhrase();
    }

    public String httpVersion() {
        return this.httpVersion.raw();
    }

    public int statusCode() {
        return this.statusCode;
    }

    public String reasonPhrase() {
        return this.reasonPhrase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusLine that = (StatusLine) o;
        return statusCode == that.statusCode && Objects.equals(httpVersion, that.httpVersion) && Objects.equals(reasonPhrase, that.reasonPhrase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpVersion, statusCode, reasonPhrase);
    }

    @Override
    public String toString() {
        return httpVersion() + " " + statusCode() + " " + reasonPhrase();
    }
}