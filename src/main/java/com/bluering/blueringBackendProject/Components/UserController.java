package com.bluering.blueringBackendProject.Components;

import com.bluering.blueringBackendProject.ApiResponse;
import com.bluering.blueringBackendProject.DTO.UserDTO;
import com.bluering.blueringBackendProject.Services.UserService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return ResponseEntity
                    .ok(new ApiResponse(true, "User created successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error creating user", e.getMessage()));
        }
    }

//    @PutMapping("/{userId}")
//    public ResponseEntity<ApiResponse> updateUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
//        try {
//            userDTO.setUserId(userId);
//            userService.updateUser(userDTO);
//            return ResponseEntity
//                    .ok(new ApiResponse(true, "User updated successfully"));
//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(new ApiResponse(false, "Error updating user", e.getMessage()));
//        }
//    }

    @PatchMapping("/{userId}")
    public ApiResponse updateUser(@PathVariable Integer userId, @RequestBody Map<String, Object> updateFields) {
        try {
            userService.updateUser(userId, updateFields);
            return new ApiResponse(true, "User updated successfully", userId);
        } catch (ResourceNotFoundException e) {
            return new ApiResponse(false, "User not found with id: " + userId, null);
        }
    }


//    @GetMapping("/")
//    public ResponseEntity<ApiResponse> getUser() {
//        try {
//            List<UserDTO> users = userService.getUser();
//            return ResponseEntity
//                    .ok(new ApiResponse(true, "Users retrieved successfully", users));
//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse(false, "Error retrieving users", e.getMessage()));
//        }
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<ApiResponse> getUserById(@PathVariable Integer userId) {
//        try {
//            UserDTO user = userService.getUserById(userId);
//            return ResponseEntity
//                    .ok(new ApiResponse(true, "User retrieved successfully", user));
//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(new ApiResponse(false, "User not found", null));
//        }
//    }

    @GetMapping("/")
    public List<UserDTO> getUsers() {
        List<UserDTO> users = userService.getUsers();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found");
        }
        return users;
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable Integer userId) {
        UserDTO userDTO = userService.getUserById(userId);
        if (userDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return userDTO;
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity
                    .ok(new ApiResponse(true, "User deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error deleting user", e.getMessage()));
        }
    }
}

