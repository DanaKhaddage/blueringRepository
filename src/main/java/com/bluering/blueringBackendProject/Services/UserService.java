package com.bluering.blueringBackendProject.Services;

import com.bluering.blueringBackendProject.DTO.UserDTO;
import com.bluering.blueringBackendProject.Entities.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserEntity createUser(UserDTO userDTO);
    //UserEntity updateUser(UserDTO userDTO);
    void updateUser(Integer userId, Map<String,Object> entityDto);
    //List<UserDTO>getUser();
    List<UserDTO> getUsers();
    UserDTO getUserById(Integer userId);
    void deleteUser(Integer userId);
}

