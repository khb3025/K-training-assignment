package com.example.assignment.boundedContext.post.app;

import com.example.assignment.boundedContext.member.app.MemberFacade;
import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.post.domain.Post;
import com.example.assignment.boundedContext.post.out.PostRepository;
import com.example.assignment.global.RsData.RsData;
import com.example.assignment.global.publisher.EventPublisher;
import com.example.assignment.share.post.dto.PostDto;
import com.example.assignment.share.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostUseCase {

    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public RsData<Post> write(
            Member author,
            String title,
            String content
    ) {
        Post post = postRepository.save(
                new Post(author, title, content)
        );
        // 글 작성 이벤트 발행
        eventPublisher.publish(new PostCreatedEvent(new PostDto(post)));

        return new RsData<>(
            "201",
            "%s 님의 게시글이 작성되었습니다.".formatted(author.getNickname()),
            post
        );
    }

    public Optional<Post> findPostById(long id) {
        return postRepository.findById(id);
    }
}
