package com.harambase.pioneer.server.core.service;

import com.harambase.pioneer.common.HaramMessage;

public interface MonitorService {

    HaramMessage getRelationChart();

    HaramMessage userChart();

    HaramMessage getSystemCount();
}
