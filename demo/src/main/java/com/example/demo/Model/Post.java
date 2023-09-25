package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id ;

    String title;

    @Column(columnDefinition = "Text")
    String body;
    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id" ,nullable = false)
    Account account;
}
