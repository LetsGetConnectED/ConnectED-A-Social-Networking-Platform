package com.dxc.controller;

import com.dxc.model.Search;
import com.dxc.model.SearchCriteria;
import com.dxc.repository.SearchRepository;
import com.dxc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    
    @Autowired
    private SearchRepository searchRepository;

    @PostMapping("/search")
    public Object save(@RequestBody SearchCriteria criteria) {
        return searchService.save(criteria);
    }
    
    @GetMapping
    public List<Search> findAll() {
        return searchRepository.findAll();
    }

    @GetMapping("/jobs/{jobTitle}")
    public List<Search> searchUsersByJobTitle(@PathVariable String jobTitle) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setJobTitle(jobTitle);
        return searchService.searchUsers(criteria);
    }

    @GetMapping("/companies/{companyName}")
    public List<Search> searchUsersByCompany(@PathVariable String companyName) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setCompanyName(companyName);
        return searchService.searchUsers(criteria);
    }

    @GetMapping("/skills/{skills}")
    public List<Search> searchUsersBySkills(@PathVariable String skills) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setSkills(skills);
        return searchService.searchUsers(criteria);
    }

    @GetMapping("/locations/{location}")
    public List<Search> searchUsersByLocation(@PathVariable String location) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setLocation(location);
        return searchService.searchUsers(criteria);
    }
}
