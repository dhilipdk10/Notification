package com.web3.notification.model;

import javax.annotation.Priority;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "category04")
public class CategoryModel {
  @Id
  @Column(name = "ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "ttl")
  private String ttl;
  
  @Column(name = "priority", columnDefinition="enum('HIGH','MEDIUM','LOW')")
  private String priority;

  @Column(name = "rateLimit")
  private String rateLimit;

  public CategoryModel() {

  }

  public CategoryModel(int id, String name, String description, String priority, String rateLimit) {
    super();
    this.id = id;
    this.name = name;
    this.description = description;
    this.priority = priority;
    this.rateLimit = rateLimit;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTtl() {
    return this.ttl;
  }

  public void setTtl(String ttl) {
    this.ttl = ttl;
  }

  public String getPriority() {
    return this.priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getRateLimit() {
    return this.rateLimit;
  }

  public void setRateLimit(String rateLimit) {
    this.rateLimit = rateLimit;
  }

}
