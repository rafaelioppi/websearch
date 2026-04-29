package com.websearch.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.websearch.model.Page;

import java.util.List;



public interface PageRepository extends JpaRepository<Page, Long> {
  
    List<Page> findByTitleContainingIgnoreCase(String Keyword);
    List<Page> findByContentContainingIgnoreCase(String Keyword);   

}


    