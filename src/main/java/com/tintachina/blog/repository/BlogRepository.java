package com.tintachina.blog.repository;

import com.tintachina.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
