package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.base.TranscriptBase;

public interface TranscriptService {

    HaramMessage updateGrade(TranscriptBase transcript);

    HaramMessage transcriptList(String s, String s1, String search, String order, String orderCol, String studentId, String crn);

}
