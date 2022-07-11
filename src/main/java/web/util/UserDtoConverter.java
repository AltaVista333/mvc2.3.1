package web.util;

import org.springframework.context.annotation.Bean;
import web.model.User;
import web.model.UserDto;

public class UserDtoConverter {

    public static void merge(User user, UserDto dto) {
        user.setAge(Integer.parseInt(dto.getAge()));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
    }
}
