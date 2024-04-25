package com.bluering.blueringBackendProject.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@Table(name = "user", schema = "internship", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id@Column(name = "userID")
    private Integer userId;
    @Basic@Column(name = "username")
    private String username;
    @Basic@Column(name = "password")
    private String password;
    @Basic@Column(name = "email")
    private String email;
}
