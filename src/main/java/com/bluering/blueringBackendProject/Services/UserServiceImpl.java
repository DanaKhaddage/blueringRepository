package com.bluering.blueringBackendProject.Services;

import com.bluering.blueringBackendProject.DTO.UserDTO;
import com.bluering.blueringBackendProject.Entities.UserEntity;
import com.bluering.blueringBackendProject.Mapper.UserMapper;
import com.bluering.blueringBackendProject.Repositories.UserRepository;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.userDTOToUserEntity(userDTO);
        UserEntity savedEntity=userRepository.save(userEntity);
        return savedEntity;
    }

//    public UserEntity updateUser(UserDTO userDTO) {
//        UserEntity user=userRepository.findById(userDTO.getUserId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found"));
//        user.setUsername(userDTO.getUsername());
//        user.setUserId(userDTO.getUserId());
//        user.setPassword(userDTO.getPassword());
//        user.setEmail(userDTO.getEmail());
//        return userRepository.save(user);
//    }

    public void updateUser(Integer userId, Map<String, Object> entityDto) {
        UserEntity entityToUpdate = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with number: " + userId));
        updateEntity(entityDto, entityToUpdate, UserEntity.class);
        userRepository.saveAndFlush(entityToUpdate);
    }

    public void updateEntity(Map<String, Object> entityDTO, Object entityToUpdate, Class entityToUpdateClass) {
        entityDTO.forEach((k, v) -> {
            try {
                Field field = FieldUtils.getField(entityToUpdateClass, k, true);
                if (field != null) {
                    field.setAccessible(true);
                    field.set(entityToUpdate, v);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Handle the exception as needed
            }
        });
    }

    public List<UserDTO> getUser(){
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity  -> userMapper.userEntityToUserDTO(userEntity))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Integer userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.userEntityToUserDTO(userEntity);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

}