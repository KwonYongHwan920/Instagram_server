package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.post.model.GetPostsRes;
import com.example.demo.src.post.model.PostPostsReq;
import com.example.demo.src.post.model.PostPostsRes;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/posts")
public class PostController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PostProvider postProvider;
    @Autowired
    private final PostService postService;
    @Autowired
    private final JwtService jwtService;




    public PostController(PostProvider postProvider, PostService postService, JwtService jwtService){
        this.postProvider = postProvider;
        this.postService = postService;
        this.jwtService = jwtService;
    }



    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetPostsRes>> getPosts(@RequestParam int userIdx) {
        try{

            List<GetPostsRes> getPostsRes = postProvider.retrievePosts(userIdx);
            return new BaseResponse<>(getPostsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //게시글 생성

    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostPostsRes> createPosts(@RequestBody PostPostsReq postPostsReq) {
        if(postPostsReq.getContent() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_POSTS_EMPTY_CONTENTS);
        }
        if(postPostsReq.getContent().length()>450){
            return new BaseResponse<>(BaseResponseStatus.POST_POSTS_INVALID_CONTENTS);
        }
        if(postPostsReq.getPostImgsUrl() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_POSTS_EMPTY_IMGURL);
        }

        try{
            int userIdxByJwt = jwtService.getUserIdx();
            PostPostsRes postPostsRes = postService.createPosts(userIdxByJwt, postPostsReq);
            return new BaseResponse<>(postPostsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
