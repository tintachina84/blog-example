package com.tintachina.blog.dto;

import com.tintachina.blog.domain.Article;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class AddArticleRequest {

    private String title;
    private String content;
    private String author;

    public Article toEntity(String author) {
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
