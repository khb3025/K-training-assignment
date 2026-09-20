package com.example.assignment.boundedContext.post.in;

import com.example.assignment.boundedContext.member.app.MemberFacade;
import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.post.app.PostFacade;
import com.example.assignment.boundedContext.post.domain.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class PostDataInit {

    private final PostDataInit self;
    private final MemberFacade memberFacade;
    private final PostFacade postFacade;

    public PostDataInit(
        @Lazy PostDataInit self,
        MemberFacade memberFacade,
        PostFacade postFacade
    ) {
        this.self = self;
        this.memberFacade = memberFacade;
        this.postFacade = postFacade;
    }

    @Bean
    @Order(2)
    public ApplicationRunner postInitDataRunner() {
        return args -> {
            self.makeBasePost();
            self.makeBasePostComment();
        };
    }

    @Transactional
    public void makeBasePost(){

        Member user1 = memberFacade.findByUsername("user1").get();
        Post post1 = postFacade.write(user1, "제목1", "내용1").get();
        Post post2 = postFacade.write(user1, "제목2", "내용2").get();
        Post post3 = postFacade.write(user1, "제목3", "내용3").get();
        Member user2 = memberFacade.findByUsername("user2").get();
        Post post4 = postFacade.write(user2, "제목4", "내용4").get();
        Post post5 = postFacade.write(user2, "제목5", "내용5").get();
        Member user3 = memberFacade.findByUsername("user3").get();
        Post post6 = postFacade.write(user3, "제목6", "내용6").get();

    }

    @Transactional
    public void makeBasePostComment(){
        Post user1post1 = postFacade.findPostById(1).get();
        // 사용자1 의 첫번째 게시물 댓글 유저
        Member postCommentUser1 = memberFacade.findByUsername("user2").get();
        user1post1.addComment(postCommentUser1, "유저2 댓글");

        Post user1post2 = postFacade.findPostById(2).get();
        Member postCommentUser2 = memberFacade.findByUsername("user2").get();
        Member postCommentUser3 = memberFacade.findByUsername("user3").get();
        user1post2.addComment(postCommentUser2, "유저2 댓글");
        user1post2.addComment(postCommentUser3, "유저3 댓글");


        Post user2post4 = postFacade.findPostById(4).get();
        Member postCommentUser4 = memberFacade.findByUsername("user1").get();
        Member postCommentUser5 = memberFacade.findByUsername("user3").get();
        user2post4.addComment(postCommentUser4, "유저1 댓글");
        user2post4.addComment(postCommentUser5, "유저3 댓글");


        Post user2post5 = postFacade.findPostById(5).get();
        Member postCommentUser6 = memberFacade.findByUsername("user3").get();
        Member postCommentUser7 = memberFacade.findByUsername("user2").get();
        user2post5.addComment(postCommentUser6, "유저3 댓글");
        user2post5.addComment(postCommentUser7, "유저2 댓글");


        Post user3post6 = postFacade.findPostById(6).get();
        Member postCommentUser8 = memberFacade.findByUsername("user1").get();
        Member postCommentUser9 = memberFacade.findByUsername("user2").get();
        Member postCommentUser10 = memberFacade.findByUsername("user3").get();
        user3post6.addComment(postCommentUser8, "유저1 댓글");
        user3post6.addComment(postCommentUser9, "유저2 댓글");
        user3post6.addComment(postCommentUser10, "유저3 댓글");
    }

    // 활동 점수 계산 :
    // user1 : 글 작성 3*3 + 댓글 작성 1*2 = 11
    // user2 : 글 작성 3*2 + 댓글 작성 1*4 = 10
    // user3 : 글 작성 3*1 + 댓글 작성 1*4 = 7
}
