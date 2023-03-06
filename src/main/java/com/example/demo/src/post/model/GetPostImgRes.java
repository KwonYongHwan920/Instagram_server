package com.example.demo.src.post.model;


import com.example.demo.src.user.model.GetUserInfoRes;
import com.example.demo.src.user.model.GetUserPostsRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetPostImgRes {
    private int postImgIdx;
    private String imgUrl;

//    private int userIdx;
//    private String name;
//    private String nickName;
//    private String email;

}
