package com.example.assignment.boundedContext.post.app;

import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.post.domain.Post;
import com.example.assignment.boundedContext.post.domain.PostMember;
import com.example.assignment.boundedContext.post.in.dto.PostWriteResponseDto;
import com.example.assignment.global.RsData.RsData;
import com.example.assignment.share.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostFacade {
    private final PostUseCase postUseCase;

    @Transactional
    public RsData<Post> write(
            PostMember author,
            String title,
            String content
    ) {
        return postUseCase.write(author, title, content);
    }

    @Transactional
    public RsData<PostWriteResponseDto> write(
            long authorId,
            String title,
            String content
    ) {
        return postUseCase.write(authorId, title, content);
    }

    public RsData<Post> findPostById(long id) {
        return postUseCase.findPostById(id);
    }

    public RsData<PostMember> syncMember(MemberDto author) {
        return postUseCase.syncMember(author);
    }

    public RsData<PostMember> findByUsername(String username) {
        return postUseCase.findByUsername(username);
    }

    public long count() {
        return postUseCase.count();
    }

}
