package com.connector.beta.mapper;

import com.connector.beta.dto.UserDto;
import com.connector.beta.dto.UserProfileImageDto;
import com.connector.beta.entities.MyUser;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MyUser map(UserDto userDto);

    UserDto mapToDto(MyUser user);

    List<UserDto> mapListToDto(List<MyUser> users);

    UserProfileImageDto mapUserToUserProfileDto(MyUser myUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(UserProfileImageDto userProfileImageDto, @MappingTarget MyUser myUser);
}
