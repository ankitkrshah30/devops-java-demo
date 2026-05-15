package com.javadevops.tutorials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javadevops.tutorials.repository.TutorialRepository;

@Controller
public class HomeController {

  @Autowired
  private TutorialRepository tutorialRepository;

  @GetMapping("/")
  public String home(Model model) {
    long totalTutorials = tutorialRepository.count();
    long publishedTutorials = tutorialRepository.countByPublished(true);

    model.addAttribute("totalTutorials", totalTutorials);
    model.addAttribute("publishedTutorials", publishedTutorials);
    model.addAttribute("unpublishedTutorials", totalTutorials - publishedTutorials);

    return "index";
  }
}

