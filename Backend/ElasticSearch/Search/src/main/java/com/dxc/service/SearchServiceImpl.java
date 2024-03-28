package com.dxc.service;

import com.dxc.model.Search;
import com.dxc.model.SearchCriteria;
import com.dxc.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchRepository searchRepository;

    @Override
    public List<Search> searchUsers(SearchCriteria criteria) {
        if (criteria.getSearchTerm() != null && !criteria.getSearchTerm().isEmpty()) {
            // Search by people (full name)
            return searchRepository.findByFullNameContaining(criteria.getSearchTerm());
        } else if (criteria.getJobTitle() != null && !criteria.getJobTitle().isEmpty()) {
            // Search by job title
            return searchRepository.findByJobTitleContaining(criteria.getJobTitle());
        } else if (criteria.getCompanyName() != null && !criteria.getCompanyName().isEmpty()) {
            // Search by company name
            return searchRepository.findByCompanyNameContaining(criteria.getCompanyName());
        } else if (criteria.getSkills() != null && !criteria.getSkills().isEmpty()) {
            // Search by skills
            return searchRepository.findBySkillsContaining(criteria.getSkills());
        } else if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
            // Search by location
            return searchRepository.findByLocation(criteria.getLocation());
        } else {
            // Default search (can handle additional cases or return all users)
            return (List<Search>) searchRepository.findAll();
        }
    }
}
