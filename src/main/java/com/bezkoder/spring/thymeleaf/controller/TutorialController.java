package com.bezkoder.spring.thymeleaf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bezkoder.spring.thymeleaf.entity.Tutorial;
import com.bezkoder.spring.thymeleaf.repository.TutorialRepository;

@Controller
public class TutorialController {

  @Autowired
  private TutorialRepository tutorialRepository;

  @GetMapping("/tutorials")
  public String getAll(Model model, @Param("keyword") String keyword) {
    try {
      List<Tutorial> tutorials = new ArrayList<Tutorial>();

      if (keyword == null || keyword.isEmpty()) {
        tutorialRepository.findAll().forEach(tutorials::add);
      } else {
        tutorialRepository.findByTitleContainingIgnoreCase(keyword).forEach(tutorials::add);
        model.addAttribute("keyword", keyword);
      }

      model.addAttribute("tutorials", tutorials);
    } catch (Exception e) {
      model.addAttribute("message", "Error: " + e.getMessage());
    }

    return "tutorials";
  }

  @GetMapping("/tutorials/new")
  public String addTutorial(Model model) {
    try {
      Tutorial tutorial = new Tutorial();
      tutorial.setPublished(false);  // Default to unpublished (draft)

      model.addAttribute("tutorial", tutorial);
      model.addAttribute("pageTitle", "Create new Tutorial");

      return "tutorial_form";
    } catch (Exception e) {
      model.addAttribute("message", "Error loading form: " + e.getMessage());
      return "redirect:/tutorials";
    }
  }

  @PostMapping("/tutorials/save")
  public String saveTutorial(Tutorial tutorial, RedirectAttributes redirectAttributes) {
    try {
      System.out.println("=== SAVING TUTORIAL ===");
      System.out.println("Title: " + tutorial.getTitle());
      System.out.println("Description: " + tutorial.getDescription());
      System.out.println("Level: " + tutorial.getLevel());
      System.out.println("Published: " + tutorial.isPublished());
      System.out.println("Author: " + tutorial.getAuthor());
      System.out.println("Category: " + tutorial.getCategory());
      System.out.println("Tags: " + tutorial.getTags());
      System.out.println("Content: " + (tutorial.getContent() != null ? tutorial.getContent().substring(0, Math.min(50, tutorial.getContent().length())) : "null"));
      System.out.println("ID: " + tutorial.getId());

      // Validate required fields
      if (tutorial.getTitle() == null || tutorial.getTitle().trim().isEmpty()) {
        redirectAttributes.addFlashAttribute("message", "Error: Title is required!");
        return "redirect:/tutorials/new";
      }

      if (tutorial.getLevel() == null || tutorial.getLevel() == 0) {
        redirectAttributes.addFlashAttribute("message", "Error: Difficulty Level is required!");
        return "redirect:/tutorials/new";
      }

      // Set timestamps
      Date now = new Date();
      if (tutorial.getId() == null) {
        tutorial.setCreatedAt(now);
        System.out.println("Creating new tutorial...");
      } else {
        System.out.println("Updating tutorial...");
      }
      tutorial.setUpdatedAt(now);

      Tutorial savedTutorial = tutorialRepository.save(tutorial);
      System.out.println("Saved Tutorial ID: " + savedTutorial.getId());
      System.out.println("=== SAVE COMPLETE ===");

      redirectAttributes.addFlashAttribute("message", "Tutorial saved successfully!");
      return "redirect:/tutorials";

    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
      e.printStackTrace();
      redirectAttributes.addFlashAttribute("message", "Error saving tutorial: " + e.getMessage());
      return "redirect:/tutorials";
    }
  }

  @GetMapping("/tutorials/{id}")
  public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      Tutorial tutorial = tutorialRepository.findById(id).get();

      model.addAttribute("tutorial", tutorial);
      model.addAttribute("pageTitle", "Edit Tutorial (ID: " + id + ")");

      return "tutorial_form";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "Error: Tutorial not found. " + e.getMessage());
      return "redirect:/tutorials";
    }
  }

  @GetMapping("/tutorials/delete/{id}")
  public String deleteTutorial(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
    try {
      tutorialRepository.deleteById(id);
      redirectAttributes.addFlashAttribute("message", "Tutorial with id=" + id + " has been deleted successfully!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "Error deleting tutorial: " + e.getMessage());
    }

    return "redirect:/tutorials";
  }

  @GetMapping("/tutorials/{id}/published/{status}")
  public String updateTutorialPublishedStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean published,
      RedirectAttributes redirectAttributes) {
    try {
      tutorialRepository.updatePublishedStatus(id, published);

      String status = published ? "published" : "unpublished";
      redirectAttributes.addFlashAttribute("message", "Tutorial id=" + id + " has been " + status);
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
    }

    return "redirect:/tutorials";
  }
}


