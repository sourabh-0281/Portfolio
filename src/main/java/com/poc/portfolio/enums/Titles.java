package com.poc.portfolio.enums;

public enum Titles {
  SOFTWARE_DEVELOPMENT_ENGINEER("Software Development Engineer"), BACKEND_DEVELOPER("Backend Developer"), GYM_TRAINER(
      "Gym Trainer");

  private final String value;

  Titles(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
