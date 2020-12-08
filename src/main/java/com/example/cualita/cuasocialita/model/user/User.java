package com.example.cualita.cuasocialita.model.user;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;


    @Column(nullable = false, length = 80, unique = true)
    private String email;

    @JsonIgnore
    @Lob
    @Column(nullable = false)
    private String password;
    
    @JsonIgnore
    @Lob
    private String fcmToken;

    @Column(nullable = false)
    private Integer status = 0;

    @JsonIgnore
    @Column(nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
    
    @JsonIgnore
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @JsonIgnore
    private OffsetDateTime deletedAt;

    public User(){

    }

    public User(String email, String password, String username){
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
