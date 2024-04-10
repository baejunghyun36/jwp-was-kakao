package webserver.controller;

import db.DataBase;
import model.User;
import webserver.dto.UserDto;
import webserver.enums.MediaType;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

public class PostUserJoinController implements Controller {
    @Override
    public void doService(HttpRequest req, HttpResponse response) {
        UserDto userDto = UserDto.from(req.contents());
        User user = User.from(userDto);
        User userOnDataBase = DataBase.findUserById(user.getUserId());
        if (userOnDataBase != null) {
            response.setConflict("DUPLICATED".getBytes(), MediaType.TEXT_PLAIN.value());
            return;
        }
        DataBase.addUser(user);
        response.setMovedTemporarily(HttpResponse.DEFAULT_ENTRY);
    }
}
