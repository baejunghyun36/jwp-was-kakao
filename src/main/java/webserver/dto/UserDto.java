package webserver.dto;

import java.util.Map;
import java.util.Objects;

public final class UserDto {
    private static final String userIdField = "userId";
    private static final String passwordField = "password";
    private static final String nameField = "name";
    private static final String emailField = "email";

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    private UserDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public static UserDto from(Map<String, Object> map) {
        String userId = (String) Objects.requireNonNull(map.get(userIdField));
        String password = (String) Objects.requireNonNull(map.get(passwordField));
        String name = (String) Objects.requireNonNull(map.get(nameField));
        String email = (String) Objects.requireNonNull(map.get(emailField));
        return new UserDto(userId, password, name, email);
    }

    public String userId() {
        return userId;
    }

    public String password() {
        return password;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }
}
