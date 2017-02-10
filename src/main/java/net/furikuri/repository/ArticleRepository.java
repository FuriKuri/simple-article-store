package net.furikuri.repository;


import net.furikuri.domain.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);

    List<Article> findAll();
}
