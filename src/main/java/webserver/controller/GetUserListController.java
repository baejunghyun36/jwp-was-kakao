package webserver.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import db.DataBase;
import model.User;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.io.IOException;
import java.util.*;

public final class GetUserListController implements Controller {

    private static final TemplateLoader loader;
    private static final Handlebars handlebars;

    private static String USER_LIST_PAGE = "user/list";
    private static String USERS_KEY = "users";

    static {
        loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");

        handlebars = new Handlebars(loader);
    }

    @Override
    public void doService(HttpRequest req, HttpResponse response) throws IOException {
        Collection<User> values = DataBase.findAll();
        List<User> userList = new ArrayList<>(values);
        response.setStatusOK(renderUserlistPage(userList).getBytes(), req.contentType());
    }

    private String renderUserlistPage(List<User> users) throws IOException {
        Map<String, Object> model = new HashMap<>();
        model.put(USERS_KEY, users);

        Template template = handlebars.compile(USER_LIST_PAGE);
        return template.apply(model);
    }
}