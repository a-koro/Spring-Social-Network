package com.connector.beta.mapper;

import com.connector.beta.dto.UserDto;
import com.connector.beta.entities.MyUser;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MyUser map(UserDto userDto);

    UserDto mapToDto(MyUser user);

    List<UserDto> mapListToDto(List<MyUser> users);
}
