package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.controller.RequestMapper;
import webserver.request.Method;

import java.net.ServerSocket;
import java.net.Socket;

public class WebApplicationServer {
    private static final Logger logger = LoggerFactory.getLogger(WebApplicationServer.class);
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        int port = DEFAULT_PORT;
        if (args != null && args.length != 0) {
            port = Integer.parseInt(args[0]);
        }
        try (ServerSocket listenSocket = new ServerSocket(port)) {
            logger.info("Web Application Server started {} port.", port);
            registerController();
            acceptClient(listenSocket);
        }
    }

    private static void registerController() {
        RequestMapper.register(Method.GET.name(), "/user/create", (httpRequest, response) -> {
        });
        RequestMapper.register(Method.GET.name(), "/user/create", (httpRequest, response) -> {
        });
    }

    private static void acceptClient(ServerSocket listenSocket) throws Exception {
        Socket connection;
        while ((connection = listenSocket.accept()) != null) {
            Thread thread = new Thread(new RequestHandler(connection));
            thread.start();
        }
    }
}
