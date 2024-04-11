package webserver.controller;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

@FunctionalInterface
public interface Controller {
    void doService(HttpRequest req, HttpResponse response);
}
