package net.furikuri.controller;


import net.furikuri.domain.Article;
import net.furikuri.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ArticleController {

    @Autowired private ArticleService articleService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", articleService.all());
        model.addAttribute("article", new Article());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addTodo(@ModelAttribute Article article) {
        articleService.add(article);
        return "redirect:/";
    }
}
