package com.tintachina.blog.controller;

import com.tintachina.blog.domain.Article;
import com.tintachina.blog.dto.AddArticleRequest;
import com.tintachina.blog.dto.ArticleResponse;
import com.tintachina.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest addArticleRequest) {
        Article savedArticle = this.blogService.save(addArticleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = this.blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticleById(@PathVariable Long id) {
        ArticleResponse article = new ArticleResponse(this.blogService.findById(id));
        return ResponseEntity.ok().body(article);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long id) {
        this.blogService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
