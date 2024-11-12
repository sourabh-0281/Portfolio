package com.poc.portfolio.experience;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Experience {
  String  role;
  String organization;
  String date;
}
