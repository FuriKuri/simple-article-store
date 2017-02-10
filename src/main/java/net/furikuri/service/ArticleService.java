package net.furikuri.service;

import net.furikuri.domain.Article;
import net.furikuri.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {
    @Autowired private ArticleRepository repository;

    public void add(Article article) {
        article.setId(UUID.randomUUID().toString());
        repository.save(article);
    }

    public List<Article> all() {
        return repository.findAll();
    }
}
