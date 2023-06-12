package com.tintachina.blog.controller;

import com.tintachina.blog.domain.Article;
import com.tintachina.blog.dto.AddArticleRequest;
import com.tintachina.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest addArticleRequest) {
        Article savedArticle = this.blogService.save(addArticleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }
}
