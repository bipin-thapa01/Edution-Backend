package com.backend.app.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.JwtUtil;
import com.backend.app.database.service.SearchService;
import com.backend.app.dto.SearchResponseDTO;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://localhost:3000")
public class SearchController {
  @Autowired
  JwtUtil jwt;

  SearchService searchService;
  public SearchController(SearchService searchService){
    this.searchService = searchService;
  }

  @GetMapping("/search-validate")
  public SearchResponseDTO getMethodName(@RequestHeader ("authorization") String header) {
    String token = header;
      if(jwt.validateToken(token)){
        String email = jwt.extractEmail(token);
        return searchService.fetchDefaultUsers(email);
      }
      else{
        SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
        searchResponseDTO.setRes("invalid");
        return searchResponseDTO;
      }
  }
}
