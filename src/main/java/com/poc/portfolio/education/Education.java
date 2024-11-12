package com.poc.portfolio.education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Education {

	String board;
	String name;
	String year;
	String per;
}
