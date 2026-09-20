package com.example.assignment.boundedContext.post.app;

import com.example.assignment.boundedContext.post.domain.Post;
import com.example.assignment.boundedContext.post.domain.PostMember;
import com.example.assignment.boundedContext.post.in.dto.PostWriteResponseDto;
import com.example.assignment.boundedContext.post.out.PostMemberRepository;
import com.example.assignment.boundedContext.post.out.PostRepository;
import com.example.assignment.global.RsData.RsData;
import com.example.assignment.global.publisher.EventPublisher;
import com.example.assignment.share.member.dto.MemberDto;
import com.example.assignment.share.member.out.MemberApiClient;
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
    private final PostMemberRepository postMemberRepository;
    private final MemberApiClient memberApiClient;

    public RsData<Post> write(
            PostMember author,
            String title,
            String content
    ) {
        Post post = postRepository.save(
                new Post(author, title, content)
        );
        // 글 작성 이벤트 발행
        eventPublisher.publish(new PostCreatedEvent(new PostDto(post)));
        // 보안 팁 문구 가져오기
        String secureTip = memberApiClient.getSecureTip();
        return new RsData<>(
            "201",
            "%s 님의 게시글이 작성되었습니다. 보안 팁 : %s".formatted(author.getNickname(), secureTip),
            post
        );
    }

    public RsData<PostWriteResponseDto> write(
            long authorId,
            String title,
            String content
    ) {
        PostMember author = postMemberRepository.findById(authorId).orElse(null);
        if(author == null){
            return new RsData<>(
                "404",
                "해당하는 회원이 없습니다.",
                null
            );
        }
        Post post = postRepository.save(
                new Post(author, title, content)
        );
        // Controller 반환 값 셋팅
        PostWriteResponseDto postWriteResponseDto = new PostWriteResponseDto(post);
        // 글 작성 이벤트 발행
        eventPublisher.publish(new PostCreatedEvent(new PostDto(post)));
        // 보안 팁 문구 가져오기
        String secureTip = memberApiClient.getSecureTip();
        return new RsData<>(
                "201",
                "%s 님의 게시글이 작성되었습니다. 보안 팁 : %s".formatted(author.getNickname(), secureTip),
                postWriteResponseDto
        );
    }

    public RsData<Post> findPostById(long id) {

        return postRepository
                .findById(id)
                .map(post -> new RsData<>("200", "%s 게시글을 찾았습니다.".formatted(post.getTitle()), post))
                .orElseGet(() -> new RsData<>("404", "%s 게시글을 찾을 수 없습니다.".formatted(id), null));
    }

    public RsData<PostMember> syncMember(MemberDto author) {
        PostMember postMember = new PostMember(
                author.getId(),
                author.getCreateDate(),
                author.getModifyDate(),
                author.getUsername(),
                author.getNickname(),
                "",
                author.getActivityScore()
        );
        postMemberRepository.save(postMember);
        return new RsData<>("201", "%s 회원 정보가 동기화되었습니다.".formatted(author.getUsername()), postMember);
    }

    public RsData<PostMember> findByUsername(String username) {
        return postMemberRepository.findByUsername(username)
                .map(postMember -> new RsData<>(
                        "200",
                        "%s 회원 정보가 조회되었습니다.".formatted(username),
                        postMember
                ))
                .orElseGet(() -> new RsData<>(
                        "404",
                        "회원정보가 조회되지 않습니다.",
                        null
                ));
    }

    public RsData<PostMember> findByAuthorId(long authorId) {
        return postMemberRepository.findById(authorId)
                .map(postMember -> new RsData<>(
                        "200",
                        "%s 회원 정보가 조회되었습니다.".formatted(postMember.getUsername()),
                        postMember
                ))
                .orElseGet(() -> new RsData<>(
                        "404",
                        "회원정보가 조회되지 않습니다.",
                        null
                ));
    }

    public long count() {
        return postRepository.count();
    }

    public Optional<PostMember> findAuthorByAuthorId(long authorId) {
        return postMemberRepository.findById(authorId);
    }
}
