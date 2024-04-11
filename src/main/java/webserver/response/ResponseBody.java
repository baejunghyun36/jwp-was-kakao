package webserver.response;

import java.util.Arrays;

public final class ResponseBody {
    private final byte[] contents;

    public ResponseBody(byte[] contents) {
        this.contents = contents;
    }

    public byte[] contents() {
        return this.contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseBody that = (ResponseBody) o;
        return Arrays.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(contents);
    }
}
