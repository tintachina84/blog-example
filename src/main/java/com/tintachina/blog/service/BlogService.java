package com.tintachina.blog.service;

import com.tintachina.blog.domain.Article;
import com.tintachina.blog.dto.AddArticleRequest;
import com.tintachina.blog.mapper.ArticleMapper;
import com.tintachina.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final ArticleMapper articleMapper;

    // add blog article.
    public Article save(AddArticleRequest addArticleRequest) {
        return this.blogRepository.save(this.articleMapper.toEntity(addArticleRequest));
    }

    // find all blog articles.
    public List<Article> findAll() {
        return this.blogRepository.findAll();
    }

    public Article findById(Long id) {
        return this.blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
    }

    public void deleteById(Long id) {
        this.blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, AddArticleRequest addArticleRequest) {
        Article article = this.blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
        article.update(addArticleRequest.getTitle(), addArticleRequest.getContent());

        return article;
    }


}
