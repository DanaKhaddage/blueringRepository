package com.bluering.blueringBackendProject.Mapper;
import com.bluering.blueringBackendProject.DTO.UserDTO;
import com.bluering.blueringBackendProject.Entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userEntityToUserDTO(UserEntity userEntity);
    UserEntity  userDTOToUserEntity (UserDTO userDTO);
}
