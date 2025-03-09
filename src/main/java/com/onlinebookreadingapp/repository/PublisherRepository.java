package com.onlinebookreadingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebookreadingapp.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{

}
