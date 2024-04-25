package com.bluering.blueringBackendProject.Repositories;

import com.bluering.blueringBackendProject.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
