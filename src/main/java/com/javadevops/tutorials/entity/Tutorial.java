package com.javadevops.tutorials.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tutorials")
public class Tutorial {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(length = 128, nullable = false)
  private String title;

  @Column(length = 256)
  private String description;

  @Column(nullable = false)
  private Integer level;

  @Column
  private boolean published;

  @Column(columnDefinition = "LONGTEXT")
  private String content;

  @Column(columnDefinition = "LONGTEXT")
  private String referenceLinks;

  @Column(length = 100)
  private String category;

  @Column(length = 500)
  private String tags;

  @Column(length = 255)
  private String author;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  public Tutorial() {

  }

  public Tutorial(String title, String description, Integer level, boolean published) {
    this.title = title;
    this.description = description;
    this.level = level;
    this.published = published;
  }

  public Tutorial(String title, String description, Integer level, boolean published, String content,
                  String category, String author) {
    this.title = title;
    this.description = description;
    this.level = level;
    this.published = published;
    this.content = content;
    this.category = category;
    this.author = author;
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }

  // ... existing code ...

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getReferenceLinks() {
    return referenceLinks;
  }

  public void setReferenceLinks(String referenceLinks) {
    this.referenceLinks = referenceLinks;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", description=" + description + ", level=" + level
        + ", published=" + published + ", category=" + category + ", author=" + author + "]";
  }

}

