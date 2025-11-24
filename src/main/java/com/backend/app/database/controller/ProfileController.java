package com.backend.app.database.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.JwtUtil;
import com.backend.app.database.service.ProfileService;
import com.backend.app.dto.ProfileDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://localhost:3000")
public class ProfileController {

  @Autowired
  JwtUtil jwtUtil;

  private ProfileService profileService;
  public ProfileController(ProfileService profileService){
    this.profileService = profileService;
  }

  @GetMapping("/user-profile")
  public ProfileDTO getMethodName(@RequestHeader("offset") int offset, @RequestHeader("username") String username, @RequestHeader("authentication") String authentication) {
      if(jwtUtil.validateToken(authentication)){
        return profileService.fetchProfileDetails(offset, username, jwtUtil.extractEmail(authentication));
      }
      else{
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setResponse("invalid");
        return profileDTO;
      }
  }
  
}
