package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Transcript;

public interface TranscriptService {

    HaramMessage updateGrade(Transcript transcript);

    HaramMessage transcriptList(String s, String s1, String search, String order, String orderCol, String studentId, String crn);

}
