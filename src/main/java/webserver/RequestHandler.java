package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.common.HttpCookie;
import webserver.controller.RequestMapper;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.ResponseRenderer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            ResponseRenderer renderer = new ResponseRenderer(out);
            HttpRequest httpRequest = HttpRequest.of(in);
            HttpResponse httpResponse = new HttpResponse();
            HttpCookie cookie = new HttpCookie(httpRequest.cookie());
            RequestMapper.doService(httpRequest, httpResponse, cookie);
            renderer.render(httpResponse, httpRequest);
        } catch (IOException | URISyntaxException e) {
            logger.error(e.getMessage());
        }
    }
}
