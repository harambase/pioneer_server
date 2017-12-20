package com.harambase.pioneer.dao.repository;

import com.harambase.pioneer.pojo.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends JpaRepository<Transcript, Integer> {

}