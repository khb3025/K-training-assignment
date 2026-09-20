package com.example.assignment.boundedContext.post.out;

import com.example.assignment.boundedContext.post.domain.PostMember;
import com.example.assignment.global.RsData.RsData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.util.Optional;

public interface PostMemberRepository extends JpaRepository<PostMember, Long> {
    Optional<PostMember> findByUsername(String username);

    Optional<PostMember> findById(long authorId);
}
