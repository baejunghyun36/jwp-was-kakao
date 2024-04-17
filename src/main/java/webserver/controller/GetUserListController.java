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

    @Override
    public void doService(HttpRequest req, HttpResponse response) throws IOException {
        Collection<User> values = DataBase.findAll();
        List<User> userList = new ArrayList<>(values);
        response.setStatusOK(renderUserlistPage(userList).getBytes(), req.contentType());
    }

    private String renderUserlistPage(List<User> users) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");

        Handlebars handlebars = new Handlebars(loader);

        Map<String, Object> model = new HashMap<>();
        model.put("users", users);

        Template template = handlebars.compile("user/list");
        return template.apply(model);
    }
}