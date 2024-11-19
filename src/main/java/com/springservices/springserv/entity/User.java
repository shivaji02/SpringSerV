package com.springservices.springserv.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email; // email should be unique

   @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password should be alphanumeric")
   private String password;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt = LocalDateTime.now();
    private String token;
   private Double latitude;
   private Double longitude;
   private String LocationName;

    //    @Column(nullable = false)

//    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "token should be alphanumeric")
//    private String token;

}
