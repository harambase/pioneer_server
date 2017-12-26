package com.harambase.pioneer.dao.repository.view;

import com.harambase.pioneer.pojo.view.TranscriptView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptViewRepository extends JpaRepository<TranscriptView, Integer> {
}
