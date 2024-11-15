package com.poc.portfolio.enums;

public enum BackendTech {
  JAVA("JAVA 8+"), NODE("Node JS"), EXPRESS("Express JS"), SPRING_BOOT("Spring-Boot"),HIBERNATE("Hibernate"),MICROSERVICES("Microservices");

  private final String value;

  BackendTech(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
