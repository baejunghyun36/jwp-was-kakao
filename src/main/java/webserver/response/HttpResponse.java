package webserver.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class HttpResponse {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private final DataOutputStream dos;

    public HttpResponse(OutputStream out) {
        this.dos = new DataOutputStream(out);
    }

    public void response200(byte[] body, String contentType) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: " + contentType + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + body.length + "\r\n");
            dos.writeBytes("\r\n");
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void response404() {
        try {
            byte[] notFoundTemplateMessage = "NOT FOUND".getBytes();
            dos.writeBytes("HTTP/1.1 404 Not Found \r\n");
            dos.writeBytes("Content-Type: text/plain\r\n");
            dos.writeBytes("Content-Length: " + notFoundTemplateMessage.length + "\r\n");
            dos.writeBytes("\r\n");
            dos.write(notFoundTemplateMessage, 0, notFoundTemplateMessage.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    public void response302(String location) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: " + location + "\r\n");
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
