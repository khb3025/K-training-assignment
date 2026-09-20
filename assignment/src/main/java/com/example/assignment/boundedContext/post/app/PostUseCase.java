package com.example.assignment.boundedContext.post.app;

import com.example.assignment.boundedContext.member.app.MemberFacade;
import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.post.domain.Post;
import com.example.assignment.boundedContext.post.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostUseCase {

    private final PostRepository postRepository;

    public Optional<Post> write(
            Member author,
            String title,
            String content
    ) {
        Post post = postRepository.save(
                new Post(author, title, content)
        );
        return Optional.of(post);
    }

    public Optional<Post> findPostById(long id) {
        return postRepository.findById(id);
    }
}
