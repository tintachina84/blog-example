package com.tintachina.blog.service;

import com.tintachina.blog.domain.Article;
import com.tintachina.blog.dto.AddArticleRequest;
import com.tintachina.blog.mapper.ArticleMapper;
import com.tintachina.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final ArticleMapper articleMapper;

    // add blog article.
    public Article save(AddArticleRequest addArticleRequest) {
        return this.blogRepository.save(this.articleMapper.toEntity(addArticleRequest));
    }
}
