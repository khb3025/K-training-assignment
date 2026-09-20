package com.example.assignment.boundedContext.post.out;

import com.example.assignment.boundedContext.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
