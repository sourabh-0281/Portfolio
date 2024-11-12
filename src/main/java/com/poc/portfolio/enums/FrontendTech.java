package com.poc.portfolio.enums;

public enum FrontendTech {
  HTML("HTML"), CSS("CSS"),THYMELEAF("THYMELEAF"),JAVASCRIPT("JAVASCRIPT"),BOOTSTRAP("BOOTSTRAP");

  private final String value;

  FrontendTech(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
