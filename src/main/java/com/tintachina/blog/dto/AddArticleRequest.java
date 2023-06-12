package com.tintachina.blog.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class AddArticleRequest {

    private String title;
    private String content;


}
