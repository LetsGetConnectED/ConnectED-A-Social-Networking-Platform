package com.dxc.service;

import com.dxc.model.Search;
import com.dxc.model.SearchCriteria;

import java.util.List;

public interface SearchService {
    List<Search> searchUsers(SearchCriteria criteria);
    Object save(SearchCriteria criteria);
}
