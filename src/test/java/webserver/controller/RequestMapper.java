package webserver.controller;

import webserver.request.Method;

import java.util.Objects;

public final class RequestMapper {
//    private static final Map<RequestLine>

    private static final class Entry {
        private final Method method;
        private final String path;

        public Entry(Method method, String path) {
            this.method = method;
            this.path = path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return method == entry.method && Objects.equals(path, entry.path);
        }

        @Override
        public int hashCode() {
            return Objects.hash(method, path);
        }
    }
}
