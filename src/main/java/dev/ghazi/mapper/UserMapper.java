package dev.ghazi.mapper;

import dev.ghazi.dto.UserRecord;
import dev.ghazi.model.User;

public class UserMapper {

    public static UserRecord toUserRecord(User user) {
        return new UserRecord(
                user.getUsername(),
                user.getName(),
                user.getFunction(),
                user.getLevel(),
                user.getDepartment(),
                user.isAlive());
    }
}
