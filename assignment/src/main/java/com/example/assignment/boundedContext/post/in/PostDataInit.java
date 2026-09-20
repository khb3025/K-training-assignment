package com.example.assignment.boundedContext.post.in;

import com.example.assignment.boundedContext.post.app.PostFacade;
import com.example.assignment.boundedContext.post.domain.Post;
import com.example.assignment.boundedContext.post.domain.PostMember;
import com.example.assignment.global.RsData.RsData;
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
    private final PostFacade postFacade;

    public PostDataInit(
        @Lazy PostDataInit self,
        PostFacade postFacade
    ) {
        this.self = self;
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
        if(postFacade.count() > 0) return;

        PostMember user1 = postFacade.findByUsername("user1").getData();
        RsData<Post> post1RsData = postFacade.write(user1, "제목1", "내용1");
        log.debug("post1RsData msg : {}", post1RsData.getMsg());
        RsData<Post> post2RsData = postFacade.write(user1, "제목2", "내용2");
        log.debug("post2RsData msg : {}", post2RsData.getMsg());
        RsData<Post> post3RsData = postFacade.write(user1, "제목3", "내용3");
        log.debug("post3RsData msg : {}", post3RsData.getMsg());

        PostMember user2 = postFacade.findByUsername("user2").getData();
        RsData<Post> post4RsData = postFacade.write(user2, "제목4", "내용4");
        log.debug("post4RsData msg : {}", post4RsData.getMsg());
        RsData<Post> post5RsData = postFacade.write(user2, "제목5", "내용5");
        log.debug("post5RsData msg : {}", post5RsData.getMsg());

        PostMember user3 = postFacade.findByUsername("user3").getData();
        RsData<Post> post6RsData = postFacade.write(user3, "제목6", "내용6");
        log.debug("post6RsData msg : {}", post6RsData.getMsg());

    }

    @Transactional
    public void makeBasePostComment(){
        Post user1post1 = postFacade.findPostById(1).getData();
        if(user1post1.hasComment()) return;
        // 사용자1 의 첫번째 게시물 댓글 유저
        PostMember postCommentUser1 = postFacade.findByUsername("user2").getData();
        user1post1.addComment(postCommentUser1, "유저2 댓글");

        Post user1post2 = postFacade.findPostById(2).getData();
        PostMember postCommentUser2 = postFacade.findByUsername("user2").getData();
        PostMember postCommentUser3 = postFacade.findByUsername("user3").getData();
        user1post2.addComment(postCommentUser2, "유저2 댓글");
        user1post2.addComment(postCommentUser3, "유저3 댓글");


        Post user2post4 = postFacade.findPostById(4).getData();
        PostMember postCommentUser4 = postFacade.findByUsername("user1").getData();
        PostMember postCommentUser5 = postFacade.findByUsername("user3").getData();
        user2post4.addComment(postCommentUser4, "유저1 댓글");
        user2post4.addComment(postCommentUser5, "유저3 댓글");


        Post user2post5 = postFacade.findPostById(5).getData();
        PostMember postCommentUser6 = postFacade.findByUsername("user3").getData();
        PostMember postCommentUser7 = postFacade.findByUsername("user2").getData();
        user2post5.addComment(postCommentUser6, "유저3 댓글");


        Post user3post6 = postFacade.findPostById(6).getData();
        PostMember postCommentUser8 = postFacade.findByUsername("user1").getData();
        PostMember postCommentUser9 = postFacade.findByUsername("user2").getData();
        PostMember postCommentUser10 = postFacade.findByUsername("user3").getData();
        user3post6.addComment(postCommentUser8, "유저1 댓글");
        user3post6.addComment(postCommentUser9, "유저2 댓글");
    }

    // 활동 점수 계산 :
    // user1 : 글 작성 3*3 + 댓글 작성 1*2 = 11
    // user2 : 글 작성 3*2 + 댓글 작성 1*3 = 9
    // user3 : 글 작성 3*1 + 댓글 작성 1*3 = 6
}
