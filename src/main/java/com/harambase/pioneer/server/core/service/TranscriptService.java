package com.harambase.pioneer.server.core.service;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.core.pojo.base.Transcript;

public interface TranscriptService {

    HaramMessage updateGrade(Integer id, Transcript transcript);

    HaramMessage transcriptList(String s, String s1, String search, String order, String orderCol,
                                String studentId, String crn, String info, String complete);

}
