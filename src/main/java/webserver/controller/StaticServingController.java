package webserver.controller;

import utils.FileIoUtils;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

import static webserver.enums.MediaType.TEXT_CSS;
import static webserver.enums.MediaType.TEXT_HTML;

public class StaticServingController implements Controller {
    private static final String TEMPLATE_PATH = "./templates";
    private static final String STATIC_PATH = "./static";

    @Override
    public void doService(HttpRequest req, HttpResponse response) {
        try {
            byte[] body = getBody(req);
            String contentType = getContentType(req);
            response.setStatusOK(body, contentType);
        } catch (Exception ex) {
            response.setNotFound();
        }
    }

    private static byte[] getBody(HttpRequest httpRequest) throws IOException, URISyntaxException {
        try {
            return getTemplatesBody(httpRequest);
        } catch (NullPointerException e) {
            return getStaticBody(httpRequest);
        } catch (Exception e) {
            throw new IllegalArgumentException("찾을 수 없는 자원입니다.");
        }
    }

    private static byte[] getTemplatesBody(HttpRequest httpRequest) throws IOException, URISyntaxException {
        return FileIoUtils.loadFileFromClasspath(TEMPLATE_PATH + httpRequest.path());
    }

    private static byte[] getStaticBody(HttpRequest httpRequest) throws IOException, URISyntaxException {
        return FileIoUtils.loadFileFromClasspath(STATIC_PATH + httpRequest.path());
    }

    private static String getContentType(HttpRequest httpRequest) {
        String accept = httpRequest.accept();
        String contentType = httpRequest.contentType();
        if (accept.contains(TEXT_CSS.value())) {
            return TEXT_CSS.value();
        }
        if (!contentType.isBlank()) {
            return contentType;
        }
        return TEXT_HTML.value();
    }
}
