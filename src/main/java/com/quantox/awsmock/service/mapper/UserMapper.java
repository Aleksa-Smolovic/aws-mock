package com.quantox.awsmock.service.mapper;

import com.quantox.awsmock.domain.Machine;
import com.quantox.awsmock.domain.User;
import com.quantox.awsmock.service.dto.MachineDTO;
import com.quantox.awsmock.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);

    default User fromId(Long id) {
        if (id == null)
            return null;
        User user = new User();
        user.setId(id);
        return user;
    }
}
