package webserver.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import utils.FileIoUtils;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import static webserver.enums.MediaType.TEXT_CSS;
import static webserver.enums.MediaType.TEXT_HTML;

public class StaticServingController implements Controller {
    private static final String EXT_DELIMITER = ".";
    private static final String HANDLEBARS_EXT_NAME = ".html";
    private static final String TEMPLATE_PATH = "/templates";
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
        } catch (FileNotFoundException | NullPointerException e) {
            return getStaticBody(httpRequest);
        } catch (Exception e) {
            throw new IllegalArgumentException("찾을 수 없는 자원입니다.");
        }
    }

    private static byte[] getTemplatesBody(HttpRequest httpRequest) throws IOException, URISyntaxException {
        ClassPathTemplateLoader loader = new ClassPathTemplateLoader(TEMPLATE_PATH, HANDLEBARS_EXT_NAME);
        Handlebars handlebars = new Handlebars(loader);
        Template compile = handlebars.compile(httpRequest.path().substring(0, httpRequest.path().lastIndexOf(EXT_DELIMITER)));
        return compile.apply(httpRequest.attribute()).getBytes();
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
