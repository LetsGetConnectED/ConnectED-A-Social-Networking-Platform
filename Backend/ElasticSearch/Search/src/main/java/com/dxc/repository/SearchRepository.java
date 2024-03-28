package com.dxc.repository;

import com.dxc.model.Search;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SearchRepository extends ElasticsearchRepository<Search, String> {

    List<Search> findByFullNameContaining(String fullName);

    List<Search> findByJobTitleContaining(String jobTitle);

    List<Search> findByCompanyNameContaining(String companyName);

    List<Search> findBySkillsContaining(String skills);

    List<Search> findByLocation(String location);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"fullName\": \"?0\"}},{\"match\": {\"location\": \"?1\"}}]}}")
    List<Search> findByFullNameAndLocation(String fullName, String location);
}
