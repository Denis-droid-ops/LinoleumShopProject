package com.kuznetsov.linoleum.testData;

import com.kuznetsov.linoleum.dto.CreateUserDto;
import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class UserTestData {

    public static final User NEW_USER = new User();
    public static final User USER1 = new User(1,"Denis","denis.denis.kuznecov@mail.ru","222",89179292245L,Role.ADMIN);
    public static final UserDto USER1_DTO = new UserDto(1,"Denis","denis.denis.kuznecov@mail.ru",89179292245L,Role.ADMIN);
    public static final User USER2 = new User(2,"Dima","Petrov59@mail.ru","444",89609292245L,Role.USER);
    public static final User USER3 = new User(3,"Oleg","Kazan88@mail.ru","333",89659292645L,Role.USER);
    public static final User USER4 = new User(4,"Galina","Sidorova23@mail.ru","777",89099292245L,Role.USER);
    public static final User USER5 = new User(5,"Jack","Jackkk4@mail.ru","555",89754548899L, Role.USER);

    public static final Optional<User> LOGGED_USER = Optional.of(new User(USER1.getId(),USER1.getName(),USER1.getEmail()
    ,USER1.getPassword(),USER1.getPhoneNumber(),USER1.getRole()));

    public static final Integer USER2_ID = 2;
    public static final Integer USER3_ID = 3;
    public static final Integer USER4_ID = 4;
    public static final User UPDATED_USER1 = new User(1,"UpdatedUser","UpdatedUser1@mail.ru","2222",89179292247L,Role.USER);
    public static final User USER4_WITH_UPDATED_ROLE = new User(4,"Galina","Sidorova23@mail.ru","777",89099292245L,Role.ADMIN);
    public static final List<User> ALL_USERS = new ArrayList<>(List.of(USER1,USER2,USER3,USER4));

    public static User getNewUser(){
        NEW_USER.setName("New user");
        NEW_USER.setEmail("NewEmail1@mail.ru");
        NEW_USER.setPassword("222");
        NEW_USER.setPhoneNumber(89656039490L);
        NEW_USER.setRole(Role.USER);
        return NEW_USER;
    }


}
