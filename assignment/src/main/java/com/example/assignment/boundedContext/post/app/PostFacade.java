package com.example.assignment.boundedContext.post.app;

import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostFacade {
    private final PostUseCase postUseCase;

    @Transactional
    public Optional<Post> write(
            Member author,
            String title,
            String content
    ) {
        return postUseCase.write(author, title, content);
    }

    public Optional<Post> findPostById(long id) {
        return postUseCase.findPostById(id);
    }
}
