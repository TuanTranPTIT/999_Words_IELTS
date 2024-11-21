package com.example.__words_ielts.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "invalidated_token")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvalidatedToken {
    @Id
    private String id;
    private Date expiryTime;
}
