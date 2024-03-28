package com.dxc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.model.Search;
import com.dxc.service.SeachService;
@RestController
@RequestMapping("/api/v1")
public class SearchController {
	
	  @Autowired
	 private final SeachService ss;

	    public SearchController(SeachService ss) {
	        this.ss = ss;
	    }

	    @GetMapping("/search")
	    public ResponseEntity<List<Search>> searchProducts(@RequestParam("username") String username){
	        return ResponseEntity.ok(ss.searchusername(username));
	    }

	    @PostMapping
	    public Search create(@RequestBody Search search){
	        return ss.create(search);
	    }
}
