package com.example.assignment.boundedContext.post.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
public class Post {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
