package com.poc.portfolio.controller;

import com.poc.portfolio.education.Education;
import com.poc.portfolio.enums.*;
import com.poc.portfolio.experience.Experience;
import com.poc.portfolio.experience.TechExperience;
import com.poc.portfolio.img.arrow.ArrowImgTag;
import com.poc.portfolio.img.client.ClientImgTag;
import com.poc.portfolio.img.project.ProjectImgTag;
import com.poc.portfolio.img.social.SocialMediaImgTag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.poc.portfolio.enums.BackendTech.*;
import static com.poc.portfolio.enums.Clients.*;
import static com.poc.portfolio.enums.CloudTech.AWS;
import static com.poc.portfolio.enums.DatabaseTech.*;
import static com.poc.portfolio.enums.Designations.*;
import static com.poc.portfolio.enums.ExperienceLevel.EXPERIENCED;
import static com.poc.portfolio.enums.FrontendTech.*;
import static com.poc.portfolio.enums.Titles.*;
import static com.poc.portfolio.utils.Constants.*;

@Controller
public class PortfolioController {

  @GetMapping("/")
  public String portfolio(Model model) {
    model.addAttribute("myName", MY_NAME);
    model.addAttribute("myExpInYear", MY_EXPERIENCE_IN_YEARS);
    model.addAttribute("myEmail", MY_EMAIL_ID);
    model.addAttribute("titles", getTitles());
    model.addAttribute("social", getSocialMediaImgTagAttributes());
    model.addAttribute("projects", getProjectImgTagAttributes());
    model.addAttribute("profilepic", PROFILE_PIC_ASSET_URL);
    model.addAttribute("aboutpic", ABOUT_PIC_ASSET_URL);
    model.addAttribute("experience", EXPERIENCE_ASSET_URL);
    model.addAttribute("education", EDUCATION_ASSET_URL);
    model.addAttribute("arrow", getArrowImgTagAttributes());
    model.addAttribute("checkmark", CHECKMARK_ASSET_URL);
    model.addAttribute("clients", getClientImgTagAttributes());
    model.addAttribute("frontend", getFrontendDevelopmentExperience());
    model.addAttribute("backend", getBackendDevelopmentExperience());
    model.addAttribute("cloud", getCloudExperience());
    model.addAttribute("database", getDatabaseExperience());
    model.addAttribute("banner", POC_BANNER_URL);
    model.addAttribute("linkedin", LINKEDIN_URL);
    model.addAttribute("mailtoemail", MAIL_TO_EMAIL);
    model.addAttribute("email", EMAIL_ASSET_URL);
    model.addAttribute("linkedinpic", LINKEDIN_ASSET_URL);
    model.addAttribute("education",geteducation());
    model.addAttribute("experience",getexperience());

    return "index";
  }


private List<Experience> getexperience() {
	List<Experience> e=new ArrayList<>();
	e.add(Experience.builder().role("Web Development").organization("Cascode").date("25/2/2023-25/3/2023").build());
	e.add(Experience.builder().role("Web Development").organization("Lets Grow More").date("1/3/2023-1/4/2023").build());
	return e;
}


@GetMapping(path = "/download")
  public ResponseEntity<Resource> downloadResume() throws IOException {
	
	 ClassPathResource resource = new ClassPathResource("static/file/Sourabh_Resume.pdf");

	    // Ensure the resource is available and calculate its length
	    if (!resource.exists()) {
	        throw new FileNotFoundException("Resume file not found!");
	    }    
    /*Set the Content-Disposition header to "attachment", which forces the browser to download the file rather than display it. 
     * RESUME_FILENAME is the name of the file that will appear in the user's download dialog.*/
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + RESUME_FILENAME + "\"")
        .contentLength(resource.contentLength())
        .contentType(MediaType.parseMediaType(RESUME_MEDIA_TYPE))
        .body(resource);
  }

  private static List<String> getTitles() {
    List<String> listOfTitles = new ArrayList<>();
    listOfTitles.add(SOFTWARE_DEVELOPMENT_ENGINEER.getValue());
    listOfTitles.add(BACKEND_DEVELOPER.getValue());
    listOfTitles.add(GYM_TRAINER.getValue());
    return listOfTitles;
  }

  private static List<SocialMediaImgTag> getSocialMediaImgTagAttributes() {
    List<SocialMediaImgTag> imgTagList = new ArrayList<>();

  
    imgTagList.add(SocialMediaImgTag.builder().assetUrl(LINKEDIN_ASSET_URL).altName("My LinkedIn profile")
        .onClickAction(LINKEDIN_URL).build());
    imgTagList.add(SocialMediaImgTag.builder().assetUrl(GITHUB_ASSET_URL).altName("My Github profile")
        .onClickAction(GITHUB_URL).build());
  
    return imgTagList;
  }

  private static List<ProjectImgTag> getProjectImgTagAttributes() {
    List<ProjectImgTag> imgTagList = new ArrayList<>();

    imgTagList.add(ProjectImgTag.builder().assetUrl(Yamaha_ASSET_URL).altName(Yamaha).projectName(Yamaha)
            .onGithubClickAction(Yamaha_GITHUB_URL)
            .build());
    
    imgTagList.add(ProjectImgTag.builder().assetUrl(SCM_ASSET_URL).altName(SCM).projectName(SCM)
        .onGithubClickAction(SCM_GITHUB_URL)
        .build());

    imgTagList.add(ProjectImgTag.builder().assetUrl(Microservice_ASSET_URL).altName(Microservice)
        .projectName(Microservice).onGithubClickAction(Microservice_GITHUB_URL)
       .build());

    imgTagList.add(ProjectImgTag.builder().assetUrl(BLOG_Application_ASSET_URL).altName(BLOG_Application)
        .projectName(BLOG_Application).onGithubClickAction(BLOG_Application_GITHUB_URL)
       .build());

    imgTagList.add(ProjectImgTag.builder().assetUrl(NoteTaker_ASSET_URL).altName(NoteTaker)
            .projectName(NoteTaker).onGithubClickAction(NoteTaker_GITHUB_URL)
           .build());

    imgTagList.add(ProjectImgTag.builder().assetUrl(Library_ASSET_URL).altName(Library)
            .projectName(Library).onGithubClickAction(Library_GITHUB_URL)
           .build());

    return imgTagList;
  }

  private static ArrowImgTag getArrowImgTagAttributes() {
    return ArrowImgTag.builder().assetUrl(ARROW_ASSET_URL).altName("Arrow icon").build();
  }

  private static List<ClientImgTag> getClientImgTagAttributes() {
    List<ClientImgTag> imgTagList = new ArrayList<>();

    imgTagList.add(ClientImgTag.builder().assetUrl(ORACLE_LOGO_URL).altName("oracle-logo")
        .clientName(ORACLE.getValue()).designation(SDE_2.getValue()).draggable(false).build());
    imgTagList.add(ClientImgTag.builder().assetUrl(MACQUARIE_LOGO_URL).altName("mcq-logo")
        .clientName(MCQ.getValue()).designation(CONSULTANT.getValue()).draggable(false).build());
    imgTagList.add(ClientImgTag.builder().assetUrl(AMEX_LOGO_URL).altName("amex-logo").clientName(AMEX.getValue())
        .designation(SDE_1.getValue()).draggable(false).build());
    imgTagList.add(ClientImgTag.builder().assetUrl(EY_LOGO_URL).altName("ey-logo").clientName(EY.getValue())
        .designation(SENIOR_CONSULTANT.getValue()).draggable(false).build());
    imgTagList.add(ClientImgTag.builder().assetUrl(HSBC_LOGO_URL).altName("hsbc-logo").clientName(HSBC.getValue())
        .designation(SDE_1.getValue()).draggable(false).build());
    imgTagList.add(ClientImgTag.builder().assetUrl(CAPGEMINI_LOGO_URL).altName("capg-logo")
        .clientName(CAPG.getValue()).designation(SENIOR_ANALYST.getValue()).draggable(false).build());
    imgTagList.add(ClientImgTag.builder().assetUrl(INFOSYS_LOGO_URL).altName("infy-logo")
        .clientName(INFOSYS.getValue()).designation(CONSULTANT.getValue()).draggable(false).build());

    return imgTagList;
  }

  private static List<TechExperience> getFrontendDevelopmentExperience() {
    List<TechExperience> imgTagList = new ArrayList<>();

    imgTagList.add(TechExperience.builder().technology(HTML.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/html.png").build());
    imgTagList.add(TechExperience.builder().technology(CSS.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/css.png").build());
    imgTagList.add(TechExperience.builder().technology(THYMELEAF.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/thymeleaf.png").build());
    imgTagList.add(TechExperience.builder().technology(JAVASCRIPT.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/javascript.png").build());
    imgTagList.add(TechExperience.builder().technology(BOOTSTRAP.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/bootstrap.png").build());
    return imgTagList;
  }

  private static List<TechExperience> getBackendDevelopmentExperience() {
    List<TechExperience> imgTagList = new ArrayList<>();
    imgTagList.add(TechExperience.builder().technology(JAVA.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/java.png").build());
    imgTagList.add(TechExperience.builder().technology(SPRING_BOOT.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/spring.png").build());
    imgTagList.add(TechExperience.builder().technology(HIBERNATE.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/hibernate.png").build());
    imgTagList.add(TechExperience.builder().technology(MICROSERVICES.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/microservices.png").build());  
    return imgTagList;
  }

  private static List<TechExperience> getCloudExperience() {
    List<TechExperience> imgTagList = new ArrayList<>();

    imgTagList.add(TechExperience.builder().technology(AWS.getValue()).expLevel(ExperienceLevel.BEGINNER.getValue()).asseturl("/assets/aws.png").build());

    return imgTagList;
  }

  private static List<TechExperience> getDatabaseExperience() {
    List<TechExperience> imgTagList = new ArrayList<>();

    imgTagList.add(TechExperience.builder().technology(MYSQL.getValue()).expLevel(EXPERIENCED.getValue()).asseturl("/assets/sql.png").build());

    return imgTagList;
  }

  private List<Education> geteducation() {
	  List<Education> e=new ArrayList<>();
	  e.add(Education.builder().board("SSC").name("St. Patrick's High School").year("2017-18").per("89.47%").build());
	  e.add(Education.builder().board("HSC").name("Annasaheb Magar College").year("2019-20").per("75.85%").build());
	  e.add(Education.builder().board("BE in Computer Engineering").name("Sinhgad Academy of Engineering").year("2020-24").per("9.16 CGPA").build());
	  return e;
}
 
}
