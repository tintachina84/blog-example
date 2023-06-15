package com.tintachina.blog.controller;

import com.tintachina.blog.dto.ArticleListViewResponse;
import com.tintachina.blog.dto.ArticleViewResponse;
import com.tintachina.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping ("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = this.blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    // a method to get a single article by id
    @GetMapping("/articles/{id}")
    public String getArticleById(@PathVariable Long id, Model model) {
        ArticleViewResponse article = new ArticleViewResponse(this.blogService.findById(id));
        model.addAttribute("article", article);

        return "article";
    }

    @GetMapping("new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            model.addAttribute("article", new ArticleViewResponse(this.blogService.findById(id)));
        }
        return "newArticle";
    }

}
