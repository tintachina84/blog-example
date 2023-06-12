package com.tintachina.blog.mapper;

import com.tintachina.blog.domain.Article;
import com.tintachina.blog.dto.AddArticleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArticleMapper extends GenericMapper<AddArticleRequest, Article> {

}
