package com.backend.app.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.JwtUtil;
import com.backend.app.database.service.FriendService;
import com.backend.app.database.service.SearchService;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.SearchResponseDTO;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://localhost:3000")
public class SearchController {
  @Autowired
  JwtUtil jwt;

  SearchService searchService;
  public SearchController(SearchService searchService, FriendService friendService){
    this.searchService = searchService;
  }

  @GetMapping("/search")
  public SearchResponseDTO fetchDetails(@RequestHeader("key") String key, @RequestHeader("type") String type) {
      return searchService.fetchSearchData(key,type);
  }

  @GetMapping("/is-friend")
  public ResponseDTO isFriendController(@RequestHeader("username") String username, @RequestHeader("friend") String friend) {
      boolean res = searchService.isFriendService(username, friend);
      ResponseDTO responseDTO = new ResponseDTO();
      if(res){
        responseDTO.setResponse("friend");
      }
      else{
        responseDTO.setResponse("not friend");
      }
      return responseDTO;
  }
  
  

  @GetMapping("/search-validate")
  public SearchResponseDTO validateSearch(@RequestHeader ("authorization") String header) {
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
