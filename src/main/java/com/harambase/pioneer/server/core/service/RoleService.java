package com.harambase.pioneer.server.core.service;

import com.harambase.pioneer.common.HaramMessage;

public interface RoleService {
    HaramMessage get(Integer roleId);

    HaramMessage list(String search, String order, String orderCol);
}
