package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.base.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends JpaRepository<Transcript, Integer> {

    int countByStudentidAndCrnAndComplete(String studentid, String crn, String complete);

    int countByStudentidAndCrn(String studentid, String crn);

    void deleteTranscriptByStudentidAndCrn(String studentid, String crn);

    void deleteTranscriptByStudentid(String userid);
}