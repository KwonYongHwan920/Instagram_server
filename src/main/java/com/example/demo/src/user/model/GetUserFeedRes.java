package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetUserFeedRes {
    private boolean _isMyFeed; //내 피드인지 다른사람의 피드인지 구별
    private GetUserInfoRes getUserInfo;
    private List<GetUserPostsRes> getUserPosts;
//    private int userIdx;
//    private String name;
//    private String nickName;
//    private String email;

}
