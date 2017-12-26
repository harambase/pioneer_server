package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.base.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends JpaRepository<Transcript, Integer> {

    int countByStudentIdAndCrnAndComplete(String studentId, String crn, String complete);

    int countByStudentIdAndCrn(String studentId, String crn);

    void deleteTranscriptByStudentIdAndCrn(String studentId, String crn);

    void deleteTranscriptByStudentId(String userId);
}