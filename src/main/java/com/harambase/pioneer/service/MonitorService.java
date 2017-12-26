package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;

public interface MonitorService {

    HaramMessage getRelationChart();

    HaramMessage userChart();

    HaramMessage getSystemCount();
}
