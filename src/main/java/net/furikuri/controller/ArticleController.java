package net.furikuri.controller;


import net.furikuri.domain.Article;
import net.furikuri.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired private ArticleService articleService;

    @RequestMapping("/json")
    @ResponseBody
    public List<Article> all() {
        return articleService.all();
    }

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
